package dev.example.fsjava.controller;

import dev.example.fsjava.DAL.MyDatabaseAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/database")
public class DatabaseController {
    @Autowired
    private MyDatabaseAccess myDatabaseAccess;

    @GetMapping("/data")
    public String getData() {
        return myDatabaseAccess.queryData();
    }

}
