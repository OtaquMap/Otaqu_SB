package com.otakumap.domain.eventShortReview.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QEventShortReview is a Querydsl query type for EventShortReview
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QEventShortReview extends EntityPathBase<EventShortReview> {

    private static final long serialVersionUID = -1666552144L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QEventShortReview eventShortReview = new QEventShortReview("eventShortReview");

    public final com.otakumap.global.common.QBaseEntity _super = new com.otakumap.global.common.QBaseEntity(this);

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Integer> dislikes = createNumber("dislikes", Integer.class);

    public final com.otakumap.domain.event.entity.QEvent event;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> likes = createNumber("likes", Integer.class);

    public final NumberPath<Float> rating = createNumber("rating", Float.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final com.otakumap.domain.user.entity.QUser user;

    public QEventShortReview(String variable) {
        this(EventShortReview.class, forVariable(variable), INITS);
    }

    public QEventShortReview(Path<? extends EventShortReview> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QEventShortReview(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QEventShortReview(PathMetadata metadata, PathInits inits) {
        this(EventShortReview.class, metadata, inits);
    }

    public QEventShortReview(Class<? extends EventShortReview> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.event = inits.isInitialized("event") ? new com.otakumap.domain.event.entity.QEvent(forProperty("event"), inits.get("event")) : null;
        this.user = inits.isInitialized("user") ? new com.otakumap.domain.user.entity.QUser(forProperty("user"), inits.get("user")) : null;
    }

}

