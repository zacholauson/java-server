package test.logging.loggers;

import main.logging.ILogger;
import main.logging.loggers.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class LoggerTest {
    @Test
    public void addToLoggerTest() {
        ILogger logger = new Logger();
        assertEquals(0, logger.getEntries().length());
        logger.addEntry("Entry 1");
        assertEquals(7, logger.getEntries().length());
    }

    @Test
    public void getLoggerEntries() {
        ILogger logger = new Logger();
        logger.addEntry("New Entry");
        assertEquals("New Entry", logger.getEntries());
    }
}
