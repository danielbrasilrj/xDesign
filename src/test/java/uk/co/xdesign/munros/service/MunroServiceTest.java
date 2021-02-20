package uk.co.xdesign.munros.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import uk.co.xdesign.munros.dto.MunroDTO;
import uk.co.xdesign.munros.helper.MunroHelper;
import uk.co.xdesign.munros.service.impl.MunroService;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import static org.junit.Assert.assertEquals;import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class MunroServiceTest {

    @Mock
    private MunroHelper munroHelper;

    @InjectMocks
    private MunroService munroService;

    @Test
    public void loadSuccessfully() throws IOException, URISyntaxException {
        given(munroHelper.getFileName()).willReturn("munrotab_for_test.csv");
        List<MunroDTO> munroList = munroService.loadFromCsv();
        assertEquals(19, munroList.size());
    }
}
