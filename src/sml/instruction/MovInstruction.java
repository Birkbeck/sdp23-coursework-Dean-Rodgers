package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

import java.util.Objects;

/**
 * Representation of the move instruction.
 * </p>
 * Moves an integer to a specified register.
 */
public class MovInstruction extends Instruction {

    private final RegisterName result;
    private final Integer value;

    public static final String OP_CODE = "mov";

    /**
     * Creates a new MovInstruction.
     * The line in the program for this instruction is: labal (optional) opcode (mov) register (result) value
     * </p>
     * @param label creates a name for the program. This is an optional parameter.
     * @param result creates operand for a register. Name of the register.
     * @param value creates value to be stored.
     */
    public MovInstruction(String label, RegisterName result, Integer value){
        super(label, OP_CODE);
        this.result = result;
        this.value = value;
    }

    /**
     * Executes the move instruction operation. Stores the result of this operation in the given register.
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
        return "MovInstruction{" +
                "result=" + result +
                ", value=" + value +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovInstruction that = (MovInstruction) o;
        return Objects.equals(result, that.result) && Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(result, value);
    }
}
