package com.student.dto;

import lombok.Builder;
import lombok.Data;


import javax.validation.constraints.NotNull;


@Data
@Builder
public class StudentDto {

    @NotNull(message = "FirstName must not be null or blank")
    private String firstName;

    @NotNull(message = "lastName must not be null or blank")
    private String lastName;

    @NotNull(message = "email must not be null or blank")
    private String email;

    @NotNull(message = "phoneNumber must not be null or blank")
    private String phoneNumber;

    @NotNull(message = "password must not be null or blank")
    private String password;

}
