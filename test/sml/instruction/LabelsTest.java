package sml.instruction;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sml.Labels;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class LabelsTest {

    private Labels labels;

    @BeforeEach
    void setUp(){
        labels = new Labels();
    }

    @AfterEach
    void tearDown(){
        labels = null;
    }

    @Test
    void getAddressTest(){
        labels.addLabel("f1",5);
        Integer value = labels.getAddress("f1");
        Integer testValue = 5;
        Assertions.assertEquals(value, testValue);
    }

    @Test
    void addLabelTestForNullPointerException(){
        Exception exception = assertThrows(NullPointerException.class, () -> {
            labels.addLabel(null,5);
        });
        String expectedMessage = "Null not allowed";
        String actualMessage = exception.getMessage();
        System.out.println(actualMessage);
        Assertions.assertEquals(actualMessage, expectedMessage);

    }

    @Test
    void addLabelTestForDuplicateIllegalArgumentException(){
        String label = "f1";
        labels.addLabel(label,2);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
           labels.addLabel(label, 3);
        });
        String expectedMessage = "Duplicate label found: " + label + ". " + "Check input file.";
        String actualMessage = exception.getMessage();;
        Assertions.assertEquals(expectedMessage, actualMessage);
    }




}
