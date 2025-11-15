package models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourierModel {
    public String login;
    public String password;
    public String firstName;


    public CourierModel(String login, String password, String firstName) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
    }



}