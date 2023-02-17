package dev.example.fsjava.DAL;

import dev.example.fsjava.service.JsonCreater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class MyDatabaseAccess {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public String queryData(){
        String query = "SELECT * FROM content";
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(query);
        StringBuilder builder = new StringBuilder();
        for (Map<String, Object> row : rows) {
            builder.append(row);
        }
        return builder.toString();
    }

}
