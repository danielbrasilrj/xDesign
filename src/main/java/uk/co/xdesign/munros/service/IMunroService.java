package uk.co.xdesign.munros.service;

import uk.co.xdesign.munros.dto.MunroDTO;
import uk.co.xdesign.munros.dto.MunroFilter;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public interface IMunroService {
    List<MunroDTO> loadFromCsv() throws IOException, URISyntaxException;
    List<MunroDTO> findByFilter(MunroFilter munroFilter) throws IOException, URISyntaxException;
}
