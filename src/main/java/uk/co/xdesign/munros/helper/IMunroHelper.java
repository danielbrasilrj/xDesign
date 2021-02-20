package uk.co.xdesign.munros.helper;

import uk.co.xdesign.munros.dto.MunroDTO;

import java.util.List;

public interface IMunroHelper {

    void setMunroList(List<MunroDTO> munroList);

    String getFileName();
}
