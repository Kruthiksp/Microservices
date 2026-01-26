package com.kruthik.petistan.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kruthik.petistan.dto.PetStatisticsDTO;
import com.kruthik.petistan.service.PetService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/pet")
@RequiredArgsConstructor
public class PetController {

	private final PetService petService;

	@GetMapping("/stats")
	public ResponseEntity<PetStatisticsDTO> getStatistics() {
		PetStatisticsDTO petStatistics = petService.getStatistics();
		return ResponseEntity.status(HttpStatus.OK).body(petStatistics);
	}
}
