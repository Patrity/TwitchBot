package bot.command.impl;

import bot.Bot;
import bot.util.Embeds;
import lombok.SneakyThrows;
import net.dv8tion.jda.api.entities.GuildChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import sql.Util;
import sql.impl.GuildConfig;

/*
 * @project TwitchBot
 * @author Patrity - https://github.com/Patrity
 * Created on - 1/9/2021
 */
public class Config extends ListenerAdapter {

    @SneakyThrows
    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {

        String[] command = event.getMessage().getContentRaw().split(" ");

        //Verify the command is input correctly
        if (command.length != 2) {
            Embeds.error(event, "Correct Syntax: `" + Bot.config.getProperty("PREFIX") + "config #announcement_channel`");
            return;
        }

        //verify that a channel is mentioned
        if (event.getMessage().getMentionedChannels().isEmpty()) {
            Embeds.error(event, "Please properly mention the desired announcement channel using `#channel_name`");
            return;
        }

        GuildChannel announcementChannel  = event.getMessage().getMentionedChannels().get(0);

        //If an entry already exists, update it.
        if (GuildConfig.guildIsConfigured(event.getGuild().getId())) {
            GuildConfig.updateGuild(event.getGuild().getId(), announcementChannel.getId());
            Embeds.success(event, "Successfully updated " + announcementChannel.getName() + " as the announcement channel!");
            return;
        }
        GuildConfig.addGuild(event.getGuild().getId(), announcementChannel.getId());
        Embeds.success(event, "Successfully set " + announcementChannel.getName() + " as the announcement channel!");
    }
}
