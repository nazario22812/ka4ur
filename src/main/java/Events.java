import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.guild.GuildLeaveEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.message.MessageDeleteEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import sql.Sqlite;
import utilts.Addons;

public class Events extends ListenerAdapter {
    @Override
    public void onReady(ReadyEvent event){
        Sqlite sql = new Sqlite();
        sql.conn();




    }


    @Override
    public void onMessageReceived(MessageReceivedEvent event){
        Sqlite sql = new Sqlite();
        User user = event.getAuthor();


        // приймає повідомлення від чела і відсилає
        if(event.getMessage().getContentRaw().equalsIgnoreCase("hi")) {
            event.getChannel().sendMessage("Ку").queue();
        }
        else if (event.getMessage().getContentRaw().equalsIgnoreCase("хто я?")){
            String[] usr = new String[5];


            String username = user.getEffectiveName();

            event.getChannel().sendMessage("Ти " + username).queue();



        }
    }
    @Override
    public void onMessageDelete(MessageDeleteEvent ev){
       String name = ev.getMessageId();

    }

    @Override
    public void onGuildJoin(GuildJoinEvent event){
        Addons addons = new Addons(event.getJDA());
        Sqlite sql = new Sqlite();
        sql.conn();


        Guild gld = event.getGuild();

        String[] name = new String[5];
        String gldname = gld.getName();

        name[0] = gldname;
        System.out.println("" + name[0]);

        sql.gld_users(name[0]);

        gld.loadMembers().onSuccess(members -> {
            for(Member member: members){
                sql.insrtusers(name[0], member.getIdLong(), member.getEffectiveName());
            }
        });




    }

    @Override
    public void onGuildLeave(GuildLeaveEvent event){
        Sqlite sql = new Sqlite();
        Guild gld = event.getGuild();
        String gld_name = gld.getName();
        String gld_id = gld.getId();


    }

    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent event){
        Sqlite sql = new Sqlite();
        Guild gld = event.getGuild();

        String[] name = new String[5];
        String gldname = gld.getName();

        name[0] = gldname;

        gld.loadMembers().onSuccess(members -> {
            for(Member member: members){
                sql.checkusers(name[0], member.getIdLong(), member.getEffectiveName());
            }
        });
    }

}
