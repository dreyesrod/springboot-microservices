package es.dreyesr.stockmicroservice.controller;

import java.util.Optional;
import java.util.UUID;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import es.dreyesr.stockmicroservice.entity.StockEntity;
import es.dreyesr.stockmicroservice.repository.StockRepository;

@RestController
@RequestMapping("/api/stock")
public class StockController {

    @Autowired
    private StockRepository stockRepository;

    @RequestMapping("/{code}")
    public boolean stockAvailable(@PathVariable String code) {

        Optional<StockEntity> stock = stockRepository.findByCode(code);

        stock.orElseThrow(() -> new RuntimeException("Cannot find the product " + code));

        return stock.get().getQuantity() > 0;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StockEntity saveStock(@RequestBody StockEntity stockEntity) {
        return stockRepository.save(stockEntity);
    }
}
