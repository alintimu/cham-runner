package Controller;

import Model.ModuleModel;
import View.ModuleView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;

/**
 * Created by alin.timu on 8/12/2014.
 */
public class ModuleController {
    private ModuleView moduleView;
    private ModuleModel moduleModel;

    public ModuleController(ModuleView moduleView) {
        this(moduleView, moduleView.getModel());
    }

    public ModuleController(ModuleView moduleView, ModuleModel moduleModel) {
        this.moduleModel = moduleModel;
        this.moduleView = moduleView;

        moduleView.addBuildActionListener(new BuildActionListener());
        moduleView.addEnablerItemListener(new EnablerItemListener());
    }

    /**
     * moves .war files into and out of the webapps dir
     *
     * @param fileName
     * @param opt      0 out of webapps; 1 into webapps;
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

    public void buildProject() {

    }

    private class BuildActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            moduleView.getBuildCommands();
        }
    }

    private class EnablerItemListener implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent e) {
            // selected = 1, deselected = 2
            if (e.getStateChange() == 1) {
                moduleView.setEnabler("Undeploy " + moduleView.getWidgetName());
            } else {
                moduleView.setEnabler("Deploy " + moduleView.getWidgetName());
            }
        }
    }
}
