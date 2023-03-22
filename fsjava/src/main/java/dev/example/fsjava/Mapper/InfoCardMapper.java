package dev.example.fsjava.Mapper;

import dev.example.fsjava.DTO.InfoCardDTO;
import dev.example.fsjava.Model.InfoCardModel;

import java.util.ArrayList;
import java.util.List;

public class InfoCardMapper {
    public static List<InfoCardDTO> convertToListDTO(List<InfoCardModel> infoCardModelList){
        List<InfoCardDTO> infoCardDTOList = new ArrayList<>();
        for(InfoCardModel infoCardModel: infoCardModelList){
            infoCardDTOList.add(new InfoCardDTO(infoCardModel.getID(), infoCardModel.getType(), infoCardModel.getValue(), infoCardModel.isCheck()));
        }
        return infoCardDTOList;
    }

    public static InfoCardModel convertToModel(InfoCardDTO infoCardDTO){
        return new InfoCardModel(infoCardDTO.getID(), infoCardDTO.getType(), infoCardDTO.getValue(), infoCardDTO.isCheck());
    }
}
