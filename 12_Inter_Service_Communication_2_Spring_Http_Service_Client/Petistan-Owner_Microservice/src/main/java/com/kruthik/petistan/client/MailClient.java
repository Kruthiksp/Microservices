package com.kruthik.petistan.client;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

import com.kruthik.petistan.dto.MailDTO;

@HttpExchange("/mail")
public interface MailClient {

	@PostExchange("/send")
	String sendMail(@RequestBody MailDTO mailDTO);

}
