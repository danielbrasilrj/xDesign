package uk.co.xdesign.munros.helper;

import org.springframework.stereotype.Component;
import uk.co.xdesign.munros.dto.MunroDTO;

import java.util.List;

@Component
public class MunroHelper implements IMunroHelper {

    private final String CVS_FILE_NAME = "munrotab_v6.2.csv";

    private List<MunroDTO> munroList;

    @Override
    public void setMunroList(List<MunroDTO> munroList) {
        this.munroList = munroList;
    }

    public List<MunroDTO> getMunroList() {
        return munroList;
    }

    @Override
    public String getFileName() {
        return CVS_FILE_NAME;
    }
}
