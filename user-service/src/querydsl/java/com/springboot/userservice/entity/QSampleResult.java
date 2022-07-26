package com.springboot.userservice.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSampleResult is a Querydsl query type for SampleResult
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSampleResult extends EntityPathBase<SampleResult> {

    private static final long serialVersionUID = -588186004L;

    public static final QSampleResult sampleResult = new QSampleResult("sampleResult");

    public final StringPath description = createString("description");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final SetPath<Sample, QSample> samples = this.<Sample, QSample>createSet("samples", Sample.class, QSample.class, PathInits.DIRECT2);

    public QSampleResult(String variable) {
        super(SampleResult.class, forVariable(variable));
    }

    public QSampleResult(Path<? extends SampleResult> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSampleResult(PathMetadata metadata) {
        super(SampleResult.class, metadata);
    }

}

