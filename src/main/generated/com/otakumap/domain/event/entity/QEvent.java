package com.otakumap.domain.event.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QEvent is a Querydsl query type for Event
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QEvent extends EntityPathBase<Event> {

    private static final long serialVersionUID = 559538850L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QEvent event = new QEvent("event");

    public final com.otakumap.global.common.QBaseEntity _super = new com.otakumap.global.common.QBaseEntity(this);

    public final StringPath animationName = createString("animationName");

    public final com.otakumap.domain.image.entity.QImage backgroudImage;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final DatePath<java.time.LocalDate> endDate = createDate("endDate", java.time.LocalDate.class);

    public final ListPath<com.otakumap.domain.event_like.entity.EventLike, com.otakumap.domain.event_like.entity.QEventLike> eventLikeList = this.<com.otakumap.domain.event_like.entity.EventLike, com.otakumap.domain.event_like.entity.QEventLike>createList("eventLikeList", com.otakumap.domain.event_like.entity.EventLike.class, com.otakumap.domain.event_like.entity.QEventLike.class, PathInits.DIRECT2);

    public final com.otakumap.domain.eventLocation.entity.QEventLocation eventLocation;

    public final EnumPath<com.otakumap.domain.event.entity.enums.Genre> genre = createEnum("genre", com.otakumap.domain.event.entity.enums.Genre.class);

    public final com.otakumap.domain.image.entity.QImage goodsImage;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final StringPath site = createString("site");

    public final DatePath<java.time.LocalDate> startDate = createDate("startDate", java.time.LocalDate.class);

    public final EnumPath<com.otakumap.domain.event.entity.enums.EventStatus> status = createEnum("status", com.otakumap.domain.event.entity.enums.EventStatus.class);

    public final com.otakumap.domain.image.entity.QImage thumbnailImage;

    public final StringPath title = createString("title");

    public final EnumPath<com.otakumap.domain.event.entity.enums.EventType> type = createEnum("type", com.otakumap.domain.event.entity.enums.EventType.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QEvent(String variable) {
        this(Event.class, forVariable(variable), INITS);
    }

    public QEvent(Path<? extends Event> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QEvent(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QEvent(PathMetadata metadata, PathInits inits) {
        this(Event.class, metadata, inits);
    }

    public QEvent(Class<? extends Event> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.backgroudImage = inits.isInitialized("backgroudImage") ? new com.otakumap.domain.image.entity.QImage(forProperty("backgroudImage")) : null;
        this.eventLocation = inits.isInitialized("eventLocation") ? new com.otakumap.domain.eventLocation.entity.QEventLocation(forProperty("eventLocation"), inits.get("eventLocation")) : null;
        this.goodsImage = inits.isInitialized("goodsImage") ? new com.otakumap.domain.image.entity.QImage(forProperty("goodsImage")) : null;
        this.thumbnailImage = inits.isInitialized("thumbnailImage") ? new com.otakumap.domain.image.entity.QImage(forProperty("thumbnailImage")) : null;
    }

}

