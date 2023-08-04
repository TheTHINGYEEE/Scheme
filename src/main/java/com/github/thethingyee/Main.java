package com.github.thethingyee;

import com.github.thethingyee.commands.SetChannel;
import com.github.thethingyee.commands.Suggest;
import com.github.thethingyee.handler.CommandManager;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        JDA jda = JDABuilder.createDefault(args[0])
//                .enableIntents(GatewayIntent.DIRECT_MESSAGES, GatewayIntent.GUILD_MEMBERS, GatewayIntent.MESSAGE_CONTENT)
                .build();
        jda.getPresence().setActivity(Activity.listening("your suggestions!"));

        jda.awaitReady();

        CommandManager cmdManager = new CommandManager(jda);
        jda.addEventListener(cmdManager);

        cmdManager.registerCommands(
                new Suggest(),
                new SetChannel()
        );
        cmdManager.initializeCommands();
    }
}
