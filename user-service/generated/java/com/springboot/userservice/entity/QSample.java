package com.springboot.userservice.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSample is a Querydsl query type for Sample
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSample extends EntityPathBase<Sample> {

    private static final long serialVersionUID = -2121180817L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSample sample = new QSample("sample");

    public final QActivity activity;

    public final DatePath<java.sql.Date> createdDate = createDate("createdDate", java.sql.Date.class);

    public final QFood food;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final QInspectionUnit inspectionUnit;

    public final DatePath<java.sql.Date> resultedDate = createDate("resultedDate", java.sql.Date.class);

    public final StringPath sampleCode = createString("sampleCode");

    public final QSampleResult sampleResult;

    public final QSampleState sampleState;

    public QSample(String variable) {
        this(Sample.class, forVariable(variable), INITS);
    }

    public QSample(Path<? extends Sample> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSample(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSample(PathMetadata metadata, PathInits inits) {
        this(Sample.class, metadata, inits);
    }

    public QSample(Class<? extends Sample> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.activity = inits.isInitialized("activity") ? new QActivity(forProperty("activity"), inits.get("activity")) : null;
        this.food = inits.isInitialized("food") ? new QFood(forProperty("food")) : null;
        this.inspectionUnit = inits.isInitialized("inspectionUnit") ? new QInspectionUnit(forProperty("inspectionUnit")) : null;
        this.sampleResult = inits.isInitialized("sampleResult") ? new QSampleResult(forProperty("sampleResult")) : null;
        this.sampleState = inits.isInitialized("sampleState") ? new QSampleState(forProperty("sampleState")) : null;
    }

}

