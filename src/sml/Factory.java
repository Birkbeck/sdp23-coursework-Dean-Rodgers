package sml;

public interface Factory {

    Factory instance = null;

    /**
     * Returns an instruction object using the <i>name</i> of this instruction and objects of its respective constructor
     */
    Instruction getInstruction(String name, Object... args);
}
