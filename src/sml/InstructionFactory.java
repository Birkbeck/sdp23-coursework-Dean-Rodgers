package sml;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Represents a factory class to generate <i>instruction</i> objects at runtime.
 */
public class InstructionFactory implements Factory {

    private BeanFactory factory;

    private static InstructionFactory instance;

    static {
        instance = new InstructionFactory();
    }

    /**
     * Constructor: for the <i>InstructionFactory</i> to implement the singleton design pattern.
     */
    private InstructionFactory() {
        try {
            factory = getBeanFactory();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Implements the singleton pattern for <i>InstructionFactory</i>.
     * @return instance of the bean factory associated with the bean properties
     */
    public static InstructionFactory getInstance() {
        return instance;
    }

    /**
     *
     * @param name
     * @param args
     * @return
     */
    public Instruction getInstruction(String name, Object... args) {
        try {
            return (Instruction) getBeanFactory().getBean(name, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Loads the configuration options to get the bean factory.
     * @return factory with all the subclasses of instruction located in the propertiers file
     * @throws IOException if cannot the bean properties located in resource folders
     */
    private BeanFactory getBeanFactory() throws IOException {
        // Get the bean factory
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        // create a definition reader
        var rdr = new PropertiesBeanDefinitionReader(factory);
        // load the configuration options
        Properties props = new Properties();
        props.load(new FileInputStream("resources/beans.properties"));
        rdr.registerBeanDefinitions(props);
        return factory;

    }
}
