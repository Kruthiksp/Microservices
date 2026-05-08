package com.kruthik.petistan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kruthik.petistan.entity.Owner;

public interface OwnerRepository extends JpaRepository<Owner, Integer> {

	boolean existsByEmail(String email);

	boolean existsByMobile(long mobile);

}
