package Model;

import javax.xml.bind.annotation.*;

/**
 * Created by alin.timu on 8/8/2014.
 */
@XmlAccessorOrder(value = XmlAccessOrder.ALPHABETICAL)
@XmlType(propOrder = { "name", "absolutePath", "buildParams"})
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "ProjectModel")
public class ProjectModel {
    @XmlAttribute(name = "id", required = false)
    private Integer id;
    @XmlElement(name = "absolutePath", required = true)
    private String absolutePath;
    @XmlElement(name = "name", required = true)
    private String name;
    @XmlAttribute(name = "enabled", required = false)
    private Boolean enabled;
    @XmlElement(name = "buildParams", required = false, nillable = true)
    private String buildParams;

    public ProjectModel() {
        this(0, null, null, false, null);
    }

    public ProjectModel(Integer id, String absolutePath, String name, Boolean enabled, String buildCommands) {
        this.id = id;
        this.absolutePath = absolutePath;
        this.name = name;
        this.enabled = enabled;
        this.buildParams = buildCommands;
    }

    public ProjectModel(ModuleModel moduleModel) {
        this.id = moduleModel.getId();
        this.absolutePath = moduleModel.getPath();
        this.name = moduleModel.getName();
        this.enabled = moduleModel.getIsBuilt();
        this.buildParams = null;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAbsolutePath() {
        return absolutePath;
    }

    public void setAbsolutePath(String absolutePath) {
        this.absolutePath = absolutePath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getBuildParams() {
        return buildParams;
    }

    public void setBuildParams(String buildParams) {
        this.buildParams = buildParams;
    }

    @Override
    public String toString() {
        return "ProjectModel{" +
                "id=" + id +
                ", absolutePath='" + absolutePath + '\'' +
                ", name='" + name + '\'' +
                ", enabled=" + enabled +
                ", buildParams='" + buildParams + '\'' +
                '}';
    }
}
