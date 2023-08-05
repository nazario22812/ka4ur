package utilts;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;

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
}
