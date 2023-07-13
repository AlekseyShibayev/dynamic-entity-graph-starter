package com.company.app.entitygraphextractor.domain.repository;

import com.company.app.entitygraphextractor.domain.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
}
