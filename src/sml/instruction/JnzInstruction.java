package sml.instruction;

import sml.Instruction;
import sml.Labels;
import sml.Machine;
import sml.RegisterName;

import java.util.Objects;

/**
 * Representation of the jump instruction.
 * </p>
 * If the contents of register s is not zero, then make the statement
 * labeled L the next statement to execute.
 */
public class JnzInstruction extends Instruction {
    private final RegisterName source;
    private final String labelJump;

    public static final String OP_CODE = "jnz";

    /**
     * Constructor: Creates a new JnzInstruction.
     * <i>label</i> provides an optional name for the instruction.
     * <i>source</i> is register name and <i>labelJump</i> is the instruction to execute.
     * </p>
     * @param label creates a name for the program. This is an optional parameter.
     * @param source creates operand for a register. Name of a register.
     * @param labelJump creates operand for the next statement to execute
     */
    public JnzInstruction(String label, RegisterName source, String labelJump) {
        super(label, OP_CODE);
        this.source = source;
        this.labelJump = labelJump;
    }

    /**
     * Executes the jump operation. Finds the programme address of next instruction to
     * execute by the name of the label in jump instruction. If the <i>source<i/> value is
     * not zero then return
     * </p>
     * @param m the machine i.e. the context where the instruction is run.
     * @return  the address of the jump instruction if <i>source</i> not zero, or
     *          resets NORMAL_PROGRAM_COUNTER_UPDATE after the instruction has executed.
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
        if (!(o instanceof JnzInstruction other)) return false;
        return Objects.equals(source, other.source) && Objects.equals(labelJump, other.labelJump);
    }

    @Override
    public int hashCode() {
        return Objects.hash(source, labelJump);
    }
}
