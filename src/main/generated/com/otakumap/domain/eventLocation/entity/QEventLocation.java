package com.otakumap.domain.eventLocation.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QEventLocation is a Querydsl query type for EventLocation
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QEventLocation extends EntityPathBase<EventLocation> {

    private static final long serialVersionUID = -609307966L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QEventLocation eventLocation = new QEventLocation("eventLocation");

    public final com.otakumap.global.common.QBaseEntity _super = new com.otakumap.global.common.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final com.otakumap.domain.event.entity.QEvent event;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath latitude = createString("latitude");

    public final StringPath longitude = createString("longitude");

    public final StringPath name = createString("name");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QEventLocation(String variable) {
        this(EventLocation.class, forVariable(variable), INITS);
    }

    public QEventLocation(Path<? extends EventLocation> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QEventLocation(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QEventLocation(PathMetadata metadata, PathInits inits) {
        this(EventLocation.class, metadata, inits);
    }

    public QEventLocation(Class<? extends EventLocation> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.event = inits.isInitialized("event") ? new com.otakumap.domain.event.entity.QEvent(forProperty("event"), inits.get("event")) : null;
    }

}

