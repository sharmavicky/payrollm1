package com.cts.payroll.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cts.payroll.bean.User;
@Repository
public interface UserRepository  extends CrudRepository<User,Integer>{
	
	User findByUserName(String userName);

}
