package com.xiaofeng.person.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xiaofeng.person.domain.Person;

public interface PersonRepository extends JpaRepository<Person, Long>{

}
