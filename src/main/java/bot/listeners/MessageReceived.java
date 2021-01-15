package bot.listeners;

import bot.command.Commands;
import bot.util.Embeds;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.Objects;

/*
 * @project TwitchBot
 * @author Patrity - https://github.com/Patrity
 * Created on - 1/9/2021
 */
public class MessageReceived extends ListenerAdapter {

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {

        if (e.getAuthor().isBot() || !e.getChannel().canTalk()) {
            return;
        }

        Commands commands = Commands.isCommand(e);

        if (Objects.isNull(commands))
            return;


        commands.getAdapter().onGuildMessageReceived(e);

    }
}
