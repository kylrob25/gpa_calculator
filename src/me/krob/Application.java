package me.krob;

import me.krob.config.JsonConfigBuilder;
import me.krob.config.ModuleConfig;
import me.krob.module.Module;

import java.io.File;

public class Application {

    private final ModuleConfig moduleConfig;

    public Application() {
        File configFile = new File("./modules.json");
        JsonConfigBuilder<ModuleConfig> configBuilder = new JsonConfigBuilder<>(configFile, ModuleConfig.class);

        configBuilder.makeParent();

        moduleConfig = configBuilder.loadFile();
    }
}
