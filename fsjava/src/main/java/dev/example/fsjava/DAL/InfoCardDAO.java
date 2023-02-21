package dev.example.fsjava.DAL;

import dev.example.fsjava.DTO.InfoCardDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class InfoCardDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<InfoCardDTO> queryData(){
        String query = "SELECT * FROM content";
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(query);
        List<InfoCardDTO> result = new ArrayList<>();

        for (Map<String, Object> row : rows) {
            int ID = (int) row.get("id");
            String type = row.get("type").toString();
            String value = row.get("value").toString();
            Boolean check = (Boolean) row.get("checkT");

            InfoCardDTO model = new InfoCardDTO(ID, type,value, check);
            result.add(model);
        }
        return result;
    }

    public boolean addData(InfoCardDTO model){
        String query = "INSERT INTO content (type, value, checkT) VALUES (?, ?, ?)";
        try {
            jdbcTemplate.update(query, model.getType(), model.getValue(), model.isCheck());
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean updateData(InfoCardDTO model){
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