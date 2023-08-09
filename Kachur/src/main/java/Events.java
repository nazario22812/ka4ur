import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.guild.GuildLeaveEvent;
import net.dv8tion.jda.api.events.message.MessageDeleteEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import sql.Sqlite;
import utilts.Addons;

import java.util.List;

public class Events extends ListenerAdapter {
    @Override
    public void onReady(ReadyEvent event){
        Sqlite sql = new Sqlite();
        sql.conn();

    }


    @Override
    public void onMessageReceived(MessageReceivedEvent event){

        // приймає повідомлення від чела і відсилає
        if(event.getMessage().getContentRaw().equalsIgnoreCase("hi")) {
            event.getChannel().sendMessage("Ку").queue();
        }
        else if (event.getMessage().getContentRaw().equalsIgnoreCase("хто я?")){
            String[] usr = new String[5];

            User user = event.getAuthor();

            String username = user.getName();

            event.getChannel().sendMessage("Ти " + username).queue();



        }
    }
    @Override
    public void onMessageDelete(MessageDeleteEvent ev){
        TextChannel channel = ev.getGuild().getTextChannels().stream()
                .filter(textChannel -> textChannel.getName().equals("del_msg_log"))
                .findFirst()
                .orElse(null);


        channel.sendMessage("Хтось видалив повідомлення").queue();

    }

    @Override
    public void onGuildJoin(GuildJoinEvent event){
        Addons addons = new Addons(event.getJDA());
        Sqlite sql = new Sqlite();
        sql.conn();

        Guild gld = event.getGuild();
        String gld_name = gld.getName();
        String gld_id = gld.getId();





    }

    @Override
    public void onGuildLeave(GuildLeaveEvent event){
        Sqlite sql = new Sqlite();
        Guild gld = event.getGuild();
        String gld_name = gld.getName();
        String gld_id = gld.getId();


    }

}
