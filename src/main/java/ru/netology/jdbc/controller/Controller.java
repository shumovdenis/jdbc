package ru.netology.jdbc.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.netology.jdbc.repository.DBRepository;

import javax.persistence.criteria.Order;
import java.util.List;

@RestController
public class Controller {
    final DBRepository dbRepository;

    public Controller(DBRepository dbRepository) {
        this.dbRepository = dbRepository;
    }

    @GetMapping("/products/fetch-product")
    public List<Order> getName(@RequestParam("name") String name) {
        return dbRepository.getOrderList(name);
    }
}
