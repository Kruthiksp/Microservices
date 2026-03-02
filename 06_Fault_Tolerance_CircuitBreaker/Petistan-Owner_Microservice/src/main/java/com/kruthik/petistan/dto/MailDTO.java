package com.kruthik.petistan.dto;

import com.kruthik.petistan.enums.MailType;

public record MailDTO(String to, String firstName, String lastName, MailType category) {

}
