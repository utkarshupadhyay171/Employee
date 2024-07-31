package com.cts.emp.Employe.Jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.emp.Employe.entity.Addresss;


@Repository
public interface AddresssRepository extends JpaRepository<Addresss,Integer> {
	Addresss findByState(String state);

}
