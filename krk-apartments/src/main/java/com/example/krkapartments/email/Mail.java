package com.example.krkapartments.email;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Mail {

    private String toEmail;
    private String subject;
    private String body;
    private boolean isHtmlContent;
}
