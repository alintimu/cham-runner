package Model;

import org.apache.commons.math3.random.RandomDataGenerator;
import org.apache.commons.math3.random.RandomGenerator;

import java.io.Serializable;
import java.util.Random;

/**
 * Created by alin.timu on 8/12/2014.
 */
public class ModuleModel implements Serializable {
    private Integer id;
    private String name;
    private Boolean isBuilt;
    private String path;

    public ModuleModel() {
        this(genId(), "default", false, "default");
    }

    public ModuleModel(Integer id, String name, Boolean isBuilt, String path) {
        this.id = id;
        this.name = name;
        this.isBuilt = isBuilt;
        this.path = path;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getIsBuilt() {
        return isBuilt;
    }

    public void setIsBuilt(Boolean isBuilt) {
        this.isBuilt = isBuilt;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    private static Integer genId() {
        RandomDataGenerator generator = new RandomDataGenerator();
        return generator.nextSecureInt(1, Integer.MAX_VALUE);
    }
}
