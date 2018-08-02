package com.lvonce.lightserver.api;

import com.lvonce.lightserver.core.ResourceService;
import com.lvonce.lightserver.dal.domain.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Component
@RestController
public class Trade {

    @Autowired
    private ResourceService resourceService;

    @PostMapping("/trade")
    public int trade(Resource resourceA, Resource resourceB) {
        return resourceService.trade(resourceA, resourceB);
    }
}
