package com.lvonce.lightserver.dal.repository;

import com.lvonce.lightserver.dal.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Component;

@Component
@RepositoryRestResource(path="user")
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
