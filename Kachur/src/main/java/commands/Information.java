package commands;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.channel.concrete.VoiceChannel;
import net.dv8tion.jda.api.entities.channel.middleman.GuildChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.internal.requests.MemberChunkManager;
import org.jetbrains.annotations.NotNull;
import sql.Sqlite;
import utilts.Addons;

import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;


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

            event.getGuild().loadMembers().onSuccess(members -> {
                int Counttxt = 0;
                int Countvoice = 0;


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


                int[] users = {0};
                int[] bots = {0};


                for(Member mmber: members){
                    if (mmber.getUser().isBot())
                        bots[0]++;
                    else
                        users[0]++;
                }



                embed.setTitle("Інформація про " + name);
                embed.addField("Учасники:","Загалом: `" + gld.getMemberCount() + "`\nЛюди: `" + users[0] + "`\nБоти: `" + bots[0] + "`", true);
                //embed.addField("За статусом:","Онлайн: `" + online + "`\nВідійшов: `" + idle + "`\nНе турбувати: `" + blocked + "`\nОффлайн: `" + offline + "`", true);
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


            });




            Sqlite sql = new Sqlite();
            sql.commands_count();
        }

        else if (event.getName().equals("user")) {
            //event.deferReply().queue(); // Запобігає автоматичній відповіді перед обробкою

            Sqlite sql = new Sqlite();
            OptionMapping optionMapping = event.getOption("member");
            EmbedBuilder embed = new EmbedBuilder();



            if(optionMapping != null){
                User user = optionMapping.getAsUser();
                Member member = optionMapping.getAsMember();
                OnlineStatus status = member.getOnlineStatus();


                String join = member.getTimeJoined().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"));
                LocalDateTime currentTime = LocalDateTime.now();
                LocalDateTime createTime = member.getTimeJoined().toLocalDateTime();// Получение времени создания из объекта gld
                Duration duration = Duration.between(createTime, currentTime); // Вычисление разницы между текущим временем и временем создания
                long daysPassed = duration.toDays(); // Получение количества прошедших дней


                String registed = member.getTimeCreated().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"));
                LocalDateTime currentTime1 = LocalDateTime.now();
                LocalDateTime createTime1 = member.getTimeCreated().toLocalDateTime();// Получение времени создания из объекта gld
                Duration duration1 = Duration.between(createTime1, currentTime1); // Вычисление разницы между текущим временем и временем создания
                long daysPassed1 = duration1.toDays(); // Получение количества прошедших дней

                String name = user.getName();
                String fullname = member.getAsMention();

                embed.setTitle("Інформація про" + name);
                embed.setDescription("Також ти додати додаткову інформацію про себе використовуючи `/bio`");

                //embed.addField("","",false);
                embed.addField("Звичайна інформація\nНікнейм:","" + name + "(" + fullname + ")",false);
                embed.addField("Cтатус:","" + status,false);
                embed.addField("Приєднався:","" + join + " (`" + daysPassed + "дні тому`)",false);
                embed.addField("Зареєструвався:","" + registed + " (`" + daysPassed1 + "дні тому`)",false);


                embed.setFooter("ID: " + user.getId());
                embed.setThumbnail(user.getAvatarUrl().toString());
                embed.setColor(new Color(member.getColorRaw()));

                event.replyEmbeds(embed.build()).queue();
            }
            else{
                event.reply("да да").queue();
            }




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
