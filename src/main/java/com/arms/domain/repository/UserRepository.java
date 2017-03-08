package com.arms.domain.repository;

import com.arms.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * Created by arms20170106 on 7/2/2560.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findOneByUsername(String username);
}
