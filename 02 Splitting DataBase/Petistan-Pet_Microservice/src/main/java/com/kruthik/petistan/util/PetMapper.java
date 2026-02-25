package com.kruthik.petistan.util;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.kruthik.petistan.dto.DomesticPetDTO;
import com.kruthik.petistan.dto.PetDTO;
import com.kruthik.petistan.dto.WildPetDTO;
import com.kruthik.petistan.entity.DomesticPet;
import com.kruthik.petistan.entity.Pet;
import com.kruthik.petistan.entity.WildPet;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PetMapper {

	DomesticPet dtoToEntity(DomesticPetDTO petDTO);

	WildPet dtoToEntity(WildPetDTO petDTO);

	default Pet dtoToEntity(PetDTO petDTO) {

		if (petDTO instanceof DomesticPetDTO domesticPetDTO)
			return dtoToEntity(domesticPetDTO);
		if (petDTO instanceof WildPetDTO wildPetDTO)
			return dtoToEntity(wildPetDTO);

		throw new IllegalArgumentException("Unknown PetDTO type: " + petDTO.getClass());
	}

	DomesticPetDTO entityToDto(DomesticPet pet);

	WildPetDTO entityToDto(WildPet pet);

	default PetDTO entityToDto(Pet pet) {
		if (pet instanceof DomesticPet domesticPet)
			return entityToDto(domesticPet);
		if (pet instanceof WildPet wildPet)
			return entityToDto(wildPet);

		throw new IllegalArgumentException("Unknown PetDTO type: " + pet.getClass());
	}

}
