package sml.instruction;

import sml.Instruction;
import sml.Labels;
import sml.Machine;
import sml.RegisterName;

import java.util.Objects;

/**
 * Representation of the jump instruction.
 * </p>
 * If the contents of register s is not zero, then make the statement labeled L the next statement to execute.
 */
public class JnzInstruction extends Instruction {

    private final RegisterName source;
    private final String labelJump;

    public static final String OP_CODE = "jnz";

    /**
     * Creates a new OutInstruction.
     The line in the program for this instruction is: labal (optional) opcode (out) register (source)
     * </p>
     * @param label creates a name for the program. This is an optional parameter.
     * @param source creates operand for a register. Name of a register.
     */
    public JnzInstruction(String label, RegisterName source, String labelJump) {
        super(label, OP_CODE);
        this.source = source;
        this.labelJump = labelJump;
    }

    /**
     * Executes the subtraction arithmetic operation. Stores the result of this operation in the given register.
     * </p>
     * @param m the machine i.e. the context where the instruction is run.
     * @return resets NORMAL_PROGRAM_COUNTER_UPDATE after the instruction has executed.
     */
    @Override
    public int execute(Machine m) {
        Labels labels = m.getLabels();
        int jumpInstructionAddress = labels.getAddress(labelJump);
        int s = m.getRegisters().get(source);
        if (s != 0) {
            s--;
            return jumpInstructionAddress;
        }
        return NORMAL_PROGRAM_COUNTER_UPDATE;
    }

    @Override
    public String toString() {
        return getLabelString() + getOpcode() + " " + source + " " + labelJump;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JnzInstruction that = (JnzInstruction) o;
        return Objects.equals(source, that.source) && Objects.equals(labelJump, that.labelJump);
    }

    @Override
    public int hashCode() {
        return Objects.hash(source, labelJump);
    }
}
