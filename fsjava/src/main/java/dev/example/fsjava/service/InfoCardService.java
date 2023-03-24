package dev.example.fsjava.service;

import com.google.gson.Gson;
import dev.example.fsjava.DAL.InfoCardDAO;
import dev.example.fsjava.DTO.InfoCardDTO;
import dev.example.fsjava.Mapper.InfoCardMapper;
import dev.example.fsjava.Model.InfoCardModel;
import dev.example.fsjava.logger.LogInbound;
import dev.example.fsjava.logger.LogService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InfoCardService {

    @Autowired
    InfoCardDAO infoCardDAO = new InfoCardDAO();

    public List<InfoCardDTO> getInfoCards(HttpServletRequest request){
        LogInbound inbound = LogService.createInbound(request.getRequestURI(), request);
        List<InfoCardDTO> dtoList = new ArrayList<>();

        try{
            List<InfoCardModel> infoCardModelList = infoCardDAO.queryData();
            dtoList = InfoCardMapper.convertToListDTO(infoCardModelList);
            LogService.logger(inbound,"SUCCESS");
        }
        catch (Exception ex){
            LogService.logger(inbound,ex.getMessage());
        }

        return dtoList;
    }

    public ResponseEntity<String> addInfoCards(String jsonInbound, HttpServletRequest request){
        LogInbound inbound = LogService.createInbound(jsonInbound, request);
        ResponseEntity message;

        try{
            InfoCardDTO infoCardDTO = new Gson().fromJson(jsonInbound, InfoCardDTO.class);
            InfoCardModel infoCardModel = InfoCardMapper.convertToModel(infoCardDTO);
            if(infoCardDAO.addData(infoCardModel) == false) {
                message = new ResponseEntity<>(
                        "Fehler beim Erstellen des Datensatzes\nKorrigieren sie die Struktur oder versuchen es später erneut"
                         , HttpStatus.INTERNAL_SERVER_ERROR);
            }
            else{
                message = new ResponseEntity<>("Datensatz erfolgreich erstellt", HttpStatus.CREATED);
            }
            LogService.logger(inbound, message.toString());
        }
        catch (Exception ex){
            message = new ResponseEntity<>("Fehler beim Erstellen des Datensatzes: " + ex.getMessage() , HttpStatus.INTERNAL_SERVER_ERROR);
            LogService.logger(inbound,message.toString());
        }

        return message;
    }

    public ResponseEntity<String> updateInfoCards(String jsonInbound, HttpServletRequest request){
        LogInbound inbound = LogService.createInbound(jsonInbound, request);
        ResponseEntity message;

        try{
            InfoCardDTO infoCardDTO = new Gson().fromJson(jsonInbound, InfoCardDTO.class);
            InfoCardModel infoCardModel = InfoCardMapper.convertToModel(infoCardDTO);
            if(infoCardDAO.updateData(infoCardModel) == false){
                message = new ResponseEntity<>(
                     "Fehler beim updaten des Datensatzes\nKorrigieren sie die Struktur oder versuchen es später erneut"
                         , HttpStatus.INTERNAL_SERVER_ERROR);
            }
            else{
                message = new ResponseEntity<>("Datensatz erfolgreich geupdatet", HttpStatus.CREATED);
            }
            LogService.logger(inbound,message.toString());
        }
        catch (Exception ex){
            message = new ResponseEntity<>("Fehler beim updaten des Datensatzes: " + ex.getMessage() , HttpStatus.INTERNAL_SERVER_ERROR);
            LogService.logger(inbound,message.toString());
        }

        return message;
    }

    public ResponseEntity<String> deleteInfoCards(String ID, HttpServletRequest request){
        LogInbound inbound = LogService.createInbound("InfoCard ID: " + ID, request);
        ResponseEntity message;

        try{
            if(infoCardDAO.deleteData(ID) == false){
                message = new ResponseEntity<>
                        ("Fehler beim löschen des Datensatzes\nKorrigieren sie die Struktur oder versuchen es später erneut"
                         , HttpStatus.INTERNAL_SERVER_ERROR);
            }
            else{
                message = new ResponseEntity<>("Datensatz erfolgreich gelöscht", HttpStatus.CREATED);
            }
            LogService.logger(inbound,message.toString());
        }
        catch (Exception ex){
            message = new ResponseEntity<>("Fehler beim löschen des Datensatzes: " + ex.getMessage() , HttpStatus.INTERNAL_SERVER_ERROR);
            LogService.logger(inbound, message.toString());
        }

        return message;
    }
}