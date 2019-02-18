package com.ratna.microservices.currencyexchangeservice;

import com.ratna.microservices.currencyexchangeservice.bean.ExchangeValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CurrencyExchangeController {

    @Autowired
    private Environment environment;

    @Autowired
    private ExchangeValueRepoasitory repository;

    @GetMapping("currency-exchange/from/{from}/to/{to}")
    public ExchangeValue retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {
        ExchangeValue value =  repository.findByFromAndTo(from, to);
        value.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
        return value;
    }
}
