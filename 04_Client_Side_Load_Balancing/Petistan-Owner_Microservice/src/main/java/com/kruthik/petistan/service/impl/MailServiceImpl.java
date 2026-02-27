package com.kruthik.petistan.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.kruthik.petistan.dto.MailDTO;
import com.kruthik.petistan.service.MailService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {

	private final RestClient loadBalancedRestClient;
	@Value("${mail.service.base.url}")
	private String mailBaseURL;

	@Override
	public String sendEmail(MailDTO mailDTO) {
		ResponseEntity<String> response = loadBalancedRestClient.post().uri(mailBaseURL).body(mailDTO).retrieve()
				.toEntity(String.class);

		return response.getBody();
	}

}
