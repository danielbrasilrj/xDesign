package uk.co.xdesign.munros.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import uk.co.xdesign.munros.dto.MunroCategory;
import uk.co.xdesign.munros.dto.MunroDTO;
import uk.co.xdesign.munros.helper.impl.MunroHelper;
import uk.co.xdesign.munros.service.impl.MunroService;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class MunroServiceTest {

    @Mock
    private MunroHelper munroHelper;

    @InjectMocks
    private MunroService munroService;

    @Before
    public void setup() {
        List<MunroDTO> munroList = Arrays.asList(
            new MunroDTO("MUNRO_1", "100", MunroCategory.MUN),
            new MunroDTO("MUNRO_2", "200", MunroCategory.MUN),
            new MunroDTO("MUNRO_3", "100", MunroCategory.TOP),
            new MunroDTO("MUNRO_4", "200", MunroCategory.TOP),
            new MunroDTO("MUNRO_5", "300", MunroCategory.MUN),
            new MunroDTO("MUNRO_6", "400", MunroCategory.MUN),
            new MunroDTO("MUNRO_7", "300", MunroCategory.TOP),
            new MunroDTO("MUNRO_8", "400", MunroCategory.TOP),
            new MunroDTO("MUNRO_9", "500", MunroCategory.MUN),
            new MunroDTO("MUNRO_10", "600", MunroCategory.TOP)
        );
        given(munroHelper.getMunroList()).willReturn(munroList);
    }

    @Test
    public void loadSuccessfully() throws IOException, URISyntaxException {
        given(munroHelper.getFileName()).willReturn("munrotab_for_test.csv");
        List<MunroDTO> munroList = munroService.loadFromCsv();
        assertEquals(19, munroList.size());
    }

    @Test
    public void findByFilterNull() {
        List<MunroDTO> result = munroService.findByFilter(null);
        assertEquals(10, result.size());
    }
}
