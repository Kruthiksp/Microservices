package com.kruthik.petistan.util;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.kruthik.petistan.dto.OwnerDTO;
import com.kruthik.petistan.entity.Owner;

@Mapper(componentModel = "spring")
public interface OwnerMapper {

	@Mapping(target = "petDTO", ignore = true)
	OwnerDTO entityToDto(Owner owner);

	@Mapping(target = "petId", ignore = true)
	Owner dtoToEntity(OwnerDTO ownerDTO);

}
