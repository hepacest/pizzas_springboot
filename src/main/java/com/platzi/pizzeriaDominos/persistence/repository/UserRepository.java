package com.platzi.pizzeriaDominos.persistence.repository;

import com.platzi.pizzeriaDominos.persistence.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity,String> {
}
