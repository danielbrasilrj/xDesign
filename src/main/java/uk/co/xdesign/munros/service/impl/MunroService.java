package uk.co.xdesign.munros.service.impl;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.enums.CSVReaderNullFieldIndicator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.co.xdesign.munros.dto.MunroCategory;
import uk.co.xdesign.munros.dto.MunroDTO;
import uk.co.xdesign.munros.dto.MunroFilter;
import uk.co.xdesign.munros.helper.FileUtil;
import uk.co.xdesign.munros.helper.impl.MunroHelper;
import uk.co.xdesign.munros.service.IMunroService;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.Comparator.comparingInt;
import static java.util.Comparator.reverseOrder;

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

    @Override
    public List<MunroDTO> findByFilter(MunroFilter munroFilter) {
        List<MunroDTO> munroList = munroHelper.getMunroList();

        List<Predicate<MunroDTO>> predicateFilter = new ArrayList<>();

        predicateFilter.add(m -> m.getPost1997() != null);
        if(munroFilter != null) {
            // Category
            if (munroFilter.getMunroCategory() != null) {
                predicateFilter.add(m -> munroFilter.getMunroCategory().name().equals(m.getPost1997()));
            } else {
                predicateFilter.add(m ->
                        MunroCategory.MUN.name().equals(m.getPost1997()) ||
                        MunroCategory.TOP.name().equals(m.getPost1997())
                );
            }
        }

        List<MunroDTO> result = munroList.stream().filter(predicateFilter.stream().reduce(x -> true, Predicate::and)).collect(Collectors.toList());

        // Sort
        if (munroFilter != null && munroFilter.getSortBy() != null) {
            Comparator<MunroDTO> comparator = comparingInt(m -> StringUtils.isNotEmpty(m.getHeightMeter()) ? Integer.parseInt(m.getHeightMeter()) : 0);
            result.sort(comparator);
        }

        return result;
    }
}
