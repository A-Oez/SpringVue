package dev.example.fsjava.service;

import dev.example.fsjava.DAL.InfoCardDAO;
import dev.example.fsjava.DTO.InfoCardDTO;
import dev.example.fsjava.Mapper.InfoCardMapper;
import dev.example.fsjava.Model.InfoCardModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.*;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InfoCardServiceTest {
    @Mock
    private InfoCardDAO infoCardDAO;

    @InjectMocks
    private InfoCardService infoCardService;

    private InfoCardDTO infoCardDTO;
    private InfoCardModel infoCardModel;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        // Define sample data for InfoCardDTO and InfoCardModel objects
        infoCardDTO = new InfoCardDTO();
        infoCardDTO.setID(1);
        infoCardDTO.setType("Sample title");
        infoCardDTO.setValue("Sample description");
        infoCardDTO.setCheck(false);

        infoCardModel = InfoCardMapper.convertToModel(infoCardDTO);
    }

    @Test
    public void getInfoCardsTest() {

        //Return of DAO queryData
        List<InfoCardModel> infoCardModelList = new ArrayList<>();
        infoCardModelList.add(infoCardModel);

        // Mock the InfoCardDAO's queryData() method to return the sample list of InfoCardModel objects
        when(infoCardDAO.queryData()).thenReturn(infoCardModelList);

        // Call the getInfoCards() method of the InfoCardService
        List<InfoCardDTO> result = infoCardService.getInfoCards();

        // Verify that InfoCardDAO's queryData() method was called once
        verify(infoCardDAO, times(1)).queryData();

        // Verify that the returned list of InfoCardDTO objects has the correct size and contents
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals("Sample title", result.get(0).getType());
        Assertions.assertEquals("Sample description", result.get(0).getValue());
        Assertions.assertEquals(false, result.get(0).isCheck());
    }

    //add, update, delete DAO return type = boolean
}