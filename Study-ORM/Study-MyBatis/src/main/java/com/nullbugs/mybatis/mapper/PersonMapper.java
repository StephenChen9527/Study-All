package com.nullbugs.mybatis.mapper;

import com.nullbugs.mybatis.pojo.Person;

public interface PersonMapper {

    Person selectPerson(int id);
    int savePerson(Person person);
}
