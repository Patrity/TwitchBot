package bot.listeners;

import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.user.UserActivityEndEvent;
import net.dv8tion.jda.api.events.user.UserActivityStartEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import sql.impl.GuildConfig;
import sql.impl.StreamerUtils;

/*
 * @project TwitchBot
 * @author Patrity - https://github.com/Patrity
 * Created on - 1/14/2021
 */
public class UserActivity extends ListenerAdapter {

    @Override
    public void onUserActivityStart(UserActivityStartEvent event) {

        if (event.getNewActivity().getType().name().equalsIgnoreCase("STREAMING")) {
            if (StreamerUtils.isStreamer(event.getUser().getId(), event.getGuild().getId()) || GuildConfig.getGuild(event.getGuild().getId()).isRolePromotion()) {
                try {
                    Role streamerRole = event.getGuild().getRoleById(GuildConfig.getGuild(event.getGuild().getId()).getStreamerRoleId());
                    event.getGuild().addRoleToMember(event.getUser().getId(), streamerRole).queue();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }

    }

    @Override
    public void onUserActivityEnd(UserActivityEndEvent event) {

        if (event.getOldActivity().getType().name().equalsIgnoreCase("STREAMING")) {
            if (StreamerUtils.isStreamer(event.getUser().getId(), event.getGuild().getId()) || GuildConfig.getGuild(event.getGuild().getId()).isRolePromotion()) {
                try {
                    Role streamerRole = event.getGuild().getRoleById(GuildConfig.getGuild(event.getGuild().getId()).getStreamerRoleId());
                    event.getGuild().removeRoleFromMember(event.getUser().getId(), streamerRole).queue();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }

    }
}
