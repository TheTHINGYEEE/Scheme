package com.github.thethingyee.commands;

import com.github.thethingyee.handler.Command;
import com.github.thethingyee.handler.Suggestion;
import com.github.thethingyee.handler.SuggestionHandler;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;

import java.util.List;

public class Suggest extends Command {

    @Override
    public SlashCommandData getCommandData() {
        return Commands.slash("suggest", "To suggest a suggestion, apparently.");
    }

    @Override
    public List<OptionData> getOptions() {
        return List.of(new OptionData(OptionType.STRING, "suggestion", "The suggestion. Pretty obvious right?"));
    }

    @Override
    public void executeCommand(SlashCommandInteractionEvent event) {
        if(!SuggestionHandler.hasSuggestionChannel(event.getGuild())) {
            event.reply("There is no suggestions channel set! Please contact the server owner.").queue();
            return;
        }
        String suggestion = event.getOption("suggestion").getAsString();
        Suggestion sugObject = new Suggestion(suggestion, event.getUser());

        SuggestionHandler.registerSuggestion(sugObject, event);
    }

    public static boolean allowed(Suggestion suggestion) {
        return (!(suggestion.getSuggestion().length() >= 5));
    }
}
