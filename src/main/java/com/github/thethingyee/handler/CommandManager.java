package com.github.thethingyee.handler;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class CommandManager extends ListenerAdapter {

    private static final ArrayList<Command> registeredCommands = new ArrayList<>();
    private static final ArrayList<SlashCommandData> registeredSlashCommands = new ArrayList<>();

    private final JDA jda;

    public CommandManager(JDA jda) {
        this.jda = jda;
    }

    public void registerCommands(Command... commands) {
        for (Command command : commands) {
            if(command.getCommandData() == null) continue;
            registeredCommands.add(command);
            registeredSlashCommands.add(command.getCommandData().addOptions(command.getOptions()));

//            System.out.println("C: " + command.getName() + " D: " + command.getDescription());
        }
    }
    public void initializeCommands() {
        System.out.println("Registering commands... " + jda.getGuilds().size() + " guilds");
        for(Guild guild : jda.getGuilds()) {
            guild.updateCommands().addCommands(registeredSlashCommands).queue();
            System.out.println("Commands registered on guild: " + guild.getName());
        }
    }

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        for(Command command : registeredCommands) {
            if(!event.getName().equals(command.getCommandData().getName())) continue;
            command.executeCommand(event);
        }
    }
}
