package uk.co.xdesign.munros.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import uk.co.xdesign.munros.dto.*;
import uk.co.xdesign.munros.helper.impl.MunroHelper;
import uk.co.xdesign.munros.service.impl.MunroService;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;

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
            new MunroDTO("MUNRO_10", "600", MunroCategory.TOP),
            new MunroDTO("MUNRO_11", "600", null),
            new MunroDTO("MUNRO_12", "10", MunroCategory.MUN),
            new MunroDTO("MUNRO_13", "1000", MunroCategory.TOP),
            new MunroDTO("MUNRO_14", "1500", null)
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
        MunroFilter munroFilter = null;

        List<MunroDTO> result = munroService.findByFilter(munroFilter);

        assertEquals(12, result.size());
    }

    @Test
    public void findByFilterCategoryNotInformed() {
        MunroFilter munroFilter = MunroFilterBuilder.builder().build();

        List<MunroDTO> result = munroService.findByFilter(munroFilter);

        assertEquals(12, result.size());
    }

    @Test
    public void findByFilterCategoryMUN() {
        MunroFilter munroFilter = MunroFilterBuilder.builder().byCategory(MunroCategory.MUN).build();

        List<MunroDTO> result = munroService.findByFilter(munroFilter);

        assertEquals(6, result.size());
        assertTrue(result.stream().allMatch(munro -> munro.getPost1997().equals(MunroCategory.MUN.name())));
    }

    @Test
    public void findByFilterCategoryTOP() {
        MunroFilter munroFilter = MunroFilterBuilder.builder().byCategory(MunroCategory.TOP).build();

        List<MunroDTO> result = munroService.findByFilter(munroFilter);

        assertEquals(6, result.size());
        assertTrue(result.stream().allMatch(munro -> munro.getPost1997().equals(MunroCategory.TOP.name())));
    }

    @Test
    public void findByFilterOrderByHeightAsc() {
        MunroFilter munroFilter = MunroFilterBuilder.builder().orderBy(SortBy.Property.HEIGHT_METER, SortBy.Direction.ASC).build();

        List<MunroDTO> result = munroService.findByFilter(munroFilter);

        assertEquals(12, result.size());
        assertThat(result, contains(
                new MunroDTO("MUNRO_12", "10", MunroCategory.MUN),
                new MunroDTO("MUNRO_1", "100", MunroCategory.MUN),
                new MunroDTO("MUNRO_3", "100", MunroCategory.TOP),
                new MunroDTO("MUNRO_2", "200", MunroCategory.MUN),
                new MunroDTO("MUNRO_4", "200", MunroCategory.TOP),
                new MunroDTO("MUNRO_5", "300", MunroCategory.MUN),
                new MunroDTO("MUNRO_7", "300", MunroCategory.TOP),
                new MunroDTO("MUNRO_6", "400", MunroCategory.MUN),
                new MunroDTO("MUNRO_8", "400", MunroCategory.TOP),
                new MunroDTO("MUNRO_9", "500", MunroCategory.MUN),
                new MunroDTO("MUNRO_10", "600", MunroCategory.TOP),
                new MunroDTO("MUNRO_13", "1000", MunroCategory.TOP)
        ));
    }

    @Test
    public void findByFilterOrderByHeightDesc() {
        MunroFilter munroFilter = MunroFilterBuilder.builder().orderBy(SortBy.Property.HEIGHT_METER, SortBy.Direction.DESC).build();

        List<MunroDTO> result = munroService.findByFilter(munroFilter);

        assertEquals(12, result.size());
        assertThat(result, contains(
                new MunroDTO("MUNRO_13", "1000", MunroCategory.TOP),
                new MunroDTO("MUNRO_10", "600", MunroCategory.TOP),
                new MunroDTO("MUNRO_9", "500", MunroCategory.MUN),
                new MunroDTO("MUNRO_6", "400", MunroCategory.MUN),
                new MunroDTO("MUNRO_8", "400", MunroCategory.TOP),
                new MunroDTO("MUNRO_5", "300", MunroCategory.MUN),
                new MunroDTO("MUNRO_7", "300", MunroCategory.TOP),
                new MunroDTO("MUNRO_2", "200", MunroCategory.MUN),
                new MunroDTO("MUNRO_4", "200", MunroCategory.TOP),
                new MunroDTO("MUNRO_1", "100", MunroCategory.MUN),
                new MunroDTO("MUNRO_3", "100", MunroCategory.TOP),
                new MunroDTO("MUNRO_12", "10", MunroCategory.MUN)
        ));
    }

    @Test
    public void findByFilterOrderByNameAsc() {
        MunroFilter munroFilter = MunroFilterBuilder.builder().orderBy(SortBy.Property.NAME, SortBy.Direction.ASC).build();

        List<MunroDTO> result = munroService.findByFilter(munroFilter);

        assertEquals(12, result.size());
        assertThat(result, contains(
                new MunroDTO("MUNRO_1", "100", MunroCategory.MUN),
                new MunroDTO("MUNRO_10", "600", MunroCategory.TOP),
                new MunroDTO("MUNRO_12", "10", MunroCategory.MUN),
                new MunroDTO("MUNRO_13", "1000", MunroCategory.TOP),
                new MunroDTO("MUNRO_2", "200", MunroCategory.MUN),
                new MunroDTO("MUNRO_3", "100", MunroCategory.TOP),
                new MunroDTO("MUNRO_4", "200", MunroCategory.TOP),
                new MunroDTO("MUNRO_5", "300", MunroCategory.MUN),
                new MunroDTO("MUNRO_6", "400", MunroCategory.MUN),
                new MunroDTO("MUNRO_7", "300", MunroCategory.TOP),
                new MunroDTO("MUNRO_8", "400", MunroCategory.TOP),
                new MunroDTO("MUNRO_9", "500", MunroCategory.MUN)
        ));
    }

    @Test
    public void findByFilterOrderByNameDesc() {
        MunroFilter munroFilter = MunroFilterBuilder.builder().orderBy(SortBy.Property.NAME, SortBy.Direction.DESC).build();

        List<MunroDTO> result = munroService.findByFilter(munroFilter);

        assertEquals(12, result.size());
        assertThat(result, contains(
                new MunroDTO("MUNRO_9", "500", MunroCategory.MUN),
                new MunroDTO("MUNRO_8", "400", MunroCategory.TOP),
                new MunroDTO("MUNRO_7", "300", MunroCategory.TOP),
                new MunroDTO("MUNRO_6", "400", MunroCategory.MUN),
                new MunroDTO("MUNRO_5", "300", MunroCategory.MUN),
                new MunroDTO("MUNRO_4", "200", MunroCategory.TOP),
                new MunroDTO("MUNRO_3", "100", MunroCategory.TOP),
                new MunroDTO("MUNRO_2", "200", MunroCategory.MUN),
                new MunroDTO("MUNRO_13", "1000", MunroCategory.TOP),
                new MunroDTO("MUNRO_12", "10", MunroCategory.MUN),
                new MunroDTO("MUNRO_10", "600", MunroCategory.TOP),
                new MunroDTO("MUNRO_1", "100", MunroCategory.MUN)
        ));
    }

    @Test
    public void findByFilterOrderByHeightWithoutDirection() {
        MunroFilter munroFilter = MunroFilterBuilder.builder().orderBy(SortBy.Property.HEIGHT_METER).build();

        List<MunroDTO> result = munroService.findByFilter(munroFilter);

        assertEquals(12, result.size());
        assertThat(result, contains(
                new MunroDTO("MUNRO_12", "10", MunroCategory.MUN),
                new MunroDTO("MUNRO_1", "100", MunroCategory.MUN),
                new MunroDTO("MUNRO_3", "100", MunroCategory.TOP),
                new MunroDTO("MUNRO_2", "200", MunroCategory.MUN),
                new MunroDTO("MUNRO_4", "200", MunroCategory.TOP),
                new MunroDTO("MUNRO_5", "300", MunroCategory.MUN),
                new MunroDTO("MUNRO_7", "300", MunroCategory.TOP),
                new MunroDTO("MUNRO_6", "400", MunroCategory.MUN),
                new MunroDTO("MUNRO_8", "400", MunroCategory.TOP),
                new MunroDTO("MUNRO_9", "500", MunroCategory.MUN),
                new MunroDTO("MUNRO_10", "600", MunroCategory.TOP),
                new MunroDTO("MUNRO_13", "1000", MunroCategory.TOP)
        ));
    }

    @Test
    public void findByFilterOrderByNameWithoutDirection() {
        MunroFilter munroFilter = MunroFilterBuilder.builder().orderBy(SortBy.Property.NAME).build();

        List<MunroDTO> result = munroService.findByFilter(munroFilter);

        assertEquals(12, result.size());
        assertThat(result, contains(
                new MunroDTO("MUNRO_1", "100", MunroCategory.MUN),
                new MunroDTO("MUNRO_10", "600", MunroCategory.TOP),
                new MunroDTO("MUNRO_12", "10", MunroCategory.MUN),
                new MunroDTO("MUNRO_13", "1000", MunroCategory.TOP),
                new MunroDTO("MUNRO_2", "200", MunroCategory.MUN),
                new MunroDTO("MUNRO_3", "100", MunroCategory.TOP),
                new MunroDTO("MUNRO_4", "200", MunroCategory.TOP),
                new MunroDTO("MUNRO_5", "300", MunroCategory.MUN),
                new MunroDTO("MUNRO_6", "400", MunroCategory.MUN),
                new MunroDTO("MUNRO_7", "300", MunroCategory.TOP),
                new MunroDTO("MUNRO_8", "400", MunroCategory.TOP),
                new MunroDTO("MUNRO_9", "500", MunroCategory.MUN)
        ));
    }

    @Test
    public void findByFilterLimitTo5() {
        MunroFilterBuilder builder = MunroFilterBuilder.builder();
        MunroFilter munroFilter = builder.limit(5).build();

        List<MunroDTO> result = munroService.findByFilter(munroFilter);

        assertEquals(5, result.size());
    }

    @Test
    public void findByFilterMinimunHeight() {
        MunroFilter munroFilter = MunroFilterBuilder.builder().byMinimumHeight(450).build();

        List<MunroDTO> result = munroService.findByFilter(munroFilter);

        assertEquals(3, result.size());
    }

    @Test
    public void findByFilterMaximumHeight() {
        MunroFilter munroFilter = MunroFilterBuilder.builder().byMaximumHeight(450).build();

        List<MunroDTO> result = munroService.findByFilter(munroFilter);

        assertEquals(9, result.size());
    }

    @Test
    public void findByMultipleFilter() {
        MunroFilter munroFilter = MunroFilterBuilder.builder()
                                    .byCategory(MunroCategory.MUN)
                                    .byMinimumHeight(100)
                                    .byMaximumHeight(500)
                                    .orderBy(SortBy.Property.HEIGHT_METER, SortBy.Direction.DESC)
                                    .limit(2)
                                    .build();

        List<MunroDTO> result = munroService.findByFilter(munroFilter);

        assertEquals(2, result.size());
        assertThat(result, contains(
                new MunroDTO("MUNRO_6", "400", MunroCategory.MUN),
                new MunroDTO("MUNRO_5", "300", MunroCategory.MUN)
        ));
    }
}
