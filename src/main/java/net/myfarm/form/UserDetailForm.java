package net.myfarm.form;

import lombok.Data;
import net.myfarm.domain.user.model.Department;
import net.myfarm.domain.user.model.Salary;

import java.util.Date;
import java.util.List;

@Data
public class UserDetailForm {
    private String userId;
    private String password;
    private String userName;
    private Date birthday;
    private Integer age;
    private Integer gender;
    private Department department;
    private List<Salary> salaryList;
}
