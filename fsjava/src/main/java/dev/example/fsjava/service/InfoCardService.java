package dev.example.fsjava.service;

import com.google.gson.Gson;
import dev.example.fsjava.DAL.InfoCardDAO;
import dev.example.fsjava.DTO.InfoCardDTO;
import dev.example.fsjava.Mapper.InfoCardMapper;
import dev.example.fsjava.Model.InfoCardModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class InfoCardService {

    @Autowired
    InfoCardDAO infoCardDAO = new InfoCardDAO();

    public List<InfoCardDTO> getInfoCards(){
        List<InfoCardModel> infoCardModelList = infoCardDAO.queryData();
        return InfoCardMapper.convertToListDTO(infoCardModelList);
    }

    public ResponseEntity<String> addInfoCards(InfoCardDTO infoCardDTO){
        InfoCardModel infoCardModel = InfoCardMapper.convertToModel(infoCardDTO);
        try{
            if(infoCardDAO.addData(infoCardModel) == false){return new ResponseEntity<>("Fehler beim Erstellen des Datensatzes\nKorrigieren sie die Struktur oder versuchen es später erneut" , HttpStatus.INTERNAL_SERVER_ERROR);}
            return new ResponseEntity<>("Datensatz erfolgreich erstellt", HttpStatus.CREATED);
        }
        catch (Exception ex){
            return new ResponseEntity<>("Fehler beim Erstellen des Datensatzes: " + ex.getMessage() , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> updateInfoCards(InfoCardDTO infoCardDTO){
        InfoCardModel infoCardModel = InfoCardMapper.convertToModel(infoCardDTO);
        try{
            if(infoCardDAO.updateData(infoCardModel) == false){return new ResponseEntity<>("Fehler beim updaten des Datensatzes\nKorrigieren sie die Struktur oder versuchen es später erneut" , HttpStatus.INTERNAL_SERVER_ERROR);}
            return new ResponseEntity<>("Datensatz erfolgreich geupdatet", HttpStatus.CREATED);
        }
        catch (Exception ex){
            return new ResponseEntity<>("Fehler beim updaten des Datensatzes: " + ex.getMessage() , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> deleteInfoCards(String ID){
        try{
            if(infoCardDAO.deleteData(ID) == false){return new ResponseEntity<>("Fehler beim löschen des Datensatzes\nKorrigieren sie die Struktur oder versuchen es später erneut" , HttpStatus.INTERNAL_SERVER_ERROR);}
            return new ResponseEntity<>("Datensatz erfolgreich gelöscht", HttpStatus.CREATED);
        }
        catch (Exception ex){
            return new ResponseEntity<>("Fehler beim löschen des Datensatzes: " + ex.getMessage() , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}