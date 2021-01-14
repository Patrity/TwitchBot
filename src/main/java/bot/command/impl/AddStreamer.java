package bot.command.impl;

import bot.Bot;
import bot.util.Embeds;
import http.request.GetUser;
import http.request.NewSubscription;
import lombok.SneakyThrows;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import sql.impl.GuildConfig;
import sql.impl.StreamerUtils;

/*
 * @project TwitchBot
 * @author Patrity - https://github.com/Patrity
 * Created on - 1/9/2021
 */
public class AddStreamer extends ListenerAdapter {

    @SneakyThrows
    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        if (!GuildConfig.guildIsConfigured(event.getGuild().getId())) {
            Embeds.error(event, "Your guild has not yet been configured, please use " + Bot.config.getProperty("PREFIX") + "config to begin.");
            return;
        }

        String[] command = event.getMessage().getContentRaw().split(" ");

        //Verify the command is input correctly
        if (command.length != 3) {
            Embeds.error(event, "Correct Syntax: `" + Bot.config.getProperty("PREFIX") + "addstreamer @user twitch_user_name`");
            return;
        }

        //Confirms that the user input contains a mentioned user
        User target;
        try{
            target = event.getMessage().getMentionedUsers().get(0);
        } catch (Exception ignored) {
            Embeds.error(event, "Please mention a user! Correct Syntax: `" + Bot.config.getProperty("PREFIX") + "addstreamer @user twitch_user_name`");
            return;
        }



        String twitchUsername = command[2];

        if(twitchUsername.startsWith("<@")){
            Embeds.error(event, "Correct Syntax: `" + Bot.config.getProperty("PREFIX") + "addstreamer @user twitch_user_name`");
            return;
        }

        //Confirms that the supplied twitch username exists
        String twitchId = GetUser.byLogin(twitchUsername);
        if (twitchId.isEmpty()) {
            Embeds.error(event, "Twitch username: `" + command[2] + "` not found!");
            return;
        }

        NewSubscription.sub(twitchId);
        StreamerUtils.addStreamer(target.getId(), twitchId, event.getGuild().getId());
        Embeds.success(event, target.getAsMention() + " Is now added a streamer with the username: " + twitchUsername);
    }
}
