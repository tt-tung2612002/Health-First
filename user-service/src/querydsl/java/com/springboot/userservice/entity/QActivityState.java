package com.springboot.userservice.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QActivityState is a Querydsl query type for ActivityState
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QActivityState extends EntityPathBase<ActivityState> {

    private static final long serialVersionUID = 12367709L;

    public static final QActivityState activityState = new QActivityState("activityState");

    public final SetPath<Activity, QActivity> activities = this.<Activity, QActivity>createSet("activities", Activity.class, QActivity.class, PathInits.DIRECT2);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath name = createString("name");

    public QActivityState(String variable) {
        super(ActivityState.class, forVariable(variable));
    }

    public QActivityState(Path<? extends ActivityState> path) {
        super(path.getType(), path.getMetadata());
    }

    public QActivityState(PathMetadata metadata) {
        super(ActivityState.class, metadata);
    }

}

