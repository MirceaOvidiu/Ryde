package com.example.ryde.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeByManagerDTO {
    private Long managerId;
    private String managerName;
    private String managerSurname;
    private Long employeeId;
    private String employeeName;
    private String employeeSurname;
}
