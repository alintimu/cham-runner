package Model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alin.timu on 8/8/2014.
 */
@XmlRootElement(name = "Projects")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProjectModelList {
    @XmlElement(name = "project")
    List<ProjectModel> modelList = new ArrayList<>();

    public ProjectModelList() {
    }

    public List<ProjectModel> getModelList() {
        return modelList;
    }

    public void setModelList(List<ProjectModel> modelList) {
        this.modelList = modelList;
    }

    @Override
    public String toString() {
        return "ProjectModelList{" +
                "modelList=" + modelList +
                '}';
    }
}
