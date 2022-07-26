package com.springboot.userservice.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPlan is a Querydsl query type for Plan
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPlan extends EntityPathBase<Plan> {

    private static final long serialVersionUID = 306093102L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPlan plan = new QPlan("plan");

    public final SetPath<Activity, QActivity> activities = this.<Activity, QActivity>createSet("activities", Activity.class, QActivity.class, PathInits.DIRECT2);

    public final QAppUser createdUser;

    public final StringPath description = createString("description");

    public final SetPath<Facility, QFacility> facilities = this.<Facility, QFacility>createSet("facilities", Facility.class, QFacility.class, PathInits.DIRECT2);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath name = createString("name");

    public final QPlanState planState;

    public final DatePath<java.sql.Date> publishedDate = createDate("publishedDate", java.sql.Date.class);

    public QPlan(String variable) {
        this(Plan.class, forVariable(variable), INITS);
    }

    public QPlan(Path<? extends Plan> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPlan(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPlan(PathMetadata metadata, PathInits inits) {
        this(Plan.class, metadata, inits);
    }

    public QPlan(Class<? extends Plan> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.createdUser = inits.isInitialized("createdUser") ? new QAppUser(forProperty("createdUser")) : null;
        this.planState = inits.isInitialized("planState") ? new QPlanState(forProperty("planState")) : null;
    }

}

