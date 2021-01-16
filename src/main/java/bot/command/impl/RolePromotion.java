package bot.command.impl;

import bot.util.Embeds;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import sql.impl.GuildConfig;

/*
 * @project TwitchBot
 * @author Patrity - https://github.com/Patrity
 * Created on - 1/16/2021
 */
public class RolePromotion extends ListenerAdapter {

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {

        if (!event.getMember().hasPermission(Permission.ADMINISTRATOR)) {
            Embeds.error(event, "Only Administrators may use this function!");
            return;
        }

        boolean rolePromotion = GuildConfig.getGuild(event.getGuild().getId()).isRolePromotion();

        GuildConfig.updateRolePromotion(event.getGuild().getId(), !rolePromotion);

        String state = rolePromotion ? "__All Users__" : "__Only Streamers__";
        Embeds.success(event, state + " will now have their roles assigned when going live!");


    }
}
