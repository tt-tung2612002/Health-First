package com.springboot.userservice.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QAppRole is a Querydsl query type for AppRole
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAppRole extends EntityPathBase<AppRole> {

    private static final long serialVersionUID = 304024338L;

    public static final QAppRole appRole = new QAppRole("appRole");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath name = createString("name");

    public QAppRole(String variable) {
        super(AppRole.class, forVariable(variable));
    }

    public QAppRole(Path<? extends AppRole> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAppRole(PathMetadata metadata) {
        super(AppRole.class, metadata);
    }

}

