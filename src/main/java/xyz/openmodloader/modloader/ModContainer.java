package xyz.openmodloader.modloader;

import com.google.gson.annotations.SerializedName;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ModContainer {
    private transient Class<?> mainClass;
    private transient BufferedImage logo;
    private transient Object instance;

    @SerializedName("main_class")
    private String classString;
    @SerializedName("id")
    private String modID;
    private String name;
    private String version;
    private String description;
    @SerializedName("update_url")
    private String updateURL;
    @SerializedName("logo")
    private String logoString;

    public Class<?> getMainClass() {
        if (this.mainClass == null) {
            try {
                this.mainClass = Class.forName(this.classString);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return mainClass;
    }

    public BufferedImage getLogo() {
        if (this.logo == null) {
            try {
                this.logo = ImageIO.read(ModContainer.class.getResourceAsStream("/" + this.logoString));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return logo;
    }

    public Object getInstance() {
        if (this.instance == null) {
            try {
                this.instance = this.getMainClass().newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        return instance;
    }

    public String getModID() {
        return modID;
    }

    public String getName() {
        return name;
    }

    public String getVersion() {
        return version;
    }

    public String getDescription() {
        return description;
    }

    public String getUpdateURL() {
        return updateURL;
    }
}
