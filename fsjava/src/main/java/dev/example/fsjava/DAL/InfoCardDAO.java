package dev.example.fsjava.DAL;

import dev.example.fsjava.DTO.InfoCardDTO;
import dev.example.fsjava.Model.InfoCardModel;
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

    public List<InfoCardModel> queryData(){
        String query = "SELECT * FROM content WHERE content.type IS NOT NULL AND content.value IS NOT NULL;";
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(query);
        List<InfoCardModel> result = new ArrayList<>();

        for (Map<String, Object> row : rows) {
            int ID = (int) row.get("id");
            String type = row.get("type").toString();
            String value = row.get("value").toString();
            Boolean check = (Boolean) row.get("checkT");

            InfoCardModel model = new InfoCardModel(ID, type,value, check);
            result.add(model);
        }
        return result;
    }

    public boolean addData(InfoCardModel model){
        String query = "INSERT INTO content (type, value, checkT) VALUES (?, ?, ?)";
        try {
            jdbcTemplate.update(query, model.getType(), model.getValue(), model.isCheck());
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean updateData(InfoCardModel model){
        String query = "UPDATE content SET type=?, value=?, checkT=? WHERE id=?";
        try {
            jdbcTemplate.update(query, model.getType(), model.getValue(), model.isCheck(), model.getID());
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean deleteData(String ID){
        String query = "DELETE FROM content WHERE content.id = ?";
        try {
            jdbcTemplate.update(query, ID);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

}