package dev.example.fsjava.controller;

import com.google.gson.Gson;
import dev.example.fsjava.DAL.InfoCardDAO;
import dev.example.fsjava.DTO.InfoCardDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/infocard")
public class InfoCardController {
    @Autowired
    private InfoCardDAO infoCardDAO;

    @GetMapping("/getData")
    public String getData() {
        List<InfoCardDTO> list = infoCardDAO.queryData();
        return new Gson().toJson(list);
    }

    @PostMapping("/postData")
    public ResponseEntity<String> addData(@RequestBody String jsonData){
        try{
            InfoCardDTO model = new Gson().fromJson(jsonData, InfoCardDTO.class);
            if(infoCardDAO.addData(model) == false){return new ResponseEntity<>("Fehler beim Erstellen des Datensatzes\nKorrigieren sie die Struktur oder versuchen es später erneut" , HttpStatus.INTERNAL_SERVER_ERROR);}
            return new ResponseEntity<>("Datensatz erfolgreich erstellt", HttpStatus.CREATED);
        }
        catch (Exception ex){
            return new ResponseEntity<>("Fehler beim Erstellen des Datensatzes: " + ex.getMessage() , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/updateData")
    public ResponseEntity<String> updateData(@RequestBody String jsonData){
        try{
            InfoCardDTO model = new Gson().fromJson(jsonData, InfoCardDTO.class);
            if(infoCardDAO.updateData(model) == false){return new ResponseEntity<>("Fehler beim updaten des Datensatzes\nKorrigieren sie die Struktur oder versuchen es später erneut" , HttpStatus.INTERNAL_SERVER_ERROR);}
            return new ResponseEntity<>("Datensatz erfolgreich geupdatet", HttpStatus.CREATED);
        }
        catch (Exception ex){
            return new ResponseEntity<>("Fehler beim updaten des Datensatzes: " + ex.getMessage() , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/deleteData")
    public ResponseEntity<String> deleteData(@RequestBody String ID){
        try{
            if(infoCardDAO.deleteData(ID) == false){return new ResponseEntity<>("Fehler beim updaten des Datensatzes\nKorrigieren sie die Struktur oder versuchen es später erneut" , HttpStatus.INTERNAL_SERVER_ERROR);}
            return new ResponseEntity<>("Datensatz erfolgreich geupdatet", HttpStatus.CREATED);
        }
        catch (Exception ex){
            return new ResponseEntity<>("Fehler beim updaten des Datensatzes: " + ex.getMessage() , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}