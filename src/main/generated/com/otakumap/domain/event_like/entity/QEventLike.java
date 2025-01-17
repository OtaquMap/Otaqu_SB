package com.otakumap.domain.event_like.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QEventLike is a Querydsl query type for EventLike
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QEventLike extends EntityPathBase<EventLike> {

    private static final long serialVersionUID = -1375749575L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QEventLike eventLike = new QEventLike("eventLike");

    public final com.otakumap.global.common.QBaseEntity _super = new com.otakumap.global.common.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final com.otakumap.domain.event.entity.QEvent event;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isFavorite = createBoolean("isFavorite");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final com.otakumap.domain.user.entity.QUser user;

    public QEventLike(String variable) {
        this(EventLike.class, forVariable(variable), INITS);
    }

    public QEventLike(Path<? extends EventLike> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QEventLike(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QEventLike(PathMetadata metadata, PathInits inits) {
        this(EventLike.class, metadata, inits);
    }

    public QEventLike(Class<? extends EventLike> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.event = inits.isInitialized("event") ? new com.otakumap.domain.event.entity.QEvent(forProperty("event"), inits.get("event")) : null;
        this.user = inits.isInitialized("user") ? new com.otakumap.domain.user.entity.QUser(forProperty("user"), inits.get("user")) : null;
    }

}

