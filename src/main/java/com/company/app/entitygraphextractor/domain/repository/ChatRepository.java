package com.company.app.entitygraphextractor.domain.repository;

import com.company.app.entitygraphextractor.domain.entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Chat, Long> {
}
