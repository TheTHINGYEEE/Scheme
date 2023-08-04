package com.github.thethingyee.handler;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import java.util.HashMap;

public class SuggestionHandler {

    public static HashMap<Guild, Suggestion> guildSuggestion = new HashMap<>();
    public static HashMap<Guild, GuildOptions> guildOptions = new HashMap<>();

    public static void registerSuggestion(Suggestion suggestion, SlashCommandInteractionEvent event) {
        Guild guild = event.getGuild();
        if(!hasSuggestionChannel(guild)) return;

        EmbedBuilder builder = new EmbedBuilder();
        builder.setColor(SuggestionHandler.guildOptions.get(guild).getEmbedColor());
        builder.setDescription("\"" + suggestion.getSuggestion() + "\"");
        builder.setAuthor(suggestion.getSuggestor().getName(), null, suggestion.getSuggestor().getEffectiveAvatarUrl());
        builder.setFooter(suggestion.getUUID() + " | Licensed under GPL-3.0");

        TextChannel channel = guildOptions.get(guild).getSuggestionsChannel();

        channel.sendMessageEmbeds(builder.build()).queue(message -> {
            message.addReaction(Emoji.fromUnicode("⬆️")).queue();
            message.addReaction(Emoji.fromUnicode("⬇️")).queue();
            suggestion.setMessage(message);
        });
        guildSuggestion.put(guild, suggestion);

        if(event.getChannel().asTextChannel() == channel) {
            event.reply("Suggested!").queue(message -> {
                message.deleteOriginal().queue();
            });
            return;
        }
        event.reply("Suggested!").queue();
    }

    public static void unregisterSuggestion(Suggestion suggestion) {

    }
    public static boolean hasSuggestionChannel(Guild guild) {
        return guildOptions.containsKey(guild);
    }
}
