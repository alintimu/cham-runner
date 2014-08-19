package Model;

import Util.GenericExtensionFilter;
import org.apache.commons.math3.random.RandomDataGenerator;
import org.apache.commons.math3.random.RandomGenerator;

import java.io.File;
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
    private String warName;

    public ModuleModel() {
        this(genId(), "default", false, "default");
    }

    public ModuleModel(String name, String path) {
        this(genId(), name, false, path);
    }

    public ModuleModel(Integer id, String name, Boolean isBuilt, String path) {
        this.id = id;
        this.name = name;
        this.isBuilt = isBuilt;
        this.path = path;
        setWarName();
    }

    public ModuleModel(ProjectModel projectModel) {
        setId(projectModel.getId());
        setName(projectModel.getName());
        setIsBuilt(projectModel.getEnabled());
        setPath(projectModel.getAbsolutePath());
    }

    public String getWarName() {
        return warName;
    }

    private void setWarName() {
        String targetPath = path + "\\target";
        File targetFolder = new File(targetPath);
        GenericExtensionFilter filter = new GenericExtensionFilter(".war");
        String[] fileList = targetFolder.list(filter);

        if (fileList == null) {
            System.out.println("WARNING -- invalid module PATH --");
            return;
        } else if (fileList.length == 0) {
            System.out.println("no .war file found");
            return;
        } else {
            for (String file : fileList) {
                warName = new StringBuffer(targetPath).append(File.separator)
                        .append(file).toString();
            }
        }
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
