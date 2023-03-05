package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

import java.util.Objects;


/**
 * Representation of the subtraction arithmetic.
 * <p>
 * Subtracts two integers values and stores result in specified register.
 */
public class SubInstruction extends Instruction {
    private final RegisterName result;
    private final RegisterName source;

    public static final String OP_CODE = "sub";

    /**
     * Constructor: creates a new SubInstruction.
     * <i>label</i> provides an optional name for the instruction. <i></i>result and <i>source</i> are registers
     * in the machine memory.
     * </p>
     * @param label creates a name for the arithmetic instruction. This is an optional parameter.
     * @param result creates operand for a register. Result of instruction of stored in this register.
     * @param source creates operand for a register.
     */
    public SubInstruction(String label, RegisterName result, RegisterName source){
        super(label, OP_CODE);
        this.result = result;
        this.source = source;
    }

    /**
     * Executes the subtraction operation. Subtracts <i>result</i> from <i>source</i>, and
     * the result of this operation in <i>result</i> register.
     * </p>
     * @param m the machine i.e. the context where the instruction is run.
     * @return resets NORMAL_PROGRAM_COUNTER_UPDATE after the instruction has executed.
     */
    @Override
    public int execute(Machine m) {
        int value1 = m.getRegisters().get(result);
        int value2 = m.getRegisters().get(source);
        m.getRegisters().set(result, value1 - value2);
        return NORMAL_PROGRAM_COUNTER_UPDATE;
    }

    @Override
    public String toString() {
        return getLabelString() + getOpcode() + " " + result + " " + source;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!(o instanceof SubInstruction other)) return false;
        return Objects.equals(result, other.result) && Objects.equals(source, other.source);
    }

    @Override
    public int hashCode() {
        return Objects.hash(result, source);
    }
}
