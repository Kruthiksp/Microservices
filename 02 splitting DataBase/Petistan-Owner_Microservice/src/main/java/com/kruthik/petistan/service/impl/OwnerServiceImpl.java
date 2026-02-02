package com.kruthik.petistan.service.impl;

import static com.kruthik.petistan.enums.MailType.EXIT;
import static com.kruthik.petistan.enums.MailType.MODIFY;
import static com.kruthik.petistan.enums.MailType.WELCOME;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.kruthik.petistan.dto.MailDTO;
import com.kruthik.petistan.dto.OwnerDTO;
import com.kruthik.petistan.dto.PetDTO;
import com.kruthik.petistan.entity.Owner;
import com.kruthik.petistan.exception.OwnerAlreadyExistsException;
import com.kruthik.petistan.exception.OwnerNotFoundException;
import com.kruthik.petistan.repository.OwnerRepository;
import com.kruthik.petistan.service.MailService;
import com.kruthik.petistan.service.OwnerService;
import com.kruthik.petistan.service.PetService;
import com.kruthik.petistan.util.OwnerMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class OwnerServiceImpl implements OwnerService {

	private final OwnerRepository ownerRepository;
	private final OwnerMapper ownerMapper;
	private final MailService mailService;
	private final PetService petService;

	@Override
	public Integer saveOwner(OwnerDTO ownerDTO) throws OwnerAlreadyExistsException {

		Integer petId = petService.savePet(ownerDTO.getPetDTO());
		ownerDTO.getPetDTO().setId(petId);
		
		String email = ownerDTO.getEmail();
		long mobile = ownerDTO.getMobile();
		Owner owner;

		if (ownerRepository.existsByEmail(email) || ownerRepository.existsByMobile(mobile)) {
			throw new OwnerAlreadyExistsException("Owner already exists with same Email Id or Mobile Number");
		} else {
			Owner entity = ownerMapper.dtoToEntity(ownerDTO);
			owner = ownerRepository.save(entity);
			MailDTO mailDTO = new MailDTO(ownerDTO.getEmail(), ownerDTO.getFirstName(), ownerDTO.getLastName(),
					WELCOME);
			log.info(mailService.sendEmail(mailDTO));
		}

		return owner.getId();
	}

	@Override
	public OwnerDTO findByid(int ownerId) throws OwnerNotFoundException {
		return ownerRepository.findById(ownerId)
				.map(owner -> {
					PetDTO petDTO = petService.findById(owner.getPetId());
					OwnerDTO ownerDTO = ownerMapper.entityToDto(owner);
					ownerDTO.setPetDTO(petDTO);
					return ownerDTO;
				})
				.orElseThrow(() -> new OwnerNotFoundException("Owner not found with the id: " + ownerId));
	}

	@Override
	public void updatePetName(int ownerId, String petName) {
		Owner owner = ownerRepository.findById(ownerId)
				.map(o -> {
					Integer petId = o.getPetId();
					petService.updatePetName(petId, petName);
					return o;
				})
				.orElseThrow(() -> new OwnerNotFoundException("Owner Not Found with the Id: " + ownerId));

		MailDTO mailDTO = new MailDTO(owner.getEmail(), owner.getFirstName(), owner.getLastName(), MODIFY);
		log.info(mailService.sendEmail(mailDTO));
	}

	@Override
	public void deleteOwnerById(int ownerId) throws OwnerNotFoundException {
		Owner owner = ownerRepository.findById(ownerId)
				.map(o -> {
					petService.deleteById(o.getPetId());
					return o;
				})
				.orElseThrow(() -> new OwnerNotFoundException("Owner Not Found with the Id: " + ownerId));

		ownerRepository.deleteById(ownerId);

		MailDTO mailDTO = new MailDTO(owner.getEmail(), owner.getFirstName(), owner.getLastName(), EXIT);
		log.info(mailService.sendEmail(mailDTO));

	}

	@Override
	public List<OwnerDTO> findAll(int pageNumber, int pageSize, String sortBy, boolean descending) {
		Direction direction = descending ? Direction.DESC : Direction.ASC;
		Sort sort = Sort.by(direction, sortBy);
		Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

		return ownerRepository.findAll(pageable)
				.map(o -> {
					PetDTO petDTO = petService.findById(o.getPetId());
					OwnerDTO ownerDTO = ownerMapper.entityToDto(o);
					ownerDTO.setPetDTO(petDTO);
					return ownerDTO;
				})
				.getContent();
	}

}
