package utilts;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.channel.concrete.VoiceChannel;
import net.dv8tion.jda.api.utils.concurrent.Task;

import java.util.List;

public class Addons {
    private final JDA jda; // Поле для хранения объекта JDA
    public Addons(JDA jda) {
        this.jda = jda;
    }


    //загружає всіх юзерів
    public void processGuildMembers() {
        for (Guild guild : jda.getGuilds()) {
            guild.loadMembers().onSuccess(members -> {
                for (Member member : members) {
                    System.out.println(member.getUser().getName());
                }
            });
        }
    }


    public int getGuildscount(){
        int Count = 0;

        for (Guild gld: jda.getGuilds()){
            Count++;
        }
        return Count;
    }

    public int getChannelscount(){
        int Count = 0;

        for (TextChannel gld: jda.getTextChannels() ){
            Count++;
        }

        for(VoiceChannel gld: jda.getVoiceChannels()){
            Count++;
        }
        return Count;
    }


    public int getMemberscount(){
        int count = 0;
        for (Guild guild : jda.getGuilds()) {
            count += guild.getMemberCount();
        }
        return count;
    }

    public long ping(){
        long lol;

        lol = jda.getGatewayPing();

        return lol;

    }
}
