import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sml.*;

import java.io.IOException;
import java.util.List;

public class TranslatorTest {

    private Machine machine;
    private Registers registers;
    private String filename;
    private Translator translator;
    private Labels labels;


    @BeforeEach
    void setUp() {
        labels = new Labels();
        filename = "test/data/factorial6.sml";
        translator = new Translator(filename);
        registers = new Registers();
        machine = new Machine(registers);
    }

    @AfterEach
    void tearDown(){
        registers = null;
        machine = null;
        labels = null;
    }

    @Test
    void testReadAndTranslate() throws IOException {
        translator.readAndTranslate(machine.getLabels(), machine.getProgram());
        List<Instruction> program = machine.getProgram();
        System.out.println(program.size());

    }



}
