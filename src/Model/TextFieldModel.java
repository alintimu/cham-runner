package Model;

/**
 * Created by alin.timu on 8/6/2014.
 */
public class TextFieldModel {

    public String templateSourcePath;
    public String cpSourcePath;
    public String selfServiceSourcePath;

    public TextFieldModel() {

    }

    public String getTemplateSourcePath() {
        return templateSourcePath;
    }

    public void setTemplateSourcePath(String templateSourcePath) {
        this.templateSourcePath = templateSourcePath;
    }

    public String getCpSourcePath() {
        return cpSourcePath;
    }

    public void setCpSourcePath(String cpSourcePath) {
        this.cpSourcePath = cpSourcePath;
    }

    public String getSelfServiceSourcePath() {
        return selfServiceSourcePath;
    }

    public void setSelfServiceSourcePath(String selfServiceSourcePath) {
        this.selfServiceSourcePath = selfServiceSourcePath;
    }

}
