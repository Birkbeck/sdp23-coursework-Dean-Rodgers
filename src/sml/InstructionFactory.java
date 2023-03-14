package sml;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class InstructionFactory implements Factory {

    private BeanFactory factory;

    private static InstructionFactory instance = null;

    static {
        instance = new InstructionFactory();
    }

    private InstructionFactory() {
        try {
            factory = getBeanFactory();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static InstructionFactory getInstance() {
        return instance;
    }

    public Instruction getInstruction(String name, Object... args) {
        try {
            return (Instruction) getBeanFactory().getBean(name, args);
        } catch (Exception e) {
            e.printStackTrace();
        };
        return null;
    }


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
