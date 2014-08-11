package Model;

import javax.xml.bind.annotation.*;

/**
 * Created by alin.timu on 8/8/2014.
 */
@XmlAccessorOrder(value = XmlAccessOrder.ALPHABETICAL)
@XmlType(propOrder = { "name", "path", "comment"})
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "ProjectPathsModel")
public class ProjectPathsModel {
    @XmlAttribute(name = "id", required = true)
    private Integer id;
    @XmlElement(name = "path", required = true)
    private String path;
    @XmlElement(name = "name", required = false)
    private String name;
    @XmlAttribute(name = "enabled", required = false)
    private Boolean enabled;
    @XmlElement(name = "comment", required = false, nillable = true)
    private String comment;

    public ProjectPathsModel() {
        this(0, null, null, false, null);
    }

    public ProjectPathsModel(Integer id, String path, String name, Boolean enabled, String comment) {
        this.id = id;
        this.path = path;
        this.name = name;
        this.enabled = enabled;
        this.comment = comment;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "ProjectPathsModel{" +
                "id=" + id +
                ", path='" + path + '\'' +
                ", name='" + name + '\'' +
                ", enabled=" + enabled +
                ", comment='" + comment + '\'' +
                '}';
    }
}
