package models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourierLoginModel {
    public String login;
    public String password;


    public CourierLoginModel(String login, String password) {
        this.login = login;
        this.password = password;
    }
}