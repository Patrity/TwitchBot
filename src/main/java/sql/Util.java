package sql;

import bot.Bot;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
 * @project TwitchBot
 * @author Patrity - https://github.com/Patrity
 * Created on - 1/9/2021
 */
public class Util {

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
}
