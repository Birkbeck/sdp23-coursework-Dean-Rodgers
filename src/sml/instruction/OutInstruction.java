package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

import java.util.Objects;

/**
 * Representation of the out instruction.
 * </p>
 * Prints out the value in the specified register.
 */
public class OutInstruction extends Instruction {
    private final RegisterName source;

    public static final String OP_CODE = "out";

    /**
     * Creates a new OutInstruction.
     The line in the program for this instruction is: labal (optional) opcode (out) register (source)
     * </p>
     * @param label creates a name for the program. This is an optional parameter.
     * @param source creates operand for a register. Name of a register.
     */
    public OutInstruction(String label, RegisterName source) {
        super(label, OP_CODE);
        this.source = source;
    }

    /**
     * Executes the subtraction arithmetic operation. Stores the result of this operation in the given register.
     * </p>
     * @param m the machine i.e. the context where the instruction is run.
     * @return resets NORMAL_PROGRAM_COUNTER_UPDATE after the instruction has executed.
     */
    @Override
    public int execute(Machine m) {
        long value = m.getRegisters().get(source);
        System.out.println(value);
        return NORMAL_PROGRAM_COUNTER_UPDATE;
    }

    @Override
    public String toString() {
        return getLabelString() + getOpcode() + " " + source;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OutInstruction that = (OutInstruction) o;
        return Objects.equals(source, that.source);
    }

    @Override
    public int hashCode() {
        return Objects.hash(source);
    }
}
