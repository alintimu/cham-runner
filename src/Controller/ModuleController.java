package Controller;

import Model.ModuleModel;
import View.ModuleView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alin.timu on 8/12/2014.
 */
public class ModuleController {
    private ModuleView moduleView;
    private ModuleModel moduleModel;

    public ModuleController(ModuleView moduleView) {
        this(moduleView, moduleView.getModuleModel());
    }

    public ModuleController(ModuleView moduleView, ModuleModel moduleModel) {
        this.moduleModel = moduleModel;
        this.moduleView = moduleView;

        moduleView.addAddModuleListener(new AddModuleListener());
        moduleView.addRemoveModuleListener(new RemoveModuleListener());
        moduleView.addBuildModuleListener(new BuildModuleListener());
        moduleView.addCheckboxStateListener(new CheckboxStateListener());
        moduleView.addPopupEventListener(new PopupEventListener());
    }

    private class AddModuleListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            moduleView.launchPopUp(1, "");
        }
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

    private class RemoveModuleListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    private class BuildModuleListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    private class CheckboxStateListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    private class PopupEventListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
/*            switch (projectId){
                case 1: {
                    textFieldModel.setTemplateSourcePath(source_tf.getText());
                    controller.setPathToXml(projectId, projectName);
                }
                case 2: {
                    textFieldModel.setCpSourcePath(source_tf.getText());
                    controller.setPathToXml(projectId, projectName);
                }
                case 3: {
                    textFieldModel.setSelfServiceSourcePath(source_tf.getText());
                    controller.setPathToXml(projectId, projectName);
                }
            }*/
        }
    }
}
