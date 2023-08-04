package commands;

import com.sun.source.tree.Tree;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.internal.entities.GuildImpl;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.sql.*;

public class Commands extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event){
        if(event.getName().equals("menu")){
//            event.reply("You just farted").queue();
            EmbedBuilder embed = new EmbedBuilder();
            embed.setTitle("Доступні команди:");
            embed.setDescription("Також ви можете отримати додаткову інформацію по кожній команді, викликавши її за допомогою `?` підпишіть або передайте його назву, наприклад: `/help ?`");
            embed.addField("Інформація (/help info):", " `/help`\n `/info`\n `/stats`\n `/serverinfo`\n `/user`\n `/bio`\n `/inviteinfo`",false);
            embed.addField("Модерація (/help moderation):", "-",false);
            embed.addField("Музика (/help music):", "-",false);
            embed.addField("Розваги (/help fun):", "-",false);
            embed.addField("Утиліти (/help utility):", "-",false);

            embed.setFooter("Ka4ur entertainment © 2023", "https://i.imgur.com/s4130Er.png");
            embed.setThumbnail("https://i.imgur.com/s4130Er.png");
            embed.setColor(new Color(255, 255, 255));

            event.replyEmbeds(embed.build()).queue();

        }

        else if (event.getName().equals("info")){
            EmbedBuilder embed = new EmbedBuilder();

            embed.setTitle("Ka4ur");
            embed.setDescription("Привіт! Мене звуть Качуром! Я серйозний пернатий бот в Discord з багатьма цікавими функціями.\n\nМоїм префіксом є звичайний слеш `/`, більшого ти можеш побачити прописавши команду `/help`");
            embed.addField("Розробка:","0.0.1 (04.08.2023)", true);
            embed.addField("Автор:","Ka4e4ka ( @802093857910685716 )", true);

            embed.setColor(new Color(255,255,255));
            embed.setFooter("Ka4ur entertainment © 2023", "https://i.imgur.com/s4130Er.png");
            embed.setThumbnail("https://i.imgur.com/s4130Er.png");
            event.replyEmbeds(embed.build()).queue();

        }
        else if (event.getName().equals("stats")) {

        }
        else if (event.getName().equals("serverinfo")) {


        }
    }
}
