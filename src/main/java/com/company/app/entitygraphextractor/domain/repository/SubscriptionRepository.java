package com.company.app.entitygraphextractor.domain.repository;

import com.company.app.entitygraphextractor.domain.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
}
