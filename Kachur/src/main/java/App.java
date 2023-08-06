import commands.Information;
import commands.Moderation;
import commands.Music;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.requests.GatewayIntent;

import java.util.EventListener;

public class App implements EventListener {
    public static void main(String[] args) {
        JDA jda = JDABuilder.createDefault("MTEzNjYyNDExMTkyNjM4MjY3Mg.GXzgnz.98D7F7c-DgNPbrpi18G_GAzZ4WTRb8qbewXYU4")
            .addEventListeners(new Events())
            .addEventListeners(new Information())
            .addEventListeners(new Moderation())
            .addEventListeners(new Music())
            .enableIntents(GatewayIntent.GUILD_MESSAGES, GatewayIntent.DIRECT_MESSAGES, GatewayIntent.MESSAGE_CONTENT, GatewayIntent.GUILD_MEMBERS)// Enable the bulk delete event
            .setBulkDeleteSplittingEnabled(false)
        // Set activity (like "playing Something")
            .setActivity(Activity.playing("Counter-Strike: Global Offensive"))
            .build();

        jda.upsertCommand("help", "Show help for the bot").queue();
        jda.upsertCommand("info", "Show information about me").queue();
        jda.upsertCommand("stats", "Show common statistics").queue();
        jda.upsertCommand("serverinfo", "Show information about server").queue();


//
//        Guild guild = jda.getGuildById("968840326502555658");
//
//        if(guild != null){
//            guild.upsertCommand("menu","you can show menu").queue();
//
//        }







    }



}
