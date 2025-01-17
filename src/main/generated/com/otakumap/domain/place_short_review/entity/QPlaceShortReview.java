package com.otakumap.domain.place_short_review.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPlaceShortReview is a Querydsl query type for PlaceShortReview
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPlaceShortReview extends EntityPathBase<PlaceShortReview> {

    private static final long serialVersionUID = 1611935772L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPlaceShortReview placeShortReview = new QPlaceShortReview("placeShortReview");

    public final com.otakumap.global.common.QBaseEntity _super = new com.otakumap.global.common.QBaseEntity(this);

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> dislikes = createNumber("dislikes", Long.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> likes = createNumber("likes", Long.class);

    public final com.otakumap.domain.place.entity.QPlace place;

    public final NumberPath<Float> rating = createNumber("rating", Float.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final com.otakumap.domain.user.entity.QUser user;

    public QPlaceShortReview(String variable) {
        this(PlaceShortReview.class, forVariable(variable), INITS);
    }

    public QPlaceShortReview(Path<? extends PlaceShortReview> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPlaceShortReview(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPlaceShortReview(PathMetadata metadata, PathInits inits) {
        this(PlaceShortReview.class, metadata, inits);
    }

    public QPlaceShortReview(Class<? extends PlaceShortReview> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.place = inits.isInitialized("place") ? new com.otakumap.domain.place.entity.QPlace(forProperty("place")) : null;
        this.user = inits.isInitialized("user") ? new com.otakumap.domain.user.entity.QUser(forProperty("user"), inits.get("user")) : null;
    }

}

