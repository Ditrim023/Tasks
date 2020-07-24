package com.tasks.cities;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Nikita Krutoguz
 */
public class FileReader {

    public List<String> read(String pathName) throws IOException {
        List<String> fromFile = Files.lines(Paths.get(pathName)).collect(Collectors.toList());
        List<String> result = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < fromFile.size(); i++) {
            sb.append(fromFile.get(i)+"\n");
            if (fromFile.get(i).isEmpty()) {
                result.add(sb.toString());
                sb.delete(0, sb.length());
            }
        }
        result.add(sb.toString());
        return result;
    }
}
