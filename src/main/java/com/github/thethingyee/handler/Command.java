package com.github.thethingyee.handler;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;

import java.util.List;

public abstract class Command {

    public abstract SlashCommandData getCommandData();
    public abstract List<OptionData> getOptions();
    public abstract void executeCommand(SlashCommandInteractionEvent event);

}
