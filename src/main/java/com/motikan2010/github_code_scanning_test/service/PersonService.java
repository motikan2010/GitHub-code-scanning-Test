package com.motikan2010.github_code_scanning_test.service;

import com.motikan2010.github_code_scanning_test.entity.Person;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Service
public class PersonService {

    @PersistenceContext
    private EntityManager em;

    public List<Person> findPersonById(String name) {
        Query q = em.createNativeQuery("SELECT * FROM Person p WHERE p.name LIKE '%" + name + "%'", Person.class);
        List<Person> personList = q.getResultList();
        return personList;
    }

}
