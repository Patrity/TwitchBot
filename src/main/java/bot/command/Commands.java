package bot.command;

import bot.command.impl.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import bot.Bot;

/*
 * @project TwitchBot
 * @author Patrity - https://github.com/Patrity
 * Created on - 1/9/2021
 */

@RequiredArgsConstructor
@Getter
public enum Commands {

    PING("ping", "Returns Pong", new Ping()),
    ADDSTREAMER("addstreamer", "Adds a new streamer", new AddStreamer()),
    REMOVESTREAMER("removestreamer", "Disables alerts for a user", new RemoveStreamer()),
    ROLEPROMOTION("rolepromotion", "Updates when users should be promoted to the streamer role", new RolePromotion()),
    CONFIG("config", "Configures a guild", new Config());

    public static final Commands[] VALUES = Commands.values();
    private static String prefix = Bot.SINGLETON.config.getProperty("PREFIX");
    private final String command, description;
    private final ListenerAdapter adapter;

    public static Commands isCommand(GuildMessageReceivedEvent e) {
        String text = e.getMessage().getContentRaw().toLowerCase();
        for (Commands command : Commands.VALUES) {
            if (text.startsWith(prefix + command.getCommand().toLowerCase())) {
                return command;
            }
        }
        return null;
    }
}
