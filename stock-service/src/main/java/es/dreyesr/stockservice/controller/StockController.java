package es.dreyesr.stockservice.controller;

import java.util.Optional;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import es.dreyesr.stockservice.entity.StockEntity;
import es.dreyesr.stockservice.repository.StockRepository;

@Slf4j
@RestController
@RequestMapping("/api/stocks")
public class StockController {

    @Autowired
    private StockRepository stockRepository;

    @RequestMapping("/{code}")
    public boolean stockAvailable(@PathVariable String code) {
        log.info("In: {}", code);
        Optional<StockEntity> stock = stockRepository.findByCode(code);
        stock.orElseThrow(() -> new RuntimeException("Cannot find the product " + code));
        return stock.get().getQuantity() > 0;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StockEntity saveStock(@RequestBody StockEntity stockEntity) {
        log.info("In: {}", stockEntity);
        return stockRepository.save(stockEntity);
    }
}
