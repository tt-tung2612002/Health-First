package com.springboot.userservice.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPlanState is a Querydsl query type for PlanState
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPlanState extends EntityPathBase<PlanState> {

    private static final long serialVersionUID = 734635267L;

    public static final QPlanState planState = new QPlanState("planState");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath name = createString("name");

    public final SetPath<Plan, QPlan> plans = this.<Plan, QPlan>createSet("plans", Plan.class, QPlan.class, PathInits.DIRECT2);

    public QPlanState(String variable) {
        super(PlanState.class, forVariable(variable));
    }

    public QPlanState(Path<? extends PlanState> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPlanState(PathMetadata metadata) {
        super(PlanState.class, metadata);
    }

}

