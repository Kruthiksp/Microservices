package com.kruthik.petistan.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kruthik.petistan.dto.OwnerDTO;
import com.kruthik.petistan.dto.UpdatePetDTO;
import com.kruthik.petistan.service.OwnerService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/owner")
@RequiredArgsConstructor
public class OwnerController {

	private final OwnerService ownerService;

	@PostMapping
	public ResponseEntity<String> saveOwner(@RequestBody OwnerDTO ownerDTO) {
		Integer ownerId = ownerService.saveOwner(ownerDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body("Owner created with the Owner ID: " + ownerId);
	}

	@GetMapping("/{ownerId}")
	public ResponseEntity<OwnerDTO> findById(@PathVariable int ownerId) {
		OwnerDTO ownerDTO = ownerService.findByid(ownerId);
		return ResponseEntity.status(HttpStatus.OK).body(ownerDTO);
	}
	
	@PatchMapping("/{ownerId}")
	public ResponseEntity<Void> updatePetName(@PathVariable int ownerId, @RequestBody UpdatePetDTO updatePetDTO) {
		ownerService.updatePetName(ownerId, updatePetDTO);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@DeleteMapping("/{ownerId}")
	public ResponseEntity<Void> deleteById(@PathVariable int ownerId) {
		ownerService.deleteOwnerById(ownerId);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@GetMapping
	public ResponseEntity<List<OwnerDTO>> findAllOwners(
			@RequestParam(defaultValue = "0") int pageNumber,
			@RequestParam(defaultValue = "10") int pageSize,
			@RequestParam(defaultValue = "id") String sortBy,
			@RequestParam(defaultValue = "false") boolean descending ) {
		List<OwnerDTO> ownersList = ownerService.findAll(pageNumber, pageSize, sortBy, descending);
		return ResponseEntity.status(HttpStatus.OK).body(ownersList);
	}
}




