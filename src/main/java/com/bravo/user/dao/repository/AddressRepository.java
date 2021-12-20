package com.bravo.user.dao.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bravo.user.dao.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, String> {

	Page<Address> findByUserId(final String userId, Pageable page);

}