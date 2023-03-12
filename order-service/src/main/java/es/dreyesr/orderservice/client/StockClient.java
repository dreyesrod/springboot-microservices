package es.dreyesr.orderservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "stock-service")
public interface StockClient {

    @RequestMapping("/api/stock/{code}")
    boolean stockAvailable(@PathVariable String code);
}
