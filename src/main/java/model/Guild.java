package model;

/*
 * @project TwitchBot
 * @author Patrity - https://github.com/Patrity
 * Created on - 1/11/2021
 */

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class Guild {

    @Getter
    public String guildId, channelId, streamerRoleId;

    @Getter
    public boolean rolePromotion;

}
