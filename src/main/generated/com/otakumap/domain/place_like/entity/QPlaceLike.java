package com.otakumap.domain.place_like.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPlaceLike is a Querydsl query type for PlaceLike
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPlaceLike extends EntityPathBase<PlaceLike> {

    private static final long serialVersionUID = 1027033299L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPlaceLike placeLike = new QPlaceLike("placeLike");

    public final com.otakumap.global.common.QBaseEntity _super = new com.otakumap.global.common.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isFavorite = createBoolean("isFavorite");

    public final com.otakumap.domain.place.entity.QPlace place;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final com.otakumap.domain.user.entity.QUser user;

    public QPlaceLike(String variable) {
        this(PlaceLike.class, forVariable(variable), INITS);
    }

    public QPlaceLike(Path<? extends PlaceLike> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPlaceLike(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPlaceLike(PathMetadata metadata, PathInits inits) {
        this(PlaceLike.class, metadata, inits);
    }

    public QPlaceLike(Class<? extends PlaceLike> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.place = inits.isInitialized("place") ? new com.otakumap.domain.place.entity.QPlace(forProperty("place")) : null;
        this.user = inits.isInitialized("user") ? new com.otakumap.domain.user.entity.QUser(forProperty("user"), inits.get("user")) : null;
    }

}

