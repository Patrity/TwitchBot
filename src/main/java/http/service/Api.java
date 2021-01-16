package http.service;

import bot.Bot;
import http.service.impl.ChallengeConfirmation;
import http.service.impl.TwitchEvent;

/*
 * @project TwitchBot
 * @author Patrity - https://github.com/Patrity
 * Created on - 1/10/2021
 */
public class Api {

    public static String path = "/callback";

    public static void callback() {

        Bot.SINGLETON.api.post(path, request -> {

            System.err.println("Request Received:");

            try {
                //Receives all requests with a challenge in order to confirm a new subscription
                ChallengeConfirmation.confirm(request);

            } catch (Exception ignored) {
                //All other requests routed to method which handles live notifications
                TwitchEvent.live(request);

            }
        });
    }

}
