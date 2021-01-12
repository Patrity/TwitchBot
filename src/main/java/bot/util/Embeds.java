package bot.util;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.awt.*;

/*
 * @project TwitchBot
 * @author Patrity - https://github.com/Patrity
 * Created on - 1/9/2021
 */
public class Embeds {

    public static void error(GuildMessageReceivedEvent event, String message) {
        EmbedBuilder eb = new EmbedBuilder();
        eb.setColor(Color.RED);
        eb.setTitle("Error");
        eb.setDescription(message);
        event.getChannel().sendMessage(eb.build()).queue();
    }
    public static void success(GuildMessageReceivedEvent event, String message) {
        EmbedBuilder eb = new EmbedBuilder();
        eb.setColor(Color.GREEN);
        eb.setTitle("Success!");
        eb.setDescription(message);
        event.getChannel().sendMessage(eb.build()).queue();
    }
}
