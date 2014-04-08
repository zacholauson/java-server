package test.parsers;

import main.parsers.ArgumentsParser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class ArgumentsParserTest {
    private ArgumentsParser argumentsParser = new ArgumentsParser(new String[] {"-testFlag", "testValue"});

    @Test
    public void returnsPortFromArguments() {
        ArgumentsParser argumentsParser = new ArgumentsParser(new String[] {"-p", "8000"});
        assertEquals(8000, argumentsParser.getPort());
    }

    @Test
    public void returnsDefaultPort() {
        assertEquals(5000, argumentsParser.getPort());
    }

    @Test
    public void returnsDirectoryFromArguments() {
        ArgumentsParser argumentsParser = new ArgumentsParser(new String[] {"-d", "/"});
        assertEquals("/", argumentsParser.getDirectory());
    }

    @Test
    public void returnsDefaultDirectory() {
        assertEquals(System.getProperty("user.dir"), argumentsParser.getDirectory());
    }

    @Test
    public void returnsCorrectIndexForFlag() {
        assertEquals(0, argumentsParser.getFlagIndex("-testFlag"));
    }
}
