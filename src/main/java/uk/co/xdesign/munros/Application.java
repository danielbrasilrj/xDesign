package uk.co.xdesign.munros;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.stereotype.Component;
import uk.co.xdesign.munros.helper.impl.MunroHelper;
import uk.co.xdesign.munros.service.IMunroService;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URISyntaxException;

@Component
@SpringBootApplication
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class Application {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MunroHelper munroHelper;

    @Autowired
    private IMunroService munroService;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @PostConstruct
    public void setup() throws IOException, URISyntaxException {
        // Initializing munro list from csv file.
        munroHelper.setMunroList(munroService.loadFromCsv());

        // To ident well the response json.
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }
}
