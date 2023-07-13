package com.company.app.entitygraphextractor.domain.repository;

import com.company.app.entitygraphextractor.domain.entity.SubscriptionInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionInfoRepository extends JpaRepository<SubscriptionInfo, Long> {
}
