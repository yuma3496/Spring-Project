package net.myprocon.myprocon.domain.user.model;

import lombok.Data;

import java.util.Date;

@Data
public class MUser {
    private String userId;
    private String password;
    private Date birthday;
    private Integer age;
    private Integer gender;
    private Integer departmentId;
    private String role;
}
