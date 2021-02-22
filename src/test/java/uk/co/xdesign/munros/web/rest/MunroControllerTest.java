package uk.co.xdesign.munros.web.rest;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import uk.co.xdesign.munros.dto.MunroCategory;
import uk.co.xdesign.munros.dto.MunroDTO;
import uk.co.xdesign.munros.dto.MunroFilter;
import uk.co.xdesign.munros.service.IMunroService;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@WebMvcTest
@EnableSpringDataWebSupport
public class MunroControllerTest {

    @MockBean
    protected IMunroService munroService;

    @Autowired
    protected MockMvc mockMvc;

    @Before
    public void setup() throws IOException, URISyntaxException {
        List<MunroDTO> munroList = Arrays.asList(
                new MunroDTO("MUNRO_1", "100", MunroCategory.MUN),
                new MunroDTO("MUNRO_2", "200", MunroCategory.TOP)
        );
        when(munroService.findByFilter(any(MunroFilter.class))).thenReturn(munroList);
    }

    @Test
    public void statusOk() throws Exception {
        mockMvc.perform(get("/find-by-filter")
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJsonBytes(new MunroFilter())))
                .andExpect(status().isOk());
    }

    @Test
    public void response() throws Exception {
        mockMvc.perform(get("/find-by-filter")
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJsonBytes(new MunroFilter())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))

                .andExpect(jsonPath("$.[0].name", is("MUNRO_1")))
                .andExpect(jsonPath("$.[0].height", is("100")))

                .andExpect(jsonPath("$.[1].name", is("MUNRO_2")))
                .andExpect(jsonPath("$.[1].height", is("200")));
    }

    protected static byte[] convertObjectToJsonBytes(Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper.writeValueAsBytes(object);
    }
}
