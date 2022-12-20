package com.platform.commons.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.platform.commons.modle.Address;

@Repository
public interface AddressRepo extends JpaRepository<Address, Integer> {

}
