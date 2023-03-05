package sml.instruction;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sml.Instruction;
import sml.Machine;
import sml.Registers;

import static sml.Registers.Register.*;

public class MulInstructionTest {
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
    void executeValid() {
        registers.set(EAX, 5);
        registers.set(EBX, 6);
        Instruction instruction = new MulInstruction(null, EAX, EBX);
        instruction.execute(machine);
        Assertions.assertEquals(30, machine.getRegisters().get(EAX));
    }

    @Test
    void executeValidTwo() {
        registers.set(EAX, -5);
        registers.set(EBX, 6);
        Instruction instruction = new MulInstruction(null, EAX, EBX);
        instruction.execute(machine);
        Assertions.assertEquals(-30, machine.getRegisters().get(EAX));
    }

    @Test
    void testToString(){
        Instruction instruction = new MulInstruction("f1", EAX, EBX);
        Assertions.assertEquals(instruction.toString(), "f1: mul EAX EBX");
    }

    @Test
    void testEqualsMethod() {
        Instruction instruction1 = new MulInstruction(null, EAX, EBX);
        Instruction instruction2 = new MulInstruction(null, EAX, EBX);
        Assertions.assertTrue(instruction1.equals(instruction2));
    }

    @Test
    void testEqualsMethodTwo() {
        Instruction instruction1 = new MulInstruction(null, EAX, EBX);
        Instruction instruction2 = new MulInstruction(null, ECX, EBX);
        Assertions.assertFalse(instruction1.equals(instruction2));
    }
}
