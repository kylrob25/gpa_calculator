package me.krob;

import me.krob.config.JsonConfigBuilder;
import me.krob.config.ModuleConfig;
import me.krob.module.Module;

import java.io.File;

public class Application {
    public Application() {
        File configFile = new File("./modules.json");
        ModuleConfig moduleConfig = new ModuleConfig();
        JsonConfigBuilder<ModuleConfig> configBuilder = new JsonConfigBuilder<>(configFile, moduleConfig);

        configBuilder.makeParent();

        moduleConfig = configBuilder.loadFile();
    }
}
