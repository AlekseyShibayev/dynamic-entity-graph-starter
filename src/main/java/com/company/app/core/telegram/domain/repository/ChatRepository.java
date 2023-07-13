package com.company.app.core.telegram.domain.repository;

import com.company.app.core.telegram.domain.entity.Chat;
import org.springframework.data.repository.CrudRepository;

public interface ChatRepository extends CrudRepository<Chat, Long> {

}
