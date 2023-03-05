package sml.instruction;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sml.Machine;
import sml.Registers;
import sml.Translator;

import javax.crypto.Mac;

import java.io.IOException;

import static sml.Registers.Register.*;

public class RegistersTest {
    private Registers registers;


    @BeforeEach
    void setUp() {
    registers = new Registers();
    }

    @AfterEach
    void tearDown(){
        registers = null;
    }

    @Test
    void testGetRegister() {
        registers.set(ESI, 701);
        Assertions.assertTrue(701 == registers.get(ESI));
    }

    @Test
    public void testGetRegisterTwo() throws IOException {
        Machine machine = new Machine(registers);
        Translator translator = new Translator("test/data/addInstruction.sml");
        translator.readAndTranslate(machine.getLabels(), machine.getProgram());
        machine.execute();
        Registers regs = machine.getRegisters();
        int expectedValue = 131;
        int actualValue = regs.get(EAX);
        Assertions.assertTrue(expectedValue == actualValue);
    }

    @Test
    void testSetRegister() {
        registers.set(ESI, 701);
        Assertions.assertTrue(701 == registers.get(ESI));
    }





}
