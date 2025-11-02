package com.example.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class EmailDTO{

    private Long userId;
    private String emailTo;
    private String subject;
    private String text;

}
