package bot;

import bot.listeners.UserActivity;
import http.service.Api;
import io.javalin.Javalin;
import bot.listeners.MessageReceived;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import sql.Database;
import javax.security.auth.login.LoginException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/*
 * @project TwitchBot
 * @author Patrity - https://github.com/Patrity
 * Created on - 1/8/2021
 */

public class Bot {

    public static final Properties config = new Properties();
    public static Bot SINGLETON;
    public static Database db;
    public ShardManager jda;

    public Bot () throws IOException, LoginException {

        config.load(new FileReader("config.properties"));

        DefaultShardManagerBuilder builder = DefaultShardManagerBuilder.createDefault(config.getProperty("TOKEN"));
        builder.setMemberCachePolicy(MemberCachePolicy.ONLINE);
        builder.enableCache(CacheFlag.ACTIVITY);
        builder.setEnabledIntents(GatewayIntent.GUILD_PRESENCES, GatewayIntent.GUILD_VOICE_STATES, GatewayIntent.GUILD_EMOJIS, GatewayIntent.GUILD_MESSAGES, GatewayIntent.GUILD_MEMBERS);
        builder.addEventListeners(new MessageReceived());
        builder.addEventListeners(new UserActivity());
        jda = builder.build();

        db = new Database(config.getProperty("DB_URL"), config.getProperty("DB_USERNAME"), config.getProperty("DB_PASS"));
        db.connect();
        Bot.SINGLETON = this;

        api = Javalin.create().start(Integer.parseInt(config.getProperty("API_PORT")));
        Api.callback();

    }

    public Javalin api;

    public static void main(String[] args) throws Exception {
        new Bot();
    }

}
