package com.springboot;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.querydsl.core.support.QueryBase;
import com.querydsl.jpa.impl.JPAQuery;
import com.springboot.userservice.entity.QAppUser;

@Component
public class CodeRunner implements CommandLineRunner {

    @PersistenceContext
    private EntityManager entityManager;

    // @Autowired
    // private UserService userService;

    @Override
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void run(String... args) throws Exception {

        JPAQuery query = new JPAQuery(entityManager);

        QueryBase result = query.from(QAppUser.appUser).where(QAppUser.appUser.username.eq("tung00deptrai"));

        System.out.println(result.toString());

    }

}