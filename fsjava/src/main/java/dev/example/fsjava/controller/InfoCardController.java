package dev.example.fsjava.controller;

import com.google.gson.Gson;
import dev.example.fsjava.DAL.InfoCardDAO;
import dev.example.fsjava.DTO.InfoCardDTO;
import dev.example.fsjava.Model.InfoCardModel;
import dev.example.fsjava.service.InfoCardService;
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

    @Autowired
    private InfoCardService infoCardService;

    @GetMapping("/getData")
    public String getData() {
        return new Gson().toJson(infoCardService.getInfoCards());
    }

    @PostMapping("/postData")
    public ResponseEntity<String> addData(@RequestBody String jsonData){
        InfoCardDTO infoCardDTO = new Gson().fromJson(jsonData, InfoCardDTO.class);
        return infoCardService.addInfoCards(infoCardDTO);
    }

    @PostMapping("/updateData")
    public ResponseEntity<String> updateData(@RequestBody String jsonData){
        InfoCardDTO infoCardDTO = new Gson().fromJson(jsonData, InfoCardDTO.class);
        return infoCardService.updateInfoCards(infoCardDTO);
    }

    @PostMapping("/deleteData")
    public ResponseEntity<String> deleteData(@RequestBody String ID){
        return infoCardService.deleteInfoCards(ID);
    }
}