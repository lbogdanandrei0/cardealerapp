package com.lbogdanandrei.cardealerapp.configuration;

import com.lbogdanandrei.cardealerapp.repository.DealerRepository;
import com.lbogdanandrei.cardealerapp.service.DealerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Autowired
    public DealerService dealerService;
    @Autowired
    public DealerRepository dealerRepository;
}
