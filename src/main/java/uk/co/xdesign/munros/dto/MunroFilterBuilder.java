package uk.co.xdesign.munros.dto;

import java.math.BigDecimal;

public class MunroFilterBuilder {

    private final MunroFilter munroFilter;

    private MunroFilterBuilder() {
        munroFilter = new MunroFilter();
    }

    public static MunroFilterBuilder builder() {
        return new MunroFilterBuilder();
    }

    public MunroFilterBuilder byCategory(MunroCategory munroCategory) {
        munroFilter.setMunroCategory(munroCategory);
        return this;
    }

    public MunroFilterBuilder byMinimumHeight(BigDecimal minimumHeight) {
        munroFilter.setMinHeight(minimumHeight);
        return this;
    }

    public MunroFilterBuilder byMaximumHeight(BigDecimal maximumHeight) {
        munroFilter.setMaxHeight(maximumHeight);
        return this;
    }

    public MunroFilterBuilder orderBy(SortBy.Property property) {
        SortBy sortBy = new SortBy();
        sortBy.setProperty(property);
        munroFilter.setSortBy(sortBy);
        return this;
    }

    public MunroFilterBuilder orderBy(SortBy.Property property, SortBy.Direction direction) {
        SortBy sortBy = new SortBy();
        sortBy.setProperty(property);
        sortBy.setDirection(direction);
        munroFilter.setSortBy(sortBy);
        return this;
    }

    public MunroFilterBuilder limit(Integer limit) {
        munroFilter.setLimit(limit);
        return this;
    }

    public MunroFilter build() {
        return munroFilter;
    }
}
