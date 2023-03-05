package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

import java.util.Objects;


/**
 * Representation of the divide instruction for SML.
 * </p>
 * Divide two integers and store result in specified register.
 */
public class DivInstruction extends Instruction {
    private final RegisterName result;
    private final RegisterName source;

    public static final String OP_CODE = "div";

    /**
     * Constructor: Creates a new DivInstruction.
     <i>label</i> provides an optional name for the instruction. <i>result</i> and <i>source</i> are registers names.
     * </p>
     * @param label creates a name for the program. This is an optional parameter.
     * @param result creates operand for a register. Name of the register to store result
     * @param source creates operand for a register. Name of a register.
     */
    public DivInstruction(String label, RegisterName result, RegisterName source) {
        super(label, OP_CODE);
        this.result = result;
        this.source = source;
    }

    /**
     * Executes the division arithmetic operation. Stores the result of this operation in the given register.
     * </p>
     * @param m the machine i.e. the context where the instruction is run.
     * @return resets NORMAL_PROGRAM_COUNTER_UPDATE after the instruction has executed.
     */
    @Override
    public int execute(Machine m) throws IllegalArgumentException {
        int value1 = m.getRegisters().get(result);
        int value2 = m.getRegisters().get(source);
        if (value2 == 0) {
            throw new IllegalArgumentException("Cannot divide by zero");
        }
        m.getRegisters().set(result, value1 / value2);
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
        if (!(o instanceof DivInstruction other)) return false;
        return Objects.equals(result, other.result) && Objects.equals(source, other.source);
    }

    @Override
    public int hashCode() {
        return Objects.hash(result, source);
    }
}
