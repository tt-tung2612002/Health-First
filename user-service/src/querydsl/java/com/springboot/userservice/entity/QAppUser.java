package com.springboot.userservice.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAppUser is a Querydsl query type for AppUser
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAppUser extends EntityPathBase<AppUser> {

    private static final long serialVersionUID = 304117351L;

    public static final QAppUser appUser = new QAppUser("appUser");

    public final SetPath<Activity, QActivity> activities = this.<Activity, QActivity>createSet("activities", Activity.class, QActivity.class, PathInits.DIRECT2);

    public final DatePath<java.sql.Date> createdDate = createDate("createdDate", java.sql.Date.class);

    public final StringPath displayName = createString("displayName");

    public final StringPath email = createString("email");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath password = createString("password");

    public final StringPath phoneNumber = createString("phoneNumber");

    public final SetPath<Plan, QPlan> plans = this.<Plan, QPlan>createSet("plans", Plan.class, QPlan.class, PathInits.DIRECT2);

    public final SetPath<AppRole, QAppRole> roles = this.<AppRole, QAppRole>createSet("roles", AppRole.class, QAppRole.class, PathInits.DIRECT2);

    public final StringPath username = createString("username");

    public final SetPath<Ward, QWard> wards = this.<Ward, QWard>createSet("wards", Ward.class, QWard.class, PathInits.DIRECT2);

    public QAppUser(String variable) {
        super(AppUser.class, forVariable(variable));
    }

    public QAppUser(Path<? extends AppUser> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAppUser(PathMetadata metadata) {
        super(AppUser.class, metadata);
    }

}

