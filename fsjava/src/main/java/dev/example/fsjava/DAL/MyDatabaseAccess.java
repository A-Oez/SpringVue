package dev.example.fsjava.DAL;

import dev.example.fsjava.model.DBModel;
import dev.example.fsjava.service.JsonCreater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class MyDatabaseAccess {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<DBModel> queryData(){
        String query = "SELECT * FROM content";
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(query);
        List<DBModel> result = new ArrayList<>();

        for (Map<String, Object> row : rows) {
            String id = row.get("idcontent").toString();
            String type = row.get("type").toString();
            String value = row.get("value").toString();

            DBModel model = new DBModel(id,type,value);
            result.add(model);
        }
        return result;
    }

    public boolean addData(DBModel model){
        String query = "INSERT INTO content (idcontent, type, value) VALUES (?, ?, ?)";
        try {
            jdbcTemplate.update(query, model.getID(), model.getType(), model.getValue());
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

}
