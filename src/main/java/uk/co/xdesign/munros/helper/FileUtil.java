package uk.co.xdesign.munros.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public final class FileUtil {

    private FileUtil() {}

    public static BufferedReader getReader(String fileName) throws URISyntaxException, IOException {
        URL res = FileUtil.class.getClassLoader().getResource(fileName);
        return res != null ? Files.newBufferedReader(Paths.get(res.toURI()), StandardCharsets.ISO_8859_1) : null;
    }
}
