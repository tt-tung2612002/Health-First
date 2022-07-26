package com.springboot.userservice.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBusinessType is a Querydsl query type for BusinessType
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBusinessType extends EntityPathBase<BusinessType> {

    private static final long serialVersionUID = -1331290337L;

    public static final QBusinessType businessType = new QBusinessType("businessType");

    public final SetPath<Facility, QFacility> facility = this.<Facility, QFacility>createSet("facility", Facility.class, QFacility.class, PathInits.DIRECT2);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath name = createString("name");

    public QBusinessType(String variable) {
        super(BusinessType.class, forVariable(variable));
    }

    public QBusinessType(Path<? extends BusinessType> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBusinessType(PathMetadata metadata) {
        super(BusinessType.class, metadata);
    }

}

