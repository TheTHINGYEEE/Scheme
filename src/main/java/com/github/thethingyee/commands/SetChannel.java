package com.github.thethingyee.commands;

import com.github.thethingyee.handler.Command;
import com.github.thethingyee.handler.GuildOptions;
import com.github.thethingyee.handler.SuggestionHandler;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;

import java.util.List;

public class SetChannel extends Command {
    @Override
    public SlashCommandData getCommandData() {
        return Commands.slash("setchannel", "Sets the channel for the suggestions.");
    }

    @Override
    public List<OptionData> getOptions() {
        return List.of(new OptionData(OptionType.CHANNEL, "channel", "THe text channel for the suggestions."));
    }

    @Override
    public void executeCommand(SlashCommandInteractionEvent event) {
        if(event.getOption("channel") == null) {
            event.reply("This channel doesn't exist!").queue();
            return;
        }
        TextChannel channel = event.getOption("channel").getAsChannel().asTextChannel();
        GuildOptions options = new GuildOptions(event.getGuild(), channel);
        SuggestionHandler.guildOptions.put(event.getGuild(), options);

        event.reply("The suggestions channel has been set to " + channel.getAsMention()).queue();
    }
}
