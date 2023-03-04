package sml.instruction;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sml.Instruction;
import sml.Labels;
import sml.Machine;
import sml.Registers;

import java.io.IOException;

import static sml.Registers.Register.EAX;

public class JnzInstructionTest {
    private Machine machine;
    private Registers registers;

    @BeforeEach
    void setUp() {
        machine = new Machine(new Registers());
        registers = machine.getRegisters();
        //...
    }

    @AfterEach
    void tearDown() {
        machine = null;
        registers = null;
    }

    @Test
    void returnCorrectJumpInstructionNumber() {
        Labels labels = machine.getLabels();
        labels.addLabel("f3",3);
        registers.set(EAX, 6);
        Instruction instruction = new JnzInstruction(null, EAX, "f3");
        int returnValue = instruction.execute(machine);
        Assertions.assertEquals(3, returnValue);
    }

    @Test
    // static member variable NORMAL_PROGRAM_COUNTER_UPDATE is set to -1
    // if no jump instruction then execute method returns -1
    void testIfExecuteMethodReturnsNormalProgramCounterUpdateForRegisterWithValueZero(){
        Labels labels = machine.getLabels();
        labels.addLabel("f3",3);
        registers.set(EAX, 0);
        Instruction instruction = new JnzInstruction(null, EAX, "f3");
        int returnValue = instruction.execute(machine);
        Assertions.assertEquals(-1, returnValue);
    }

    @Test
    void countTheTotalNumberOfInstructionsInJumpProgram() throws IOException {
        System.out.println();
    }


}
