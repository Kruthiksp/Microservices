package com.kruthik.petistan.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kruthik.petistan.dto.MailDTO;
import com.kruthik.petistan.service.MailService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/mail")
@RequiredArgsConstructor
public class MailController {

	private final MailService mailService;

	@PostMapping("/send")
	public ResponseEntity<String> sendEmail(@RequestBody MailDTO mailDTO) {
		String response = mailService.sendEmail(mailDTO);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
}
