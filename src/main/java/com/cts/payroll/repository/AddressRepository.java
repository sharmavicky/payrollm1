package com.cts.payroll.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cts.payroll.bean.Address;
@Repository
public interface AddressRepository extends  CrudRepository<Address, Integer> {

}
