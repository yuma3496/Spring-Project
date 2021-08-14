package net.myfarm.form;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.util.Date;

@Data
public class SignupForm {

    @NotBlank(groups = ValidGroup1.class)
    @Email(groups = ValidGruop2.class)
    private String userId;

    @NotBlank(groups = ValidGroup1.class)
    @Length(min = 4, max = 100, groups = ValidGruop2.class)
    @Pattern(regexp = "[a-zA-Z0-0]+$", groups = ValidGruop2.class)
    private String password;

    @NotBlank(groups = ValidGroup1.class)
    private String userName;

    @DateTimeFormat(pattern = "yyyy/MM/dd")
    @NotNull(groups = ValidGroup1.class)
    private Date birthday;

    @Min(value = 20, groups = ValidGruop2.class)
    @Max(value = 100, groups = ValidGroup1.class)
    private Integer age;

    @NotNull(groups = ValidGroup1.class)
    private Integer gender;
}
