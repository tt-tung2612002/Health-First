package com.springboot;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.springboot.userservice.entity.QAppUser;

@Component
public class CodeRunner implements CommandLineRunner {

    @PersistenceContext
    private EntityManager entityManager;

    // @Autowired
    // private UserService userService;

    @Override
    public void run(String... args) throws Exception {

        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
        QAppUser user = QAppUser.appUser;
        var ans = queryFactory.select(user).from(user).where(user.username.eq("tt-tung261")).fetch();

        System.out.println(ans.get(0).getDisplayName());

    }

}