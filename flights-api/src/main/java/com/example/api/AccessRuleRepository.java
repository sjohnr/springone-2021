package com.example.api;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessRuleRepository extends CrudRepository<AccessRule, String> {
}
