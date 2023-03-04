package sml.instruction;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sml.*;

import java.io.IOException;
import java.util.List;

import static sml.Instruction.NORMAL_PROGRAM_COUNTER_UPDATE;
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
    // expected number of instructions executed in the .sml file is X
    // which calculates the factorial of six
    void countTheTotalNumberOfInstructionsInJumpProgram()  {
        int totalNumberOfInstructionsExecuted = 0;
        try {
            Translator t = new Translator("test/data/sml.txt");
            t.readAndTranslate(machine.getLabels(), machine.getProgram());
        } catch (IOException e) {
            System.out.println(e);
        }

        List<Instruction> program = machine.getProgram();
        int programCounter = 0;
        registers.clear();
        while (programCounter < program.size()) {
            Instruction ins = program.get(programCounter);
            int programCounterUpdate = ins.execute(machine);
            totalNumberOfInstructionsExecuted ++;
            programCounter = (programCounterUpdate == NORMAL_PROGRAM_COUNTER_UPDATE)
                    ? programCounter + 1
                    : programCounterUpdate;
        }

        Assertions.assertEquals(22, totalNumberOfInstructionsExecuted);
    }


}
