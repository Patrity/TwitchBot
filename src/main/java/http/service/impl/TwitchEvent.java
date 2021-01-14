package http.service.impl;

import bot.Bot;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.javalin.http.Context;
import model.Streamer;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import sql.impl.GuildConfig;
import sql.impl.StreamerUtils;

import java.awt.*;
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

        System.out.println(jsonRequest.toString());

        //grab event object from request
        JsonObject event = jsonRequest.getAsJsonObject("event");

        //get twitch ID of the streamer
        String streamerId = event.getAsJsonPrimitive("broadcaster_user_id").getAsString();

        EmbedBuilder eb = new EmbedBuilder();
        eb.setColor(Color.GREEN);


        List<Streamer> streamers = StreamerUtils.getStreamerByTwitchId(streamerId);
        streamers.stream().forEach( streamer -> {


            try {
                String guildId = streamer.getGuildId();
                String discordId = streamer.getDiscordId();
                String channelId = GuildConfig.getGuild(guildId).getChannelId();

                Bot.SINGLETON.jda.getGuildById(guildId).retrieveMemberById(discordId).queue(member -> {
                    eb.setTitle(member.getEffectiveName() + " is Now Live!");
                    eb.setDescription(member.getAsMention() + " has just gone live! Check out the stream! https://twitch.tv/" + streamer.getTwitchUsername());
                    eb.setThumbnail(member.getUser().getAvatarUrl());
                    Bot.SINGLETON.jda.getGuildById(guildId).getTextChannelById(channelId).sendMessage(eb.build()).queue();
                        });
            } catch (Exception ex) {
                ex.printStackTrace();
            }

                });
    }
}
