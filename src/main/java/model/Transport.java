package model;

import bot.Bot;
import lombok.RequiredArgsConstructor;

/*
 * @project TwitchBot
 * @author Patrity - https://github.com/Patrity
 * Created on - 1/12/2021
 */

public class Transport {
    public Transport (String method, String callback, String secret) {
        this.method = method;
        this.callback = callback;
        this.secret = secret;
    }
    private String method;
    private String callback;
    private String secret;
}
