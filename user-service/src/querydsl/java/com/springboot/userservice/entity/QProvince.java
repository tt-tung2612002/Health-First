package com.springboot.userservice.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QProvince is a Querydsl query type for Province
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProvince extends EntityPathBase<Province> {

    private static final long serialVersionUID = -1308347979L;

    public static final QProvince province = new QProvince("province");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath name = createString("name");

    public QProvince(String variable) {
        super(Province.class, forVariable(variable));
    }

    public QProvince(Path<? extends Province> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProvince(PathMetadata metadata) {
        super(Province.class, metadata);
    }

}

