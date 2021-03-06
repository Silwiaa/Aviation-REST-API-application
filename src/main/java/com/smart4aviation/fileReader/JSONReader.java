package com.smart4aviation.fileReader;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class JSONReader {
    private static final Logger LOGGER = LoggerFactory.getLogger(JSONReader.class);

    public String readFile(String resourceName) {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(resourceName).getFile());
        String result = "";

        try (Stream<String> fileLines = Files.lines(Paths.get(file.getPath()))) {
            result = fileLines.collect(Collectors.joining());
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return result;
    }
}
