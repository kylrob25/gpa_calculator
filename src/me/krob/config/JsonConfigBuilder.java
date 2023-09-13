package me.krob.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.RequiredArgsConstructor;

import java.io.*;

@RequiredArgsConstructor
public class JsonConfigBuilder<C> {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    private final File file;
    private final Class<C> clazz;

    public void makeParent() {
        File parent = file.getParentFile();
        if (!parent.exists()) parent.mkdirs();
    }

    public C loadFile() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
            return (C) GSON.fromJson(reader, clazz);
        } catch (IOException exception) {
            return createFile();
        }
    }

    public C createFile() {
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(toJsonString());
            writer.flush();
        } catch (IOException | InstantiationException | IllegalAccessException exception) {
            exception.printStackTrace();
            return null;
        }

        return loadFile();
    }

    public String toJsonString() throws InstantiationException, IllegalAccessException {
        return GSON.toJson(clazz.newInstance());
    }
}