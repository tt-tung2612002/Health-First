package com.springboot.userservice.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QActivityResult is a Querydsl query type for ActivityResult
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QActivityResult extends EntityPathBase<ActivityResult> {

    private static final long serialVersionUID = 341454545L;

    public static final QActivityResult activityResult = new QActivityResult("activityResult");

    public final SetPath<Activity, QActivity> activities = this.<Activity, QActivity>createSet("activities", Activity.class, QActivity.class, PathInits.DIRECT2);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath name = createString("name");

    public QActivityResult(String variable) {
        super(ActivityResult.class, forVariable(variable));
    }

    public QActivityResult(Path<? extends ActivityResult> path) {
        super(path.getType(), path.getMetadata());
    }

    public QActivityResult(PathMetadata metadata) {
        super(ActivityResult.class, metadata);
    }

}

