package com.github.thethingyee.handler;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;

import java.awt.*;

public class GuildOptions {

    private final Guild guild;
    private TextChannel suggestionsChannel;
    private Color embedColor;

    public GuildOptions(Guild guild, TextChannel suggestionsChannel) {
        this.guild = guild;
        this.suggestionsChannel = suggestionsChannel;
        this.embedColor = Color.CYAN;
    }

    public void setSuggestionsChannel(TextChannel suggestionsChannel) {
        this.suggestionsChannel = suggestionsChannel;
    }

    public void setEmbedColor(Color embedColor) {
        this.embedColor = embedColor;
    }

    public Guild getGuild() {
        return guild;
    }

    public TextChannel getSuggestionsChannel() {
        return suggestionsChannel;
    }

    public Color getEmbedColor() {
        return embedColor;
    }
}
