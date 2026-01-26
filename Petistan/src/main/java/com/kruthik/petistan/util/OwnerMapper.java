package com.kruthik.petistan.util;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.kruthik.petistan.dto.OwnerDTO;
import com.kruthik.petistan.entity.Owner;

@Mapper(componentModel = "spring", uses = PetMapper.class)
public interface OwnerMapper {

	@Mapping(source = "pet", target = "petDTO")
	OwnerDTO entityToDto(Owner owner);

	@Mapping(source = "petDTO", target = "pet")
	Owner dtoToEntity(OwnerDTO ownerDTO);

}
