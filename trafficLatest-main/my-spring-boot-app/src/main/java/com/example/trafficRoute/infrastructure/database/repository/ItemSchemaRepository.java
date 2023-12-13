package com.example.trafficRoute.infrastructure.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.trafficRoute.infrastructure.database.schema.ItemSchema;

@Repository
public interface ItemSchemaRepository extends JpaRepository<ItemSchema, Long> {
}

