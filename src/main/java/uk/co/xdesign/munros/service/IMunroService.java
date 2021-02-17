package uk.co.xdesign.munros.service;

import uk.co.xdesign.munros.dto.MunroDTO;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public interface IMunroService {
    List<MunroDTO> loadFromCsv(String fileName) throws IOException, URISyntaxException;
}
