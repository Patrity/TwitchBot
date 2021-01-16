package sql.impl;

import bot.Bot;
import lombok.SneakyThrows;
import model.Guild;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
 * @project TwitchBot
 * @author Patrity - https://github.com/Patrity
 * Created on - 1/9/2021
 */
public class GuildConfig {

    public static void addGuild(String guildId, String channelId, String streamerRoleId) throws SQLException {
        try (Connection con = Bot.db.get()) {
            con.setAutoCommit(false);
            PreparedStatement st = con.prepareStatement("INSERT INTO guilds (guild_id, channel_id, streamer_role_id, role_promotions) VALUES (?,?,?,0)");
            st.setString(1, guildId);
            st.setString(2, channelId);
            st.setString(3, streamerRoleId);
            st.execute();
            con.commit();
        }
    }

    public static void updateGuild(String guildId, String channelId, String streamerRoleId) throws SQLException {
        try (Connection con = Bot.db.get()) {
            con.setAutoCommit(false);
            PreparedStatement st = con.prepareStatement("UPDATE guilds SET channel_id = ? AND streamer_role_id = ? WHERE guild_id = ?");
            st.setString(1, channelId);
            st.setString(1, streamerRoleId);
            st.setString(3, guildId);
            st.execute();
            con.commit();
        }
    }

    //Verifies the guild's existence in the guilds table
    public static boolean guildIsConfigured(String guildId) throws SQLException {
        try(Connection con = Bot.db.get()) {
            con.setAutoCommit(false);
            PreparedStatement st = con.prepareStatement("SELECT * FROM guilds WHERE guild_id = ?");
            st.setString(1, guildId);
            ResultSet rs = st.executeQuery();
            con.commit();
            while (rs.next()) {
                return true;
            }
            return false;
        }
    }

    @SneakyThrows
    public static Guild getGuild(String guildId) {
        try (Connection con = Bot.db.get()) {
            con.setAutoCommit(false);
            PreparedStatement st = con.prepareStatement("SELECT * FROM guilds WHERE guild_id = ?");
            st.setString(1, guildId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return new Guild(guildId, rs.getString("channel_id"), rs.getString("streamer_role_id"), rs.getBoolean("role_promotions"));
            }

        }
        return null;
    }

}
