package dev.example.fsjava.Mapper;

import dev.example.fsjava.DTO.InfoCardDTO;
import dev.example.fsjava.Model.InfoCardModel;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class InfoCardMapperTest {
    @Test
    void convertToListDTO() {
        // Erstellen der Mock-Daten
        InfoCardModel mockInfoCardModel1 = mock(InfoCardModel.class);//1. Zu Beginn des Tests werden Mock-Objekte für InfoCardModel erstellt und für jedes dieser Objekte die entsprechenden Werte mithilfe von when und thenReturn festgelegt.
        when(mockInfoCardModel1.getID()).thenReturn(1);
        when(mockInfoCardModel1.getType()).thenReturn("Type 1");
        when(mockInfoCardModel1.getValue()).thenReturn("Value 1");
        when(mockInfoCardModel1.isCheck()).thenReturn(true);

        InfoCardModel mockInfoCardModel2 = mock(InfoCardModel.class);
        when(mockInfoCardModel2.getID()).thenReturn(2);
        when(mockInfoCardModel2.getType()).thenReturn("Type 2");
        when(mockInfoCardModel2.getValue()).thenReturn("Value 2");
        when(mockInfoCardModel2.isCheck()).thenReturn(false);

        List<InfoCardModel> mockInfoCardModelList = new ArrayList<>(); //2. Diese Mock-Objekte werden dann in eine Liste eingefügt, die als Eingabe für die convertToListDTO-Methode verwendet wird.
        mockInfoCardModelList.add(mockInfoCardModel1);
        mockInfoCardModelList.add(mockInfoCardModel2);

        // Ausführen der Methode unter Test
        List<InfoCardDTO> infoCardDTOList = InfoCardMapper.convertToListDTO(mockInfoCardModelList);//3. Dann wird die convertToListDTO-Methode aufgerufen und das Ergebnis in einer List<InfoCardDTO> gespeichert.


        // Überprüfen der Ergebnisse
        assertEquals(2, infoCardDTOList.size()); //4. Anschließend werden verschiedene Eigenschaften der InfoCardDTO-Objekte in der Liste mit den erwarteten Werten verglichen,um sicherzustellen, dass die Methode die Model-Objekte korrekt in DTO-Objekte umgewandelt hat.

        InfoCardDTO infoCardDTO1 = infoCardDTOList.get(0);
        assertEquals(1, infoCardDTO1.getID());
        assertEquals("Type 1", infoCardDTO1.getType());
        assertEquals("Value 1", infoCardDTO1.getValue());
        assertEquals(true, infoCardDTO1.isCheck());

        InfoCardDTO infoCardDTO2 = infoCardDTOList.get(1);
        assertEquals(2, infoCardDTO2.getID());
        assertEquals("Type 2", infoCardDTO2.getType());
        assertEquals("Value 2", infoCardDTO2.getValue());
        assertEquals(false, infoCardDTO2.isCheck());

            /*
             Insgesamt ist dieser Test also eine grundlegende Überprüfung, ob die Methode convertToListDTO korrekt funktioniert, indem sie InfoCardModel-Objekte in InfoCardDTO-Objekte umwandelt.
             Der Test verwendet Mock-Objekte, um sicherzustellen, dass das Testergebnis reproduzierbar ist und nicht von der tatsächlichen Implementierung der InfoCardModel-Klasse abhängt.
             */
    }

    @Test
    void convertToModel(){

    }
}