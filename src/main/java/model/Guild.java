package model;

/*
 * @project TwitchBot
 * @author Patrity - https://github.com/Patrity
 * Created on - 1/11/2021
 */

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class Guild {

    @Setter
    @Getter
    public String guildId, channelId, streamerRoleId;

}
