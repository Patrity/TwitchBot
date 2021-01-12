package http.service;

import bot.Bot;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import http.service.impl.ChallengeConfirmation;

/*
 * @project TwitchBot
 * @author Patrity - https://github.com/Patrity
 * Created on - 1/10/2021
 */
public class Api {

    public static void callback() {
        Bot.SINGLETON.api.post("/callback", request -> {
            try {

                ChallengeConfirmation.confirm(request);

            } catch (Exception ignored) {

                //TODO: Discord stuff from here, handle role switches and announcements

            }
        });
    }

}
