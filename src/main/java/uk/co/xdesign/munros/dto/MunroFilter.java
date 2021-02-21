package uk.co.xdesign.munros.dto;

import java.io.Serializable;

public class MunroFilter implements Serializable {
    private static final long serialVersionUID = -5864232185152193788L;

    private MunroCategory munroCategory;
    private SortBy sortBy;
    private Integer limit;
    private Integer minHeight;

    public MunroCategory getMunroCategory() {
        return munroCategory;
    }
    public void setMunroCategory(MunroCategory munroCategory) {
        this.munroCategory = munroCategory;
    }

    public SortBy getSortBy() { return sortBy; }
    public void setSortBy(SortBy sortBy) { this.sortBy = sortBy; }

    public Integer getLimit() { return limit; }
    public void setLimit(Integer limit) { this.limit = limit; }

    public Integer getMinHeight() { return minHeight; }
    public void setMinHeight(Integer minHeight) { this.minHeight = minHeight; }
}
