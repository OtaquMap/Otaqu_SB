package com.otakumap.domain.route_item.repository;

import com.otakumap.domain.route_item.entity.RouteItem;
import com.otakumap.domain.route_item.enums.ItemType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RouteItemRepository extends JpaRepository<RouteItem, Long> {

    List<RouteItem> findByItemIdAndItemType(Long itemId, ItemType itemType);
}
