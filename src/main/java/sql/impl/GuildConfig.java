package sql.impl;

import bot.Bot;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/*
 * @project TwitchBot
 * @author Patrity - https://github.com/Patrity
 * Created on - 1/9/2021
 */
public class GuildConfig {

    public static void addGuild(String guildId, String channelId) throws SQLException {
        try (Connection con = Bot.db.get()) {
            con.setAutoCommit(false);
            PreparedStatement st = con.prepareStatement("INSERT INTO guilds (guild_id, channel_id) VALUES (?,?)");
            st.setString(1, guildId);
            st.setString(2, channelId);
            st.execute();
            con.commit();
        }
    }

    public static void updateGuild(String guildId, String channelId) throws SQLException {
        try (Connection con = Bot.db.get()) {
            con.setAutoCommit(false);
            PreparedStatement st = con.prepareStatement("UPDATE guilds SET channel_id = ? WHERE guild_id = ?");
            st.setString(1, channelId);
            st.setString(2, guildId);
            st.execute();
            con.commit();
        }
    }
}
