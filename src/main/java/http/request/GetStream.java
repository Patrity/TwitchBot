package http.request;

import bot.Bot;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import model.StreamInfo;
import sql.impl.Logger;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Timer;
import java.util.TimerTask;

/*
 * @project TwitchBot
 * @author Patrity - https://github.com/Patrity
 * Created on - 1/16/2021
 */
public class GetStream {

    public static StreamInfo byUserId(String userId)  {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.twitch.tv/helix/streams?user_id=" + userId))
                    .header("client-id", Bot.config.getProperty("TTV_CLIENT"))
                    .header("Authorization", "Bearer " + Bot.config.getProperty("TTV_ACCESSTOKEN"))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            Logger.insert(response.statusCode(), "streams");

            System.out.println(response.body());

            JsonParser parser = new JsonParser();
            JsonObject element = (JsonObject) parser.parse(response.body());
            JsonArray array = element.getAsJsonArray("data");
            JsonObject nestedArray = array.get(0).getAsJsonObject();
            String viewers = nestedArray.get("viewer_count").getAsString();
            String gameName = nestedArray.get("game_name").getAsString();
            String thumbnail = nestedArray.get("thumbnail_url").getAsString().replace("{width}", "600").replace("{height}", "400");

            return new StreamInfo(viewers, gameName, thumbnail);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
