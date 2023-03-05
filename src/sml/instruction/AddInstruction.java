package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

import java.util.Objects;

// TODO: write a JavaDoc for the class
/**
 * Representation of the addition instruction for SML.
 * <p>
 * Add two integers and store result in specified register.
 */
public class AddInstruction extends Instruction {
	private final RegisterName result;
	private final RegisterName source;

	public static final String OP_CODE = "add";

	/**
	 * Constructor: creates a new AddInstruction.
	 * <i>label</i> provides an optional name for the instruction. <i>result</i> and <i>source</i> are registers names.
	 * </p>
	 * @param label creates a name for the arithmetic instruction. This is an optional parameter.
	 * @param result creates operand for a register. Result of instruction2 stored in this register.
	 * @param source creates operand for a register.
	 */
	public AddInstruction(String label, RegisterName result, RegisterName source) {
		super(label, OP_CODE);
		this.result = result;
		this.source = source;
	}

	/**
	 * Executes the addition instruction. Stores the result of this operation in the given register.
	 * </p>
	 * @param m the machine i.e. the context where the instruction is run.
	 * @return resets NORMAL_PROGRAM_COUNTER_UPDATE after the instruction has executed.
	 */
	@Override
	public int execute(Machine m) {
		int value1 = m.getRegisters().get(result);
		int value2 = m.getRegisters().get(source);
		m.getRegisters().set(result, value1 + value2);
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
		if (!(o instanceof AddInstruction other)) return false;
		return Objects.equals(result, other.result) && Objects.equals(source, other.source);
	}

	@Override
	public int hashCode() {
		return Objects.hash(result, source);
	}
}
