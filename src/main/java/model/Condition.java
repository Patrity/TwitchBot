package model;

import lombok.RequiredArgsConstructor;

/*
 * @project TwitchBot
 * @author Patrity - https://github.com/Patrity
 * Created on - 1/12/2021
 */

public class Condition {
    public Condition (String broadcaster_user_id) {
        this.broadcaster_user_id = broadcaster_user_id;
    }
    private String broadcaster_user_id;
}
