package uk.co.xdesign.munros.validator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import uk.co.xdesign.munros.dto.MunroFilter;
import uk.co.xdesign.munros.dto.MunroFilterBuilder;
import uk.co.xdesign.munros.exeption.FilterException;
import uk.co.xdesign.munros.validator.impl.MunroValidator;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.fail;

// TODO Validate the error messages.

@RunWith(MockitoJUnitRunner.class)
public class MunroValidatorTest {

    @InjectMocks
    private MunroValidator munroValidator;

    @Test(expected = FilterException.class)
    public void errorWhenMinimumHeightIsGratherThanMaximumHeight() {
        MunroFilter munroFilter = MunroFilterBuilder.builder()
                .byMinimumHeight(new BigDecimal("200"))
                .byMaximumHeight(new BigDecimal("100"))
                .build();

        munroValidator.validateFilter(munroFilter);
    }

    @Test
    public void okWhenLimitIsBlank() {
        MunroFilter munroFilter = MunroFilterBuilder.builder()
                .build();

        try {
            munroValidator.validateFilter(munroFilter);
        } catch(FilterException e) {
            fail("Should not have thrown any exception.");
        }
    }

    @Test(expected = FilterException.class)
    public void errorWhenLimitIsZero() {
        MunroFilter munroFilter = MunroFilterBuilder.builder()
                .limit(0)
                .build();

        munroValidator.validateFilter(munroFilter);
    }

    @Test(expected = FilterException.class)
    public void errorWhenLimitIsNegative() {
        MunroFilter munroFilter = MunroFilterBuilder.builder()
                .limit(-1)
                .build();

        munroValidator.validateFilter(munroFilter);
    }
}
