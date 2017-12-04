package core;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import java.util.stream.Stream;

public class TokenReader {

    private String token;
    private String botName;

    public void readConfigs(String filePath) {
        ArrayList<String> arrayList = new ArrayList<>();
        try (Stream<String> stream = Files.lines(Paths.get(filePath))) {
            stream.forEach(arrayList::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        token = arrayList.get(0);
        botName = arrayList.get(1);
    }

    public String getToken() {
        return token;
    }

    public String getBotName() {
        return botName;
    }
}

