package com.springboot.userservice.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QActivity is a Querydsl query type for Activity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QActivity extends EntityPathBase<Activity> {

    private static final long serialVersionUID = -1976829548L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QActivity activity = new QActivity("activity");

    public final QActivityResult activityResult;

    public final QActivityState activityState;

    public final StringPath conclusion = createString("conclusion");

    public final DatePath<java.sql.Date> createdDate = createDate("createdDate", java.sql.Date.class);

    public final QAppUser createdUser;

    public final DatePath<java.sql.Date> endDate = createDate("endDate", java.sql.Date.class);

    public final QFacility facility;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath name = createString("name");

    public final QPlan plan;

    public final SetPath<Sample, QSample> samples = this.<Sample, QSample>createSet("samples", Sample.class, QSample.class, PathInits.DIRECT2);

    public final DatePath<java.sql.Date> startDate = createDate("startDate", java.sql.Date.class);

    public QActivity(String variable) {
        this(Activity.class, forVariable(variable), INITS);
    }

    public QActivity(Path<? extends Activity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QActivity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QActivity(PathMetadata metadata, PathInits inits) {
        this(Activity.class, metadata, inits);
    }

    public QActivity(Class<? extends Activity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.activityResult = inits.isInitialized("activityResult") ? new QActivityResult(forProperty("activityResult")) : null;
        this.activityState = inits.isInitialized("activityState") ? new QActivityState(forProperty("activityState")) : null;
        this.createdUser = inits.isInitialized("createdUser") ? new QAppUser(forProperty("createdUser")) : null;
        this.facility = inits.isInitialized("facility") ? new QFacility(forProperty("facility"), inits.get("facility")) : null;
        this.plan = inits.isInitialized("plan") ? new QPlan(forProperty("plan"), inits.get("plan")) : null;
    }

}

