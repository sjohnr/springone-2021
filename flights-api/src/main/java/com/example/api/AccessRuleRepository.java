package com.example.api;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessRuleRepository extends ReactiveCrudRepository<AccessRule, String> {
}
