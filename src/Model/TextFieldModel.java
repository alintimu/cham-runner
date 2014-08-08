package Model;

/**
 * Created by alin.timu on 8/6/2014.
 */
public class TextFieldModel {

    public String templateSourcePath;
    public String templateDestinationPath;
    public String cpSourcePath;
    public String cpDestinationPath;
    public String selfServiceSourcePath;
    public String selfServiceDestinationPath;

    public TextFieldModel() {

    }

    public String getTemplateSourcePath() {
        return templateSourcePath;
    }

    public void setTemplateSourcePath(String templateSourcePath) {
        this.templateSourcePath = templateSourcePath;
    }

    public String getTemplateDestinationPath() {
        return templateDestinationPath;
    }

    public void setTemplateDestinationPath(String templateDestinationPath) {
        this.templateDestinationPath = templateDestinationPath;
    }

    public String getCpSourcePath() {
        return cpSourcePath;
    }

    public void setCpSourcePath(String cpSourcePath) {
        this.cpSourcePath = cpSourcePath;
    }

    public String getCpDestinationPath() {
        return cpDestinationPath;
    }

    public void setCpDestinationPath(String cpDestinationPath) {
        this.cpDestinationPath = cpDestinationPath;
    }

    public String getSelfServiceSourcePath() {
        return selfServiceSourcePath;
    }

    public void setSelfServiceSourcePath(String selfServiceSourcePath) {
        this.selfServiceSourcePath = selfServiceSourcePath;
    }

    public String getSelfServiceDestinationPath() {
        return selfServiceDestinationPath;
    }

    public void setSelfServiceDestinationPath(String selfServiceDestinationPath) {
        this.selfServiceDestinationPath = selfServiceDestinationPath;
    }
}
