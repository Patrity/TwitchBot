package sql.impl;

import bot.Bot;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;

/*
 * @project TwitchBot
 * @author Patrity - https://github.com/Patrity
 * Created on - 1/14/2021
 */
public class Logger {

    @SneakyThrows
    public static void insert(int responseCode, String endpoint) {
        try (Connection con = Bot.db.get()) {
            con.setAutoCommit(false);
            PreparedStatement st = con.prepareStatement("INSERT INTO logs (response_code, endpoint, timestamp) VALUES (?,?,?)");
            st.setInt(1, responseCode);
            st.setString(2, endpoint);
            st.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
            st.execute();
            con.commit();
        }
    }
}
