package com.springboot.userservice.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSampleState is a Querydsl query type for SampleState
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSampleState extends EntityPathBase<SampleState> {

    private static final long serialVersionUID = 1922041954L;

    public static final QSampleState sampleState = new QSampleState("sampleState");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath name = createString("name");

    public final SetPath<Sample, QSample> samples = this.<Sample, QSample>createSet("samples", Sample.class, QSample.class, PathInits.DIRECT2);

    public QSampleState(String variable) {
        super(SampleState.class, forVariable(variable));
    }

    public QSampleState(Path<? extends SampleState> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSampleState(PathMetadata metadata) {
        super(SampleState.class, metadata);
    }

}

