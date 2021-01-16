package model;

import lombok.AllArgsConstructor;
import lombok.Getter;

/*
 * @project TwitchBot
 * @author Patrity - https://github.com/Patrity
 * Created on - 1/16/2021
 */

@AllArgsConstructor
public class StreamInfo {

    @Getter
    public String viewers, gameName, thumbnail;
}
