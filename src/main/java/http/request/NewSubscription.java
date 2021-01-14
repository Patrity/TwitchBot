package http.request;

import bot.Bot;
import com.google.gson.Gson;
import http.service.Api;
import model.Condition;
import model.Subscribe;
import model.Transport;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/*
 * @project TwitchBot
 * @author Patrity - https://github.com/Patrity
 * Created on - 1/12/2021
 */
public class NewSubscription {
    public static void sub(String userId) throws IOException, InterruptedException {

        Condition con = new Condition(userId);

        Transport transport = new Transport(
                "webhook",
                "https://" + Bot.config.getProperty("CALLBACK_URL") + Api.path,
                Bot.config.getProperty("TTV_SECRET"));

        Subscribe sub = new Subscribe("stream.online", "1", con, transport);

        Gson gson = new Gson();

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.twitch.tv/helix/eventsub/subscriptions"))
                .POST(HttpRequest.BodyPublishers.ofString(gson.toJson(sub)))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
    }
}
