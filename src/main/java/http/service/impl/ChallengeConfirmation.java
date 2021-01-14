package http.service.impl;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.javalin.http.Context;

/*
 * @project TwitchBot
 * @author Patrity - https://github.com/Patrity
 * Created on - 1/10/2021
 */
public class ChallengeConfirmation {

    public static void confirm(Context request) {

        //parse the request into JSON
        JsonParser parser = new JsonParser();
        JsonObject jsonRequest = (JsonObject) parser.parse(request.body());

        System.out.println(jsonRequest.toString());

        //extract the challenge string
        String challenge = jsonRequest.get("challenge").getAsString();

        //return the challenge string to verify url ownership
        request.result(challenge);
        System.out.println(challenge);

    }
}
