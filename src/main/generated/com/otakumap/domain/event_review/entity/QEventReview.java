package com.otakumap.domain.event_review.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QEventReview is a Querydsl query type for EventReview
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QEventReview extends EntityPathBase<EventReview> {

    private static final long serialVersionUID = -1760095975L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QEventReview eventReview = new QEventReview("eventReview");

    public final com.otakumap.global.common.QBaseEntity _super = new com.otakumap.global.common.QBaseEntity(this);

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final com.otakumap.domain.event.entity.QEvent event;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.otakumap.domain.image.entity.QImage image;

    public final NumberPath<Float> rating = createNumber("rating", Float.class);

    public final StringPath title = createString("title");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final com.otakumap.domain.user.entity.QUser user;

    public final NumberPath<Integer> view = createNumber("view", Integer.class);

    public QEventReview(String variable) {
        this(EventReview.class, forVariable(variable), INITS);
    }

    public QEventReview(Path<? extends EventReview> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QEventReview(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QEventReview(PathMetadata metadata, PathInits inits) {
        this(EventReview.class, metadata, inits);
    }

    public QEventReview(Class<? extends EventReview> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.event = inits.isInitialized("event") ? new com.otakumap.domain.event.entity.QEvent(forProperty("event"), inits.get("event")) : null;
        this.image = inits.isInitialized("image") ? new com.otakumap.domain.image.entity.QImage(forProperty("image")) : null;
        this.user = inits.isInitialized("user") ? new com.otakumap.domain.user.entity.QUser(forProperty("user"), inits.get("user")) : null;
    }

}

