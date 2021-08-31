package net.myfarm.domain.user.model;

import lombok.Data;

import javax.persistence.Embeddable;

@Embeddable
@Data
public class SalaryKey {
    private String userId;
    private String yearMonth;
}
