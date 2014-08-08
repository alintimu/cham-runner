package Controller;

import Model.TextFieldModel;
import Repository.AbstractRepository;
import View.AbstractWindowView;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

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

    /* opt 0 to move from webapps -> undeployed
       opt 1 to move from undeployed -> webapps
       fileName is file to be moved back or forth */
    public void deployApp(String fileName, int opt) {
        if (opt == 0) {
            try {
                File toMove = new File("C:\\tomcat\\webapps\\" + fileName + ".war");

                if (toMove.renameTo(new File("C:\\tomcat\\undeployed\\" + toMove.getName()))) {
                    System.out.println("Moved " + toMove.getName() + " to undeployed !");
                } else {
                    System.out.println("Deleting file at destination...");
                    File to_delete = new File("C:\\tomcat\\undeployed\\" + fileName + ".war");
                    to_delete.delete();
                    toMove.renameTo(new File("C:\\tomcat\\undeployed\\" + toMove.getName()));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (opt == 1) {
            try {
                File toMove = new File("C:\\tomcat\\undeployed\\" + fileName + ".war");

                if (toMove.renameTo(new File("C:\\tomcat\\webapps\\" + toMove.getName()))) {
                    System.out.println("Moved " + toMove.getName() + " back to webapps, fuck yeah !");
                } else {
                    System.out.println("Deleting file at destination...");
                    File to_delete = new File("C:\\tomcat\\webapps\\" + fileName + ".war");
                    to_delete.delete();
                    toMove.renameTo(new File("C:\\tomcat\\webapps\\" + toMove.getName()));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // if any files remain in the webapps dir, move them to undeployed
    public void moveOnClose() {
        List<String> fileList = new ArrayList<>();
        fileList.add("templates");
        fileList.add("selfService");
        fileList.add("cp");

        // TODO
        for (String s : fileList) {
            Path source = Paths.get("C:\\tomcat\\webapps\\" + s + ".war");
            Path destination = Paths.get("C:\\tomcat\\undeployed\\" + s + ".war");
            try {
                Files.move(source, destination, REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}