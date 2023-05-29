package com.example.dto;

import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.data.annotation.Transient;
import org.springframework.validation.annotation.Validated;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class PersonDto {
    @NotBlank
    private String username;

    private String password;

    private String confirmPassword;



}
