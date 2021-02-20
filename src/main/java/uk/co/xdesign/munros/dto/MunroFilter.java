package uk.co.xdesign.munros.dto;

import java.io.Serializable;

public class MunroFilter implements Serializable {
    private static final long serialVersionUID = -5864232185152193788L;

    private MunroCategory munroCategory;

    public MunroCategory getMunroCategory() {
        return munroCategory;
    }
    public void setMunroCategory(MunroCategory munroCategory) {
        this.munroCategory = munroCategory;
    }
}
