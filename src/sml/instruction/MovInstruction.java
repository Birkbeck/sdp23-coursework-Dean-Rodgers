package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

import java.util.Objects;

/**
 * Representation of the move instruction.
 * </p>
 * Moves an integer value to a specified register.
 */
public class MovInstruction extends Instruction {
    private final RegisterName result;
    private final Integer value;

    public static final String OP_CODE = "mov";

    /**
     * Constructor: creates a new MovInstruction.
     * The line in the program for this instruction is: labal (optional) opcode (mov) register (result) value
     * </p>
     * @param label creates a name for the program. This is an optional parameter.
     * @param result creates operand for a register. Name of the register.
     * @param value creates value to be stored.
     */
    public MovInstruction(String label, RegisterName result, Integer value) {
        super(label, OP_CODE);
        this.result = result;
        this.value = value;
    }

    /**
     * Executes the move instruction operation. Stores <i>value<i/> in the given register <i>result</i>.
     * </p>
     * @param m the machine i.e. the context where the instruction is run.
     * @return resets NORMAL_PROGRAM_COUNTER_UPDATE after the instruction has executed.
     */
    @Override
    public int execute(Machine m) {
        m.getRegisters().set(result, value);
        return NORMAL_PROGRAM_COUNTER_UPDATE;
    }

    @Override
    public String toString() {
        return getLabelString() + getOpcode() + " " + result + " " + value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!(o instanceof MovInstruction other)) return false;
        return Objects.equals(result, other.result) && Objects.equals(value, other.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(result, value);
    }
}
