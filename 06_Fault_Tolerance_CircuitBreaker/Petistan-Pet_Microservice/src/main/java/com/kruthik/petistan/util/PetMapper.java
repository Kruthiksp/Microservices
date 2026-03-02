package com.kruthik.petistan.util;

import org.mapstruct.Mapper;

import com.kruthik.petistan.dto.DomesticPetDTO;
import com.kruthik.petistan.dto.PetDTO;
import com.kruthik.petistan.dto.WildPetDTO;
import com.kruthik.petistan.entity.DomesticPet;
import com.kruthik.petistan.entity.Pet;
import com.kruthik.petistan.entity.WildPet;

@Mapper(componentModel = "spring")
public interface PetMapper {

	DomesticPet dtoToEntity(DomesticPetDTO domesticPetDTO);

	WildPet dtoToEntity(WildPetDTO wildPetDTO);

	default Pet dtoToEntity(PetDTO petDTO) {
		if (petDTO instanceof DomesticPetDTO domesticPetDTO)
			return dtoToEntity(domesticPetDTO);
		if (petDTO instanceof WildPetDTO wildPetDTO)
			return dtoToEntity(wildPetDTO);
		return null;
	}

	DomesticPetDTO entityToDto(DomesticPet domesticPet);

	WildPetDTO entityToDto(WildPet wildPet);

	default PetDTO entityToDto(Pet pet) {
		if (pet instanceof DomesticPet domesticPet)
			return entityToDto(domesticPet);
		if (pet instanceof WildPet wildPet)
			return entityToDto(wildPet);
		return null;
	}
}
