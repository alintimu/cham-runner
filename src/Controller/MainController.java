package Controller;

import Model.TextFieldModel;
import Repository.AbstractRepository;
import Util.FileVisitor;
import View.AbstractWindowView;

import java.io.File;
import java.io.IOException;
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

    /* moves .war files into and out of the webapps dir
    *  0 out of webapps
    *  1 into webapps */
    public void deployApp(String fileName, int opt) {
        String undeployed_dir = "C:\\tomcat\\undeployed\\";
        String webapps_dir = "C:\\tomcat\\webapps\\";

        // move file from webapps to undeployed
        if (opt == 0) {
            // source, destination, filename
            moveToDir(webapps_dir, undeployed_dir, fileName);

        // move file from undeployed to webapps
        } else if (opt == 1) {
            // source, destination, filename
            moveToDir(undeployed_dir, webapps_dir, fileName);
        }
    }

    public void moveToDir(String source_dir, String dest_dir, String fileName) {
        try {
            File toMove = new File(source_dir + fileName + ".war");

            // move .war file
            // if nothing at destination, move file.
            if (toMove.renameTo(new File(dest_dir + toMove.getName()))) {
                System.out.println("Moved " + toMove.getName() + " to undeployed !");
            }
            // if there already is a file @ destination, delete and move
            else {
                System.out.println("Deleting file at destination...");
                File to_delete = new File(dest_dir + fileName + ".war");
                to_delete.delete();
                toMove.renameTo(new File(dest_dir + toMove.getName()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // if any files remain in the webapps dir on exit, use this to move them to undeployed
    public void moveOnClose() {
        List<String> fileList = new ArrayList<String>() {{
            add("templates");
            add("cp");
            add("selfService");
        }};

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

    /* TODO
       Make sure to uncomment the corect dir names before release */
    public void deleteDirs() {
        List<String> fileList = new ArrayList<String>() {{
            add("ceva");
//            add("templates\\");
//            add("cp\\");
//            add("selfService\\");
        }};

        for (String s : fileList) {
            Path directoryToDelete = Paths.get("C:\\tomcat\\webapps\\" + s);
            File check = new File("C:\\tomcat\\webapps\\" + s);
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
        System.out.println("value is: " + value);
    }
}