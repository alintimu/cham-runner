package Controller;

import Model.TextFieldModel;
import Repository.AbstractRepository;
import View.AbstractWindowView;
import View.MainWindowView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alin.timu on 8/8/2014.
 */
public class MainController implements BasicController {
    protected List<AbstractWindowView> viewList = new ArrayList<>();
    protected List<AbstractRepository> repositoryList = new ArrayList<>();
    private TextFieldModel textFieldModel;

    public List<AbstractWindowView> getViewList() {
        return viewList;
    }

    public void setViewList(List<AbstractWindowView> viewList) {
        this.viewList = viewList;
    }

    public List<AbstractRepository> getRepositoryList() {
        return repositoryList;
    }

    public void setRepositoryList(List<AbstractRepository> repositoryList) {
        this.repositoryList = repositoryList;
    }

    public void addView(AbstractWindowView mainView) {
        viewList.add(mainView);
    }

    public void addRepository(AbstractRepository mainRepository) {
        repositoryList.add(mainRepository);
    }

    @Override
    public void run() {
        if (viewList.size() > 0) {
            viewList.get(0).run(this);
        }
    }

    @Override
    public void changeView(String view_identifier) {

    }

    @Override
    public void changeView(Class<? extends AbstractWindowView> view) {

    }

    @Override
    public AbstractWindowView getView(String s) {
        return null;
    }

    @Override
    public AbstractRepository getRepository(String s) {
        return null;
    }


    public void runStartButton() {

    }

    public void moveToUndeployed() {
        try {
            File templates = new File("C:\\tomcat\\webapps\\templates.war");

            if (templates.renameTo(new File ("C:\\tomcat\\undeployed\\" + templates.getName()))) {
                System.out.println("Moved " + templates.getName() + " to undeployed !");
            } else {
                System.out.println("Deleting file at destination...");
                File to_delete = new File("C:\\tomcat\\undeployed\\templates.war");
                to_delete.delete();
                templates.renameTo(new File ("C:\\tomcat\\undeployed\\" + templates.getName()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void moveToWebapps() {
        try {
            File templates = new File("C:\\tomcat\\undeployed\\templates.war");

            if (templates.renameTo(new File ("C:\\tomcat\\webapps\\" + templates.getName()))) {
                System.out.println("Moved " + templates.getName() + " back to webapps, fuck yeah !");
            } else {
                System.out.println("Deleting file at destination...");
                File to_delete = new File("C:\\tomcat\\webapps\\templates.war");
                to_delete.delete();
                templates.renameTo(new File ("C:\\tomcat\\webapps\\" + templates.getName()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}