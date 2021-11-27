package ru.netology.jdbc.repository;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.persistence.criteria.Order;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class DBRepository {
    @PersistenceContext
    EntityManager entityManager;

    public List<Order> getOrderList (String name) {
        Query query = entityManager.createQuery(read("myScript.sql"));
        query.setParameter("name", name);
        var resultList = query.getResultList();
        return resultList;
    }

    private static String read(String scriptFileName) {
        try (InputStream is = new ClassPathResource(scriptFileName).getInputStream();
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is))) {
            return bufferedReader.lines().collect(Collectors.joining("\n"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
