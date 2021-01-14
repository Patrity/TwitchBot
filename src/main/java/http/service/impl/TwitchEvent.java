package http.service.impl;

import bot.Bot;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.javalin.http.Context;
import model.Streamer;
import model.Subscribe;
import sql.impl.StreamerUtils;

import java.util.List;

/*
 * @project TwitchBot
 * @author Patrity - https://github.com/Patrity
 * Created on - 1/11/2021
 */
public class TwitchEvent {

    public static void live(Context request) {

        //parse the request into JSON
        JsonParser parser = new JsonParser();
        JsonObject jsonRequest = (JsonObject) parser.parse(request.body());

        //grab event object from request
        JsonObject event = jsonRequest.getAsJsonObject("event");

        //get twitch ID of the streamer
        String streamerId = event.getAsJsonPrimitive("broadcaster_user_id").toString();

        List<Streamer> streamers = StreamerUtils.getStreamerByTwitchId(streamerId);
        streamers.stream().forEach( streamer -> {

                    //TODO: Send Messages, Change roles

                    String guildId = streamer.getGuildId();
                    String discordId = streamer.getDiscordId();

            //Subscribe test = new Subscribe();
                });
    }
}
