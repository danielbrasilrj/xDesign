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
import uk.co.xdesign.munros.dto.SortBy;
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

import static java.util.Comparator.comparing;
import static java.util.Comparator.comparingInt;

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

        List<MunroDTO> result = filter(munroFilter, munroList);
        sort(munroFilter, result);
        result = limit(munroFilter, result);

        return result;
    }

    private List<MunroDTO> limit(MunroFilter munroFilter, List<MunroDTO> result) {
        if(munroFilter != null && munroFilter.getLimit() != null) {
            result = result.stream().limit(munroFilter.getLimit()).collect(Collectors.toList());
        }
        return result;
    }

    private void sort(MunroFilter munroFilter, List<MunroDTO> result) {
        if (munroFilter != null && munroFilter.getSortBy() != null) {
            Comparator<MunroDTO> comparator = getSortByComparator(munroFilter.getSortBy());

            // Sort direction
            if(comparator != null) {
                if (munroFilter.getSortBy().isDesc()) {
                    result.sort(comparator.reversed());
                } else {
                    result.sort(comparator);
                }
            }
        }
    }

    private List<MunroDTO> filter(MunroFilter munroFilter, List<MunroDTO> munroList) {
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

            // Minimum height
            if (munroFilter.getMinHeight() != null) {
                predicateFilter.add(m -> StringUtils.isNotEmpty(m.getHeightMeter()) && Integer.parseInt(m.getHeightMeter()) > munroFilter.getMinHeight());
            }

            // Maximum height
            if (munroFilter.getMaxHeight() != null) {
                predicateFilter.add(m -> StringUtils.isNotEmpty(m.getHeightMeter()) && Integer.parseInt(m.getHeightMeter()) < munroFilter.getMaxHeight());
            }
        }

        return munroList.stream().filter(predicateFilter.stream().reduce(x -> true, Predicate::and)).collect(Collectors.toList());
    }

    private Comparator<MunroDTO> getSortByComparator(SortBy sortBy) {
        if(sortBy != null && sortBy.height()) {
            return comparingInt(m -> StringUtils.isNotEmpty(m.getHeightMeter()) ? Integer.parseInt(m.getHeightMeter()) : 0);
        } else if(sortBy != null && sortBy.name()) {
            return comparing(m -> StringUtils.isNotEmpty(m.getName()) ? m.getName() : "");
        } else {
            return null;
        }
    }
}
