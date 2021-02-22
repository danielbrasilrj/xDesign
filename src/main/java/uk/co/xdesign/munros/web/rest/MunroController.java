package uk.co.xdesign.munros.web.rest;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uk.co.xdesign.munros.dto.MunroFilter;
import uk.co.xdesign.munros.service.IMunroService;
import uk.co.xdesign.munros.web.rest.response.MunroResponse;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class MunroController {

    @Autowired
    private IMunroService munroService;

    @GetMapping(value = "/find-by-filter", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity findByFilter(@RequestBody MunroFilter munroFilter) throws IOException, URISyntaxException {
        List<MunroResponse> responseList = MunroResponse.fromDtoList(munroService.findByFilter(munroFilter));

        if(CollectionUtils.isEmpty(responseList)) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(responseList, HttpStatus.OK);
        }
    }
}
