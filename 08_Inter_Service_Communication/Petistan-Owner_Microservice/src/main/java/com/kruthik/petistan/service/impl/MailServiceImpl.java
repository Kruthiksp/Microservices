package com.kruthik.petistan.service.impl;

import org.springframework.stereotype.Service;

import com.kruthik.petistan.client.MailClient;
import com.kruthik.petistan.dto.MailDTO;
import com.kruthik.petistan.service.MailService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {

	private final MailClient mailClient;

	@Override
	public String sendEmail(MailDTO mailDTO) {
		return mailClient.sendMail(mailDTO);
	}

}
