package com.otakumap.domain.place_review.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPlaceReview is a Querydsl query type for PlaceReview
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPlaceReview extends EntityPathBase<PlaceReview> {

    private static final long serialVersionUID = 370620915L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPlaceReview placeReview = new QPlaceReview("placeReview");

    public final com.otakumap.global.common.QBaseEntity _super = new com.otakumap.global.common.QBaseEntity(this);

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.otakumap.domain.place.entity.QPlace place;

    public final StringPath title = createString("title");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final com.otakumap.domain.user.entity.QUser user;

    public final NumberPath<Long> view = createNumber("view", Long.class);

    public QPlaceReview(String variable) {
        this(PlaceReview.class, forVariable(variable), INITS);
    }

    public QPlaceReview(Path<? extends PlaceReview> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPlaceReview(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPlaceReview(PathMetadata metadata, PathInits inits) {
        this(PlaceReview.class, metadata, inits);
    }

    public QPlaceReview(Class<? extends PlaceReview> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.place = inits.isInitialized("place") ? new com.otakumap.domain.place.entity.QPlace(forProperty("place")) : null;
        this.user = inits.isInitialized("user") ? new com.otakumap.domain.user.entity.QUser(forProperty("user"), inits.get("user")) : null;
    }

}

