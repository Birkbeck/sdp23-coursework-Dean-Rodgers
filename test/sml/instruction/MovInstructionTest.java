package sml.instruction;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sml.Instruction;
import sml.Machine;
import sml.Registers;

import static sml.Registers.Register.*;

public class MovInstructionTest {
    private Machine machine;

    @BeforeEach
    void setUp() {
        machine = new Machine(new Registers());
    }

    @AfterEach
    void tearDown() {
        machine = null;
    }

    @Test
    void executeValid() {
        Instruction instruction = new MovInstruction(null, EAX, 145);
        instruction.execute(machine);
        Assertions.assertEquals(145, machine.getRegisters().get(EAX));
    }

    @Test
    void testToString(){
        Instruction instruction = new MovInstruction("f5", ECX, 10);
        Assertions.assertEquals(instruction.toString(), "f5: mov ECX 10");

    }

    @Test
    void testEquals() {
        Instruction instruction1 = new MovInstruction(null, ECX, 10);
        Instruction instruction2 = new MovInstruction(null, ECX, 10);
        Assertions.assertTrue(instruction1.equals(instruction2));
    }

    @Test
    void testEqualsTwo() {
        Instruction instruction1 = new MovInstruction(null, ECX, 10);
        Instruction instruction2 = new MovInstruction(null, EBX, 10);
        Assertions.assertFalse(instruction1.equals(instruction2));
    }



}
