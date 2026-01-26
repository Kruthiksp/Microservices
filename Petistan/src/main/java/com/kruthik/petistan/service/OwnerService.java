package com.kruthik.petistan.service;

import java.util.List;

import com.kruthik.petistan.dto.OwnerDTO;
import com.kruthik.petistan.exception.OwnerAlreadyExistsException;
import com.kruthik.petistan.exception.OwnerNotFoundException;

public interface OwnerService {

	Integer saveOwner(OwnerDTO ownerDTO) throws OwnerAlreadyExistsException;

	OwnerDTO findByid(int ownerId) throws OwnerNotFoundException;

	void deleteOwnerById(int ownerId) throws OwnerNotFoundException;

	List<OwnerDTO> findAll(int pageNumber, int pageSize, String sortBy, boolean descending);

	void updatePetName(int ownerId, String petName);

}
