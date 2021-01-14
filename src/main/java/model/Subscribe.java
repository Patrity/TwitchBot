package model;

import bot.Bot;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


/*
 * @project TwitchBot
 * @author Patrity - https://github.com/Patrity
 * Created on - 1/12/2021
 */

public class Subscribe {

    public Subscribe (String type, String version, Condition condition, Transport transport){
        this.type = type;
        this.version = version;
        this.condition = condition;
        this.transport = transport;

    }
    private String type;
    private String version;
    private Condition condition;
    private Transport transport;
}

