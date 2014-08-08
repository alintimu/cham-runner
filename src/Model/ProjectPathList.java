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
public class ProjectPathList {
    @XmlElement(name = "project")
    List<ProjectPathsModel> modelList = new ArrayList<>();

    public ProjectPathList() {
    }

    public List<ProjectPathsModel> getModelList() {
        return modelList;
    }

    public void setModelList(List<ProjectPathsModel> modelList) {
        this.modelList = modelList;
    }

    @Override
    public String toString() {
        return "ProjectPathList{" +
                "modelList=" + modelList +
                '}';
    }
}
