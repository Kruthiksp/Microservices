package com.kruthik.petistan.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.kruthik.petistan.dto.MailDTO;

@FeignClient(name = "petistan-mail-microservice")
public interface MailClient {

	@PostMapping("/mail/send")
	String sendMail(@RequestBody MailDTO mailDTO);

}
