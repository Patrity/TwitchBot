package bot.command.impl;

import bot.Bot;
import bot.util.Embeds;
import lombok.SneakyThrows;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import sql.Util;
import sql.impl.GuildConfig;
import sql.impl.Streamer;

/*
 * @project TwitchBot
 * @author Patrity - https://github.com/Patrity
 * Created on - 1/10/2021
 */
public class RemoveStreamer extends ListenerAdapter {

    @SneakyThrows
    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        if (!GuildConfig.guildIsConfigured(event.getGuild().getId())) {
            Embeds.error(event, "Your guild has not yet been configured, please use " + Bot.config.getProperty("PREFIX") + "config to begin.");
            return;
        }

        String[] command = event.getMessage().getContentRaw().split(" ");

        //Verify the command is input correctly
        if (command.length != 2) {
            Embeds.error(event, "Correct Syntax: `" + Bot.config.getProperty("PREFIX") + "removestreamer @user`");
            return;
        }

        //Confirms that the user input contains a mentioned user
        User target;
        try{
            target = event.getMessage().getMentionedUsers().get(0);
        } catch (Exception ignored) {
            Embeds.error(event, "Please mention a user! Correct Syntax: `" + Bot.config.getProperty("PREFIX") + "removestreamer @user`");
            return;
        }

        //Checks if the user is watched yet
        if(!Streamer.isStreamer(target.getId(), event.getGuild().getId())) {
            Embeds.error(event, "This user has not been configured as a streamer, please use `" + Bot.config.getProperty("PREFIX") + "addstreamer @user twitch_user_name`");
            return;
        }

        Streamer.removeStreamer(target.getId());
        Embeds.success(event, "You will no longer receive announcements if "+target.getAsMention() + " goes live.");

    }

}
