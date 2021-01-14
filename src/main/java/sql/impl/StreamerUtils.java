package sql.impl;

import bot.Bot;
import lombok.SneakyThrows;
import model.Streamer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*
 * @project TwitchBot
 * @author Patrity - https://github.com/Patrity
 * Created on - 1/9/2021
 */
public class StreamerUtils {

    @SneakyThrows
    public static boolean isStreamer(String discordId, String guildId) {
        try (Connection con = Bot.db.get()) {
            con.setAutoCommit(false);
            PreparedStatement st = con.prepareStatement("SELECT * FROM streamers WHERE discord_id = ? AND guild_id = ?");
            st.setString(1, discordId);
            st.setString(1, guildId);
            ResultSet rs = st.executeQuery();
            con.commit();
            while (rs.next()) {
                return true;
            }
            return false;
        }
    }

    public static void addStreamer(String discordId, String twitchId, String guildId) throws SQLException {
        if (isStreamer(discordId, guildId)) {
            modfifyStreamer(discordId, twitchId, guildId);
        } else {
            try (Connection con = Bot.db.get()) {
                con.setAutoCommit(false);
                PreparedStatement st = con.prepareStatement("INSERT INTO streamers (discord_id, twitch_id, guild_id, enabled) VALUES (?, ?, ?, ?)");
                st.setString(1, discordId);
                st.setString(2, twitchId);
                st.setString(3, guildId);
                st.setInt(4, 1);
                st.execute();
                con.commit();
            }
        }
    }

    @SneakyThrows
    private static void modfifyStreamer(String discordId, String twitchId, String guildId) {
        try (Connection con = Bot.db.get()) {
            con.setAutoCommit(false);
            PreparedStatement st = con.prepareStatement("UPDATE streamers set enabled = 1, twitch_id = ? WHERE discord_id = ? AND guild_id = ?");
            st.setString(1, twitchId);
            st.setString(2, discordId);
            st.setString(3, guildId);
        }
    }

    public static void removeStreamer(String discordId) throws SQLException {
        try (Connection con = Bot.db.get()) {
            con.setAutoCommit(false);
            PreparedStatement st = con.prepareStatement("UPDATE streamers SET enabled = 0 WHERE discord_id = ?");
            st.setString(1, discordId);
            st.execute();
            con.commit();
        }
    }

    @SneakyThrows
    public static List<Streamer> getStreamerByTwitchId(String twitchId) {
        List<Streamer> streamers = new ArrayList<>();
        try (Connection con = Bot.db.get()) {
            con.setAutoCommit(false);
            PreparedStatement st = con.prepareStatement("SELECT discord_id, guild_id FROM streamers WHERE twitch_id = ? AND enabled = 1");
            st.setString(1, twitchId);
            ResultSet rs = st.executeQuery();
            con.commit();
            while (rs.next()) {
                streamers.add(new Streamer(rs.getString("discord_id"), twitchId, rs.getString("guild_id")));
            }
        }
        return streamers;
    }


}
