package commands;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.channel.concrete.VoiceChannel;
import net.dv8tion.jda.api.entities.channel.middleman.GuildChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import sql.Sqlite;
import utilts.Addons;

import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.List;


public class Information extends ListenerAdapter{
    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event){

        if(event.getName().equals("help")){
//            event.reply("You just farted").queue();
            EmbedBuilder embed = new EmbedBuilder();
            embed.setTitle("Доступні команди:");
            embed.setDescription("Також ви можете отримати додаткову інформацію по кожній команді, викликавши її за допомогою `?` підпишіть або передайте його назву, наприклад: `/help ?`");
            embed.addField("Інформація (/help info):", " `/help` `/info` `/stats` `/serverinfo` `/user` `/bio` `/inviteinfo`",false);
            embed.addField("Модерація (/help moderation):", "`/mute` `/unmute` `/mutes` `/ban` `/unban` `/kick` `/warn` `/warns` `/unwarn` `/clear` `/slowmode` `/temprole`",false);
            embed.addField("Музика (/help music):", "`/play` `/skip` `/remove` `/queue` `/repeat` `/pause` `/start` `/stop` `/volume` `/restart`",false);
            embed.addField("Розваги (/help fun):", "-",false);
            embed.addField("Утиліти (/help utility):", "-",false);

            embed.setFooter("Ka4ur entertainment © 2023", "https://i.imgur.com/s4130Er.png");
            embed.setThumbnail("https://i.imgur.com/s4130Er.png");
            embed.setColor(new Color(255, 255, 255));

            event.replyEmbeds(embed.build()).queue();

            Sqlite sql = new Sqlite();

            sql.commands_count();

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

            Sqlite sql = new Sqlite();

            sql.commands_count();

        }
        else if (event.getName().equals("stats")) {

            Guild gld = event.getGuild();
            Addons addons = new Addons(event.getJDA());
            Sqlite sql = new Sqlite();

            int x = addons.getGuildscount();
            int y = addons.getChannelscount();
            int b = addons.getMemberscount();
            //event.getChannel().sendMessage("" + x).queue();
            long ping = addons.ping();
            int co = sql.countcom();

            EmbedBuilder embed = new EmbedBuilder();
            embed.setTitle("Статистика Качура");

            embed.addField("Звичайна", "Гільдії: `" + x + "`\nКористувачі: `" + b + "`\nКанали: `" + y + "`", true);
            embed.addField("Системна", "З'єднання: `" + ping + "ms`\nВикористання команд: `" + co + "`" , true);



            embed.setColor(new Color(255, 255 ,255));
            embed.setFooter("Ka4ur entertainment © 2023", "https://i.imgur.com/s4130Er.png");
            embed.setThumbnail("https://i.imgur.com/s4130Er.png");
            event.replyEmbeds(embed.build()).queue();




        }

        else if (event.getName().equals("serverinfo")) {

            JDA jda = event.getJDA();
            Guild gld = event.getGuild();
            EmbedBuilder embed = new EmbedBuilder();
            Addons addons = new Addons(event.getJDA());

            int Counttxt = 0;
            int Countvoice = 0;
            int Countmens = 0;
            int Countbots = 0;


            String name = gld.getName();
            String gld_id = gld.getId();
            String owner_id = gld.getOwnerId();
            String member = jda.getUserById(owner_id).getName();
            String data = gld.getTimeCreated().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"));



            for(TextChannel str: gld.getTextChannels()){
                Counttxt ++;
            }
            for (VoiceChannel str: gld.getVoiceChannels()){
                Countvoice++;
            }

            int sum = Counttxt + Countvoice;


            for (Member mmbr: gld.getMembers()){
                if (mmbr.getUser().isBot()) {
                    Countbots++;
                }
                else{
                    Countmens++;
                }
            }


            embed.setTitle("Інформація про " + name);
            embed.addField("Учасники:","Загалом: `" + gld.getMemberCount() + "`\nЛюди: `" + Countmens + "`\nБоти: `" + Countbots + "`", true);
            embed.addField("За статусом:","-", true);
            embed.addField("Канали:","Загалом: `" + sum + "`\nТекстові: `" + Counttxt + "`\nГолосові: `" + Countvoice + "`", true);

            embed.addField("Власник:","" + member, true);
            embed.addField("Створенний:","" + data, true);

            embed.setColor(new Color(255,255,255));
            embed.setFooter("ID: " + gld_id);

            try {
                String str =  gld.getIconUrl().toString();

                embed.setThumbnail(str);
                event.replyEmbeds(embed.build()).queue();

            }catch (NullPointerException e){

                embed.setFooter("ID: " + gld_id);
                event.replyEmbeds(embed.build()).queue();
            }


            Sqlite sql = new Sqlite();
            sql.commands_count();
        }

        else if (event.getName().equals("user")) {

            Guild gld = event.getGuild();
            String str =  gld.getIconUrl().toString();


            System.out.println(str);


            Sqlite sql = new Sqlite();

            sql.commands_count();
        }
        else if (event.getName().equals("bio")) {
            Sqlite sql = new Sqlite();

            sql.commands_count();

        }

        else if (event.getName().equals("inviteinfo")) {
            Sqlite sql = new Sqlite();



            sql.commands_count();

        }

    }
}
