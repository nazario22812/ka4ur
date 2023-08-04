import commands.Commands;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.JDA;
import commands.Commands;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

import java.util.EventListener;
import sql.Sqlite;

public class App implements EventListener {
    public static void main(String[] args) {
        JDA jda = JDABuilder.createDefault("MTEzNjYyNDExMTkyNjM4MjY3Mg.G-vU25.mLOlFd2yGxK_F38FgGmJGMRijmE1YDYbN2Cth4")
            .addEventListeners(new Events())
            .addEventListeners(new Commands())
            .enableIntents(GatewayIntent.GUILD_MESSAGES, GatewayIntent.DIRECT_MESSAGES, GatewayIntent.MESSAGE_CONTENT)// Enable the bulk delete event
            .setBulkDeleteSplittingEnabled(false)
        // Set activity (like "playing Something")
            .setActivity(Activity.playing("Counter-Strike: Global Offensive"))
            .build();

        jda.upsertCommand("menu", "Show help for the bot").queue();
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
