import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sml.Instruction;
import sml.Labels;
import sml.Machine;
import sml.Registers;
import sml.instruction.AddInstruction;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static sml.Registers.Register.EAX;
import static sml.Registers.Register.EBX;

public class LabelsTest {

    private Labels labels;
    private Registers registers;
    private Machine machine;

    @BeforeEach
    void setUp(){
        labels = new Labels();
        machine = new Machine(new Registers());
        registers = machine.getRegisters();
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
    void getAddressTestWithNullLabel(){
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            labels.getAddress(null);
                });
        String exceptedMessage = "Label is null";
        String actualMessage = exception.getMessage();
        Assertions.assertEquals(exceptedMessage, actualMessage);
    }

    @Test
    void addLabelTestForNullPointerException(){
        Exception exception = assertThrows(NullPointerException.class, () -> {
            labels.addLabel(null,5);
        });
        String expectedMessage = "Null not allowed";
        String actualMessage = exception.getMessage();
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

    @Test
    void checkIfLabelIsReturned(){
        registers.set(EAX, 3);
        registers.set(EBX, 3);
        Instruction instruction = new AddInstruction("f1", EAX, EBX);
        instruction.execute(machine);
        Labels labels = machine.getLabels();
        System.out.println(labels);
        Assertions.assertEquals("f1", machine.getLabels());
    }


}
