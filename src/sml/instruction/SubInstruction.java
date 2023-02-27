package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

import java.util.Objects;


/**
 * Representation of the subtraction arithmetic.
 * <p>
 * In this instruction the OP_CODE is represented by "sub".
 */
public class SubInstruction extends Instruction {

    private final RegisterName result;
    private final RegisterName source;

    public static final String OP_CODE = "sub";

    /**
     * Creates a new SubInstruction.
     * label provides an optional name for the instruction. result and source are registers in the machine memory.
     * In SML, the registers are represented as String variables.
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
     * Executes the subtraction arithmetic operation. Stores the result of this operation in the given register.
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
        SubInstruction that = (SubInstruction) o;
        return Objects.equals(result, that.result) && Objects.equals(source, that.source);
    }

    @Override
    public int hashCode() {
        return Objects.hash(result, source);
    }
}
