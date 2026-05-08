package com.kruthik.petistan.service.impl;

import org.springframework.stereotype.Service;

import com.kruthik.petistan.dto.MailDTO;
import com.kruthik.petistan.producers.MailProducer;
import com.kruthik.petistan.service.MailService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {

	private final MailProducer mailProducer;

	@Override
	public String sendEmail(MailDTO mailDTO) {
		return mailProducer.publishEvent(mailDTO);
	}

}
