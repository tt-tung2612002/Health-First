package com.springboot.userservice.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCertificateState is a Querydsl query type for CertificateState
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCertificateState extends EntityPathBase<CertificateState> {

    private static final long serialVersionUID = 259828415L;

    public static final QCertificateState certificateState = new QCertificateState("certificateState");

    public final SetPath<Certificate, QCertificate> certificates = this.<Certificate, QCertificate>createSet("certificates", Certificate.class, QCertificate.class, PathInits.DIRECT2);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath name = createString("name");

    public QCertificateState(String variable) {
        super(CertificateState.class, forVariable(variable));
    }

    public QCertificateState(Path<? extends CertificateState> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCertificateState(PathMetadata metadata) {
        super(CertificateState.class, metadata);
    }

}

