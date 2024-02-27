package com.malexj.repository;

import com.malexj.model.entity.ChatEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRepository extends MongoRepository<ChatEntity, String> {
  boolean existsByChatId(Long chatId);
}
