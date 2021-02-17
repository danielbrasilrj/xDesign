package uk.co.xdesign.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import uk.co.xdesign.munros.dto.MunroDTO;
import uk.co.xdesign.munros.service.impl.MunroService;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class MunroServiceTest {

    @InjectMocks
    private MunroService munroService;

    @Test(expected = FileNotFoundException.class)
    public void errorFileNotExist() throws IOException, URISyntaxException {
        munroService.loadFromCsv("unknown");
    }

    @Test
    public void loadSuccessfully() throws IOException, URISyntaxException {
        List<MunroDTO> munroList = munroService.loadFromCsv("munrotab_for_test.csv");
        assertEquals(18, munroList.size());
    }
}
