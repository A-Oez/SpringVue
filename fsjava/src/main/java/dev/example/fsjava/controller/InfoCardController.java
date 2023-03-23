package dev.example.fsjava.controller;

import com.google.gson.Gson;
import dev.example.fsjava.DAL.InfoCardDAO;
import dev.example.fsjava.DTO.InfoCardDTO;
import dev.example.fsjava.Model.InfoCardModel;
import dev.example.fsjava.service.InfoCardService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/infocard")
public class InfoCardController {
    @Autowired
    private InfoCardDAO infoCardDAO;

    @Autowired
    private InfoCardService infoCardService;

    @GetMapping("/getData")
    public String getData(HttpServletRequest request) {
        return new Gson().toJson(infoCardService.getInfoCards(request));
    }

    @PostMapping("/postData")
    public ResponseEntity<String> addData(@RequestBody String jsonInbound,HttpServletRequest request){
        return infoCardService.addInfoCards(jsonInbound,request);
    }

    @PostMapping("/updateData")
    public ResponseEntity<String> updateData(@RequestBody String jsonInbound,HttpServletRequest request){
        return infoCardService.updateInfoCards(jsonInbound,request);
    }

    @PostMapping("/deleteData")
    public ResponseEntity<String> deleteData(@RequestBody String ID,HttpServletRequest request){
        return infoCardService.deleteInfoCards(ID,request);
    }
}