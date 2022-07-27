package com.springboot.userservice.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFacilityState is a Querydsl query type for FacilityState
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFacilityState extends EntityPathBase<FacilityState> {

    private static final long serialVersionUID = -334735287L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFacilityState facilityState = new QFacilityState("facilityState");

    public final QFacility facility;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath name = createString("name");

    public QFacilityState(String variable) {
        this(FacilityState.class, forVariable(variable), INITS);
    }

    public QFacilityState(Path<? extends FacilityState> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFacilityState(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFacilityState(PathMetadata metadata, PathInits inits) {
        this(FacilityState.class, metadata, inits);
    }

    public QFacilityState(Class<? extends FacilityState> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.facility = inits.isInitialized("facility") ? new QFacility(forProperty("facility"), inits.get("facility")) : null;
    }

}

