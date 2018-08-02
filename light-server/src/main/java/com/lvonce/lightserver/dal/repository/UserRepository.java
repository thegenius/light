package com.lvonce.lightserver.dal.repository;

import com.lvonce.lightserver.dal.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Component;

@Component
@RepositoryRestResource(path="/user")
public interface UserRepository extends JpaRepository<User, Long> {
}
