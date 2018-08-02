package com.lvonce.lightserver.core;

import com.lvonce.lightserver.dal.domain.Resource;
import com.lvonce.lightserver.dal.repository.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class ResourceService {

    @Autowired
    ResourceRepository resourceRepository;

    @Transactional
    public int trade(Resource resourceA, Resource resourceB) {
        Optional<Resource> baseResourceA = resourceRepository.findById(resourceA.getId());
        Optional<Resource> baseResourceB = resourceRepository.findById(resourceB.getId());
        if (!baseResourceA.isPresent() || !baseResourceB.isPresent()) {
            return -1;
        }
        Resource newResourceA = baseResourceA.get();
        Resource newResourceB = baseResourceA.get();
        newResourceA.setAmount(newResourceA.getAmount() + resourceA.getAmount());
        newResourceB.setAmount(newResourceB.getAmount() + resourceB.getAmount());
        return 0;
    }

}
