package dev.example.fsjava.DAL;

import dev.example.fsjava.model.DBModel;
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
            int ID = (int) row.get("ID");
            String type = row.get("type").toString();
            String value = row.get("value").toString();
            Boolean check = (Boolean) row.get("checkT");

            DBModel model = new DBModel(ID, type,value, check);
            result.add(model);
        }
        return result;
    }

    public boolean addData(DBModel model){
        String query = "INSERT INTO content (type, value, checkT) VALUES (?, ?, ?)";
        try {
            jdbcTemplate.update(query, model.getType(), model.getValue(), model.isCheck());
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean updateData(DBModel model){
        String query = "UPDATE content SET type=?, value=?, checkT=? WHERE id=?";
        try {
            jdbcTemplate.update(query, model.getType(), model.getValue(), model.isCheck(), model.getID());
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

}
