package com.kruthik.petistan.service;

import com.kruthik.petistan.dto.MailDTO;

public interface MailService {
	String sendEmail(MailDTO mailDTO);
}
