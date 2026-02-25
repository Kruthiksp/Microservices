package com.kruthik.petistan.util;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import com.kruthik.petistan.dto.OwnerDTO;
import com.kruthik.petistan.entity.Owner;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OwnerMapper {

	@Mapping(target = "petDTO", ignore = true)
	OwnerDTO entityToDto(Owner owner);

	@Mapping(source = "petDTO.id", target = "petId")
	Owner dtoToEntity(OwnerDTO ownerDTO);

}
