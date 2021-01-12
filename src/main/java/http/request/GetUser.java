package http.request;

/*
 * @project TwitchBot
 * @author Patrity - https://github.com/Patrity
 * Created on - 1/9/2021
 */

import bot.Bot;
import com.google.gson.*;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class GetUser {

    public static String byLogin(String login)  {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .header("client-id", Bot.config.getProperty("TTV_CLIENT"))
                    .header("Authorization", "Bearer " + Bot.config.getProperty("TTV_ACCESSTOKEN"))
                    .uri(URI.create("https://api.twitch.tv/helix/users?login=" + login))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            JsonParser parser = new JsonParser();
            JsonObject element = (JsonObject) parser.parse(response.body());
            JsonArray array = element.getAsJsonArray("data");
            JsonObject nestedArray = array.get(0).getAsJsonObject();

            return nestedArray.get("id").getAsString();
        } catch (Exception e) {
            return "";
        }
    }

}
