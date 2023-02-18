package dev.example.fsjava.controller;

import com.google.gson.Gson;
import dev.example.fsjava.DAL.MyDatabaseAccess;
import dev.example.fsjava.model.DBModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/database")
public class DatabaseController {
    @Autowired
    private MyDatabaseAccess myDatabaseAccess;

    @GetMapping("/data")
    public String getData() {
        List<DBModel> list = myDatabaseAccess.queryData();
        return new Gson().toJson(list);
    }

    @PostMapping("/postData")
    public ResponseEntity<String> addData(@RequestBody String jsonData){
        DBModel model = new Gson().fromJson(jsonData, DBModel.class);
        if(model.equals(null) || myDatabaseAccess.addData(model) == false){return new ResponseEntity<>("Fehler beim Erstellen des Datensatzes: " , HttpStatus.INTERNAL_SERVER_ERROR);}

        return new ResponseEntity<>("Datensatz erfolgreich erstellt", HttpStatus.CREATED);
    }
}
