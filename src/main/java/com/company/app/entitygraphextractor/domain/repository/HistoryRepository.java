package com.company.app.entitygraphextractor.domain.repository;

import com.company.app.entitygraphextractor.domain.entity.History;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryRepository extends JpaRepository<History, Long> {
}
