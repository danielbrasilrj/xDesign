package uk.co.xdesign.munros.validator.impl;

import org.springframework.stereotype.Component;
import uk.co.xdesign.munros.dto.MunroFilter;
import uk.co.xdesign.munros.exeption.FilterException;
import uk.co.xdesign.munros.validator.IMunroValidator;

@Component
public class MunroValidator implements IMunroValidator {

    @Override
    public void validateFilter(MunroFilter munroFilter) {
        if(munroFilter != null) {
            boolean limitLessThanOne = munroFilter.getLimit() != null && munroFilter.getLimit() < 1;
            if(limitLessThanOne) {
                throw new FilterException("The minimum height cannot be grather than maximum height.");
            }

            boolean minGratherThanMax = munroFilter.getMinHeight() != null && munroFilter.getMaxHeight() != null
                                        && munroFilter.getMinHeight().compareTo(munroFilter.getMaxHeight()) > 0;
            if(minGratherThanMax) {
                throw new FilterException("The minimum height cannot be grather than maximum height.");
            }
        }
    }
}
