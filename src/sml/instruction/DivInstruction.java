package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

import java.util.Objects;


/**
 * Representation of the divide instruction.
 * </p>
 * Divide two integers and store result in specified register.
 */
public class DivInstruction extends Instruction {
    private final RegisterName result;
    private final RegisterName source;

    public static final String OP_CODE = "div";

    /**
     * Creates a new DivInstruction.
     The line in the program for this instruction is: labal (optional) opcode (div) register (result) register (source)
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
        if (value2 == 0){
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
        DivInstruction that = (DivInstruction) o;
        return Objects.equals(result, that.result) && Objects.equals(source, that.source);
    }

    @Override
    public int hashCode() {
        return Objects.hash(result, source);
    }
}
