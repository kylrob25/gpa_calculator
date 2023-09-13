package me.krob.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.RequiredArgsConstructor;

import java.io.*;
import java.nio.file.Files;

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
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(Files.newInputStream(file.toPath())))) {
            return (C) GSON.fromJson(reader, clazz);
        } catch (IOException exception) {
            return createFile();
        }
    }

    public C createFile() {
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(toJsonString());
            writer.flush();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }

        return loadFile();
    }

    public String toJsonString() throws Throwable {
        return GSON.toJson(clazz.getDeclaredConstructor().newInstance());
    }
}