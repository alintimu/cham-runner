package Controller;

import Model.ModuleModel;
import View.ModuleView;
import org.apache.maven.shared.invoker.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.net.URL;
import java.util.Arrays;

/**
 * Controller for project modules
 */
public class ModuleController implements Runnable{
    private ModuleView moduleView;
    private ModuleModel moduleModel;
    Thread buildThread;

    public ModuleController(ModuleView moduleView) {
        this(moduleView, moduleView.getModel());
    }

    public ModuleController(ModuleView moduleView, ModuleModel moduleModel) {
        buildThread = new Thread(this, "Project Builder Thread");
        this.moduleModel = moduleModel;
        this.moduleView = moduleView;

        moduleView.addBuildActionListener(new BuildActionListener());
        moduleView.addEnablerItemListener(new EnablerItemListener());
    }

    /**
     * moves .war files into and out of the webapps dir
     *
     * @param fileName
     * @param opt 0 out of webapps; 1 into webapps;
     */
    public void deployApp(String fileName, int opt) {
        String undeployed_dir = "C:\\tomcat\\undeployed\\";
        String webapps_dir = "C:\\tomcat\\webapps\\";

        if (opt == 0) {
            moveToDir(webapps_dir, undeployed_dir, fileName);
        } else if (opt == 1) {
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

    // TODO Priority MED check for build params at some point add them as goals
    public void buildProject() {
        InvocationRequest request = new DefaultInvocationRequest();
        request.setPomFile(new File(moduleModel.getPath() + "\\pom.xml"));
        request.setGoals(Arrays.asList("clean", "install", "-Dskiptests"));

        Invoker invoker = new DefaultInvoker();
        try {
            invoker.execute(request);
        } catch (MavenInvocationException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        buildProject();
    }

    private class BuildActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            buildThread.start();
        }
    }

    private class EnablerItemListener implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent e) {
            // getStateChanged selected = 1, deselected = 2
            if (e.getStateChange() == 1) {
                deployApp(moduleView.getWidgetName(), 1);
                moduleView.setEnabler("Undeploy " + moduleView.getWidgetName());
            } else {
                deployApp(moduleView.getWidgetName(), 0);
                moduleView.setEnabler("Deploy " + moduleView.getWidgetName());
            }
        }
    }
}
