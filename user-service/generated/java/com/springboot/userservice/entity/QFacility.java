package com.springboot.userservice.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFacility is a Querydsl query type for Facility
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFacility extends EntityPathBase<Facility> {

    private static final long serialVersionUID = 180253992L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFacility facility = new QFacility("facility");

    public final SetPath<Activity, QActivity> activities = this.<Activity, QActivity>createSet("activities", Activity.class, QActivity.class, PathInits.DIRECT2);

    public final QAddress address;

    public final QBusinessType businessType;

    public final SetPath<Certificate, QCertificate> certificates = this.<Certificate, QCertificate>createSet("certificates", Certificate.class, QCertificate.class, PathInits.DIRECT2);

    public final StringPath facilityCode = createString("facilityCode");

    public final QFacilityState facilityState;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath name = createString("name");

    public QFacility(String variable) {
        this(Facility.class, forVariable(variable), INITS);
    }

    public QFacility(Path<? extends Facility> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFacility(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFacility(PathMetadata metadata, PathInits inits) {
        this(Facility.class, metadata, inits);
    }

    public QFacility(Class<? extends Facility> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.address = inits.isInitialized("address") ? new QAddress(forProperty("address"), inits.get("address")) : null;
        this.businessType = inits.isInitialized("businessType") ? new QBusinessType(forProperty("businessType")) : null;
        this.facilityState = inits.isInitialized("facilityState") ? new QFacilityState(forProperty("facilityState"), inits.get("facilityState")) : null;
    }

}

