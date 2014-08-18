package Controller;

import Model.ModuleModel;
import Model.ProjectModel;
import Model.ProjectModelList;
import Util.FileVisitor;
import Util.JaxbUtils;
import Util.StringUtils;
import View.MainWindowView;

import javax.xml.bind.JAXBException;
import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

/**
 * Controller for main app behavior. Also handles operations requested through the MainWindowsView.
 */
public class MainController {
    protected MainWindowView mainWindow;
    protected final String CONFIG_PATH = System.getenv("USERPROFILE") + "\\cr_config.xml";
    protected File configFile;
    protected boolean hasConfig;

    public void run() {
        mainWindow.run(this);

        /* if config doesn't exist, create it */
        if (!hasConfig) {
            configFile = new File(CONFIG_PATH);
            try {
                configFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            loadConfig();
        }
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
        hasConfig = hasConfig();
    }

    // TODO Priority LOW generate fileList based on .war extension
    // if any files remain in the webapps dir on exit, use this to move them to undeployed
    // used in MainWindowView
    public void moveOnClose() {
        fileList.add("templates");
        fileList.add("cp");
        fileList.add("selfService");

        for (String s : fileList) {
            Path source_file = Paths.get("C:\\tomcat\\webapps\\" + s + ".war");
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

    /* TODO Priority LOW recheck fileList var and initialize propertly */
    public void deleteDirs() {
        for (String s : fileList) {
            Path directoryToDelete = Paths.get("C:\\tomcat\\webapps\\" + s + "\\");
            File check = new File("C:\\tomcat\\webapps\\" + s + "\\");
            // if the dir doesn't exist move on
            if (!check.exists()) {
                continue;
            }
            FileVisitor delFileVisitor = new FileVisitor();
            try {
                Files.walkFileTree(directoryToDelete, delFileVisitor);
                // will squirt some silly stack trace when dir isn't there, regardless of catch
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }

    public String getTomcatPath(String var) {
        String tomcatPath = System.getenv(var);
        tomcatPath = StringUtils.makeWindowsPath(tomcatPath);

        return tomcatPath;
    }

    private void loadProjectsFromConfig(String configPath) throws JAXBException {
        ProjectModelList projectModelList = (ProjectModelList) JaxbUtils.convertToObject(configPath, ProjectModelList.class);
        for (ProjectModel projectModel : projectModelList.getModelList()) {
            ModuleModel moduleModel = new ModuleModel(projectModel);
            mainWindow.addModuleFromConfig(moduleModel);
        }
    }

    /**
     *
     */
    public void loadConfig() {
        try {
            loadProjectsFromConfig(StringUtils.makeWindowsPath(CONFIG_PATH));
        } catch (JAXBException | NullPointerException e) {
            if (e instanceof NullPointerException) {
                System.out.println("Unable to load config, path was null, check in hasConfig()");
            }
            e.printStackTrace();
        }
    }

    /**
     * Checks for the existence of a config file
     *
     * @return String with path to config if it exists, null otherwise.
     */
    public boolean hasConfig() {
        configFile = new File(CONFIG_PATH);
        if (configFile.exists() && !configFile.isDirectory()) {
            return true;
        }
        return false;
    }



    /**
     * Writes the given model to the config. If the config file does not exist, it is created.
     * If the config file exists, the data is appended.
     *
     * @param moduleModel
     */
    /* TODO Priority HIGH implement this bad-boy. */
    public void modelToConfig(ModuleModel moduleModel) {
        // append to config if it already exists
        if (hasConfig) {
            writeModelToConfig(moduleModel);
        }
        // otherwise create a new config from scratch
        else {
            writeModelToConfig(moduleModel);
        }
    }

    private void writeModelToConfig(ModuleModel moduleModel) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(CONFIG_PATH));
            if (br.readLine() == null) {
                ProjectModelList projectModelList = new ProjectModelList();
                projectModelList.getModelList().add(new ProjectModel(moduleModel));
                JaxbUtils.convertToXml(CONFIG_PATH, ProjectModelList.class, projectModelList);
            } else {
                ProjectModelList projectModelList = (ProjectModelList) JaxbUtils.convertToObject(CONFIG_PATH, ProjectModelList.class);
                projectModelList.getModelList().add(new ProjectModel(moduleModel));
                JaxbUtils.convertToXml(CONFIG_PATH, ProjectModelList.class, projectModelList);
            }
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
        }
    }
}