package com.example.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class PersonDto {

    private String username;

    private String password;


    private String confirmPassword;



}
