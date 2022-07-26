package com.springboot.userservice.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QInspectionUnit is a Querydsl query type for InspectionUnit
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QInspectionUnit extends EntityPathBase<InspectionUnit> {

    private static final long serialVersionUID = -1635266595L;

    public static final QInspectionUnit inspectionUnit = new QInspectionUnit("inspectionUnit");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath name = createString("name");

    public final SetPath<Sample, QSample> samples = this.<Sample, QSample>createSet("samples", Sample.class, QSample.class, PathInits.DIRECT2);

    public QInspectionUnit(String variable) {
        super(InspectionUnit.class, forVariable(variable));
    }

    public QInspectionUnit(Path<? extends InspectionUnit> path) {
        super(path.getType(), path.getMetadata());
    }

    public QInspectionUnit(PathMetadata metadata) {
        super(InspectionUnit.class, metadata);
    }

}

