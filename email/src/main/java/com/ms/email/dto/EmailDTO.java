package com.ms.email.dto;

public record EmailDTO(
        Long userId,
        String emailTo,
        String subject,
        String text
) {
}
