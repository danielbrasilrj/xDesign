package uk.co.xdesign.munros.helper.impl;

import org.springframework.stereotype.Component;
import uk.co.xdesign.munros.dto.MunroDTO;
import uk.co.xdesign.munros.helper.IMunroHelper;

import java.util.List;

@Component
public class MunroHelper implements IMunroHelper {

    private final String CVS_FILE_NAME = "munrotab_v6.2.csv";

    private List<MunroDTO> munroList;

    public List<MunroDTO> getMunroList() {
        return munroList;
    }

    @Override
    public void setMunroList(List<MunroDTO> munroList) {
        this.munroList = munroList;
    }

    @Override
    public String getFileName() {
        return CVS_FILE_NAME;
    }
}
