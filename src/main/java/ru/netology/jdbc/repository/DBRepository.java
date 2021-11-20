package ru.netology.jdbc.repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class DBRepository {
    private List<String> product_list = new ArrayList<>();

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<String> getProductName (String name) {
        SqlRowSet sqlRowSet1 = jdbcTemplate.queryForRowSet(read("myScript.sql"),name);
        while (sqlRowSet1.next()) {
            String product_name = sqlRowSet1.getString("product_name");
            System.out.println(product_name);
            product_list.add(product_name);
        }
        return product_list;
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
