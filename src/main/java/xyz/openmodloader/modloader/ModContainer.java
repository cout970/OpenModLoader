package xyz.openmodloader.modloader;

public class ModContainer {

    public final String modid;
    public final String name;
    public final String version;
    public final Class<?> clazz;

    public ModContainer (String modid, String name, String version, Class<?> clazz){
        this.modid = modid;
        this.name = name;
        this.version = version;
        this.clazz = clazz;
    }
}
