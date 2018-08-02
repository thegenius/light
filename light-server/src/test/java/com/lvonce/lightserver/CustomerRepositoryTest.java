package com.lvonce.lightserver;

import com.lvonce.lightserver.dal.domain.Customer;
import com.lvonce.lightserver.dal.repository.CustomerRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository repository;

    @Test
    public void test() {
        Customer customer = new Customer();
        customer.setId(24);
        customer.setFirstName("wang");
        customer.setLastName("wei");

        customer = repository.save(customer);
        Assert.assertNotNull(customer);

        Optional<Customer> result = repository.findById(24);
        Assert.assertTrue(result.isPresent());
    }
}
