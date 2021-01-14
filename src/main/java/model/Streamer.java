package model;

import lombok.AllArgsConstructor;
import lombok.Getter;

/*
 * @project TwitchBot
 * @author Patrity - https://github.com/Patrity
 * Created on - 1/11/2021
 */

@AllArgsConstructor
public class Streamer {

    @Getter

    public String discordId, twitchId, twitchUsername, guildId;

}
