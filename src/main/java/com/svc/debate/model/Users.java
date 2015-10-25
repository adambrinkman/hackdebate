package com.svc.debate.model;

import lombok.Data;

/**
 * Created by dsawla on 10/24/2015.
 */

@Data
public class Users {
    public int user_id;
    public String sid;
    public String email;
    public String user_name;
    public String first_name;
    public String last_name;
    public String password;
    public String role;
}
