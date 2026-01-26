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

	DomesticPetDTO entityToDto(DomesticPet domesticPet);

	WildPetDTO entityToDto(WildPet domestiwildPet);

	default PetDTO entityToDto(Pet pet) {
		return switch (pet) {
		case DomesticPet domesticPet -> entityToDto(domesticPet);
		case WildPet wildPet -> entityToDto(wildPet);

		default -> throw new IllegalArgumentException();
		};
	}

	DomesticPet dtoToEntity(DomesticPetDTO domesticPetDTO);

	WildPet dtoToEntity(WildPetDTO wildPetDTO);

	default Pet dtoToEntity(PetDTO petDTO) {
		return switch (petDTO) {
		case DomesticPetDTO domesticPetDTO -> dtoToEntity(domesticPetDTO);
		case WildPetDTO wildPetDTO -> dtoToEntity(wildPetDTO);

		default -> throw new IllegalArgumentException();
		};
	}

}
