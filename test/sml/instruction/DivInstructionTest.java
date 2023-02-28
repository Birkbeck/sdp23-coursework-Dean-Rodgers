package sml.instruction;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sml.Instruction;
import sml.Machine;
import sml.Registers;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static sml.Registers.Register.EAX;
import static sml.Registers.Register.EBX;

public class DivInstructionTest {
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
        registers.set(EAX, 30);
        registers.set(EBX, 5);
        Instruction instruction = new DivInstruction(null, EAX, EBX);
        instruction.execute(machine);
        Assertions.assertEquals(6, machine.getRegisters().get(EAX));
    }

    @Test
    void executeValidTwo() {
        registers.set(EAX, 5);
        registers.set(EBX, 1);
        Instruction instruction = new DivInstruction(null, EAX, EBX);
        instruction.execute(machine);
        Assertions.assertEquals(5, machine.getRegisters().get(EAX));
    }

    @Test
    void executeNotValidThrowsIllgalArgumentException() {
        registers.set(EAX, 5);
        registers.set(EBX, 0);
        Instruction instruction = new DivInstruction(null, EAX, EBX);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            instruction.execute(machine);;
        });
        String exceptedMessage = "Cannot divide by zero";
        String actualMessage = exception.getMessage();
        Assertions.assertEquals(exceptedMessage, actualMessage);
    }


}
