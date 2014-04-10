package test.logging.loggers;

import main.logging.loggers.SystemLogger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class SystemLoggerTest {
    @Test
    public void createNewSystemLoggerTest() {
        SystemLogger logger = new SystemLogger();
        assertTrue(logger.logs.isEmpty());
    }

    @Test
    public void canAddEntriesToLogsTest() {
        SystemLogger logger = new SystemLogger();
        logger.addEntry("ERROR");
        assertEquals(logger.logs.size(), 1);
    }

    @Test
    public void canGetEntriesFromLogs() {
        SystemLogger logger = new SystemLogger();
        assertEquals(logger.getEntries(), "");
        logger.addEntry("ERROR");
        assertEquals(26, logger.getEntries().length());
        logger.addEntry("ANOTHER ERROR");
        assertEquals(60, logger.getEntries().length());
    }
}
