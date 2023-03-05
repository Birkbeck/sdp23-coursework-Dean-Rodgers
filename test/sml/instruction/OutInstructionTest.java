package sml.instruction;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sml.Instruction;
import sml.Machine;
import sml.Registers;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static sml.Registers.Register.*;
import static sml.Registers.Register.EBX;


public class OutInstructionTest {
    private Machine machine;
    private Registers registers;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;


    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent));
        machine = new Machine(new Registers());
        registers = machine.getRegisters();
        //...
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
        machine = null;
        registers = null;
    }

    @Test
    void executeValid() {
        registers.set(EAX, 5);
        Instruction instruction = new OutInstruction(null, EAX);
        instruction.execute(machine);
        Assertions.assertEquals(String.valueOf(5), outContent.toString().trim());
    }

    @Test
    void executeValidTwo() {
        registers.set(ECX, 60);
        Instruction instruction = new OutInstruction(null, ECX);
        instruction.execute(machine);
        Assertions.assertEquals(String.valueOf(60), outContent.toString().trim());
    }

    @Test
    void executeValidThree() {
        registers.set(ECX, 60);
        Instruction instruction = new OutInstruction(null, ECX);
        instruction.execute(machine);
        Assertions.assertNotEquals(String.valueOf(80), outContent.toString().trim());
    }

    @Test
    void testToString(){
        Instruction instruction = new OutInstruction("f1", EAX);
        Assertions.assertEquals(instruction.toString(), "f1: out EAX");
    }

    @Test
    void testEqualsMethod() {
        Instruction instruction1 = new OutInstruction(null, EAX);
        Instruction instruction2 = new OutInstruction(null, EAX);
        Assertions.assertTrue(instruction1.equals(instruction2));
    }

    @Test
    void testEqualsMethodTwo() {
        Instruction instruction1 = new OutInstruction(null, EBX);
        Instruction instruction2 = new OutInstruction(null, ECX);
        Assertions.assertFalse(instruction1.equals(instruction2));
    }




}
