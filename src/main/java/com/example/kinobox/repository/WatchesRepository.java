package com.example.kinobox.repository;

import com.example.kinobox.model.Watchers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WatchesRepository extends JpaRepository<Watchers, Integer> {
}
