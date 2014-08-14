package Controller;

import Model.ProjectPathList;
import Model.ProjectPathsModel;
import Util.FileVisitor;
import Util.JaxbUtils;
import View.AbstractMainWindowView;
import View.MainWindowView;

import javax.xml.bind.JAXBException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

/**
 * Created by alin.timu on 8/8/2014.
 */
public class MainController {
    protected MainWindowView mainWindow;

    public void run() {
        mainWindow.run(this);
    }

    List<String> fileList = new ArrayList<String>();

    public MainWindowView getMainWindow() {
        return mainWindow;
    }

    public void setMainWindow(MainWindowView mainWindow) {
        this.mainWindow = mainWindow;
    }

    public void addView(MainWindowView mainWindow) {
        this.mainWindow = mainWindow;
    }

    public MainController() {
        checkForConfig();
    }

    // TODO recheck fileList var
    // if any files remain in the webapps dir on exit, use this to move them to undeployed
    // used in MainWindowView
    public void moveOnClose() {
        fileList.add("templates");
        fileList.add("cp");
        fileList.add("selfService");

        for (String s : fileList) {
            Path source_file = Paths.get("C:\\tomcat\\webapps\\" + s +".war");
            Path destination_file = Paths.get("C:\\tomcat\\undeployed\\" + s + ".war");
            try {
                Files.move(source_file, destination_file, REPLACE_EXISTING);
            } catch (IOException e) {
                if (e instanceof NoSuchFileException) {
                    // if file isn't in webapps, we don't need to move it so do nothing here.
                } else {
                    // otherwise print the stack trace because it may be a different exception.
                    e.printStackTrace();
                }
            }
        }

        // and delete dirs
        deleteDirs();
    }

    public void setPathToXml(int projectId, String projectName) {
        ProjectPathList pathList = new ProjectPathList();

/*        switch (projectId){
            case 1: {
                pathList.getModelList().add(new ProjectPathsModel(projectId, textFieldModel.getTemplateSourcePath(), projectName, true, ""));
            }
            case 2: {
                pathList.getModelList().add(new ProjectPathsModel(projectId, textFieldModel.getCpSourcePath(), projectName, true, ""));
            }
            case 3: {
                pathList.getModelList().add(new ProjectPathsModel(projectId, textFieldModel.getSelfServiceSourcePath(), projectName, true, ""));
            }
        }*/

        try {
            JaxbUtils.convertToXml("config.xml", ProjectPathList.class, pathList);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    /* TODO recheck fileList var and initialize propertly */
    public void deleteDirs() {
        for (String s : fileList) {
            Path directoryToDelete = Paths.get("C:\\tomcat\\webapps\\" + s + "\\");
            File check = new File("C:\\tomcat\\webapps\\" + s + "\\");
            // if the dir doesn't exist move on
            if (!check.exists()) {
                continue;
            }
            FileVisitor delFileVisitor = new FileVisitor();
            try{
                Files.walkFileTree(directoryToDelete, delFileVisitor);
                // will squirt some silly stack trace when dir isn't there, regardless of catch
            }catch(IOException ioe){
                ioe.printStackTrace();
            }
        }
    }

    public void getTomcatPath(String var) {
        String value = System.getenv(var);
        char backslash = '\\';
        // make sure to add a backslash after each backslash because Windows
        for (int i = 0; i < value.length(); i++) {
            if (value.charAt(i) == backslash) {
                value = new StringBuilder(value).insert(i,backslash).toString();
                i++;
            }
        }
    }

    private void checkForConfig() {
        URL configUrl = this.getClass().getClassLoader().getResource("config.xml");
        if (configUrl != null) {

        }

        String configString = configUrl.getPath().substring(1);
        System.out.println(configString);
    }
}