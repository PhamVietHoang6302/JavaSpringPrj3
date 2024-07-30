package com.javaweb.model.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDTO extends AbstractDTO{
    private String name;
    private String managementStaff;
    private String customerPhone;
    private String email;
    private String demand;
    private String status;
    private String companyName;

}
