package uk.co.xdesign.munros.service.impl;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.enums.CSVReaderNullFieldIndicator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.co.xdesign.munros.dto.MunroDTO;
import uk.co.xdesign.munros.helper.FileUtil;
import uk.co.xdesign.munros.helper.MunroHelper;
import uk.co.xdesign.munros.service.IMunroService;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

@Service
public class MunroService implements IMunroService {

    @Autowired
    private MunroHelper munroHelper;

    @Override
    public List<MunroDTO> loadFromCsv() throws IOException, URISyntaxException {
        BufferedReader reader = FileUtil.getReader(munroHelper.getFileName());
        if(reader == null) {
            throw new FileNotFoundException();
        }

        CsvToBean<MunroDTO> csvToBean = new CsvToBeanBuilder<MunroDTO>(reader)
                .withFieldAsNull(CSVReaderNullFieldIndicator.EMPTY_SEPARATORS)
                .withType(MunroDTO.class)
                .withSkipLines(1)
                .build();

        return csvToBean.parse();
    }
}
