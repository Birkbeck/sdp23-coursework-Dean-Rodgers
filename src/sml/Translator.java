package sml;

import sml.instruction.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;

import static sml.Registers.Register;

/**
 * This class translates a sml file into List of <i>Instruction</i> and Map of <i>Labels</i> so that a <i>Machine</i> can
 * execute each <i>Instruction</i>
 * <p>
 * The translator of a <b>S</b><b>M</b>al<b>L</b> program.
 */
public final class Translator {

    private final String fileName; // source file of SML code

    // line contains the characters in the current line that's not been processed yet
    private String line = "";

    /**
     * Constructor: creates a Translator object
     * @param fileName the filename of the sml file. A command line argument args[0] is passed here.
     */
    public Translator(String fileName) {
        this.fileName =  fileName;
    }

    // translate the small program in the file into lab (the labels) and
    // prog (the program)
    // return "no errors were detected"
    /**
     * Translate the small program in the file found in the command line argument into the labels and programme.
     * </p>
     * @param labels pass getLabels() method found in the Machine class to create an empty of Map to store label and its address
     * @param program pass getProgram() method found in the Machine class to create empty of List<Instruction>
     * @throws IOException when programme file read error
     */
    public void readAndTranslate(Labels labels, List<Instruction> program) throws IOException {
        try (var sc = new Scanner(new File(fileName), StandardCharsets.UTF_8)) {
            labels.reset();
            program.clear();

            // Each iteration processes line and reads the next input line into "line"
            while (sc.hasNextLine()) {
                line = sc.nextLine();
                String label = getLabel();

                Instruction instruction = getInstruction(label);
                if (instruction != null) {
                    if (label != null)
                        labels.addLabel(label, program.size());
                    program.add(instruction);
                }
            }
        }
    }

    /**
     * Translates the current line into an instruction with the given label
     * </p>
     * @param label the instruction label
     * @return the new instruction
     * <p>
     * The input line should consist of a single SML instruction,
     * with its label already removed.
     */
    private Instruction getInstruction(String label) {

        if (line.isEmpty())
            return null;

        String opcode = scan();
        char firstChar = opcode.charAt(0);
        firstChar = Character.toUpperCase(firstChar);
        StringBuffer buffer = new StringBuffer(opcode);
        buffer.setCharAt(0,firstChar);
        String className = "sml.instruction." + buffer + "Instruction";

        try {
            Class<?> c = Class.forName(className);
            System.out.println("this is class name: " + c.getName());
            Constructor[] constructors = c.getConstructors();
            Constructor constr = constructors[0];
            Class<?>[] paramCons = constr.getParameterTypes();

            for(Constructor<?> con: constructors) {
                System.out.println(con.getName());
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

//        switch (opcode) {
//            case AddInstruction.OP_CODE -> {
//                String r = scan();
//                String s = scan();
//                return new AddInstruction(label, Register.valueOf(r), Register.valueOf(s));
//            }
//            case SubInstruction.OP_CODE -> {
//                String r = scan();
//                String s = scan();
//                return new SubInstruction(label, Register.valueOf(r), Register.valueOf(s));
//            }
//            case MulInstruction.OP_CODE -> {
//                String r = scan();
//                String s = scan();
//                return new MulInstruction(label, Register.valueOf(r), Register.valueOf(s));
//            }
//            case DivInstruction.OP_CODE -> {
//                String r = scan();
//                String s = scan();
//                return new DivInstruction(label, Register.valueOf(r), Register.valueOf(s));
//            }
//            case MovInstruction.OP_CODE -> {
//                String r = scan();
//                String value = scan();
//                return new MovInstruction(label, Register.valueOf(r), Integer.valueOf(value));
//            }
//            case OutInstruction.OP_CODE -> {
//                String s = scan();
//                return new OutInstruction(label, Register.valueOf(s));
//            }
//            case JnzInstruction.OP_CODE -> {
//                String s = scan();
//                String jumpLabel = scan();
//                return new JnzInstruction(label, Register.valueOf(s), jumpLabel);
//            }
//
//            // TODO: add code for all other types of instructions
//
//            // TODO: Then, replace the switch by using the Reflection API
//
//            // TODO: Next, use dependency injection to allow this machine class
//            //       to work with different sets of opcodes (different CPUs)
//
//            default -> {
//                System.out.println("Unknown instruction: " + opcode);
//            }
//        }
        return null;
    }

    /**
     * Method calls the scan() method to return a word in a programme line
     * @return label if found in a programme line, else null if no label found
     */
    private String getLabel() {
        String word = scan();
        if (word.endsWith(":"))
            return word.substring(0, word.length() - 1);

        // undo scanning the word
        line = word + " " + line;
        return null;
    }

    /*
     * Return the first word of line and remove it from line.
     * If there is no word, return "".
     */

    /**
     * Scans a programme line to isolate each word found in the line.
     * @return the first word of line and remove it from line, if there is no word, return ""
     */
    private String scan() {
        line = line.trim();

        for (int i = 0; i < line.length(); i++)
            if (Character.isWhitespace(line.charAt(i))) {
                String word = line.substring(0, i);
                line = line.substring(i);
                return word;
            }

        return line;
    }
}