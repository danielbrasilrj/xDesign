package uk.co.xdesign.munros.helper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class FileUnitTest {

    @Test
    public void readNonExistentFile() throws IOException, URISyntaxException {
        assertNull(FileUtil.getReader("unknown"));
    }

    @Test
    public void readExistentFile() throws IOException, URISyntaxException {
        assertNotNull(FileUtil.getReader("munrotab_for_test.csv"));
    }
}
