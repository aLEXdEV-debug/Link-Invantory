package com.Link.Inventory.repositories;

import com.Link.Inventory.controllers.models.User;
import com.Link.Inventory.controllers.models.Place;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlaceRepository extends JpaRepository<Place, Long> {

    List<Place> findByUser(User user);

    List<Place> findByChecked(boolean checked);
}