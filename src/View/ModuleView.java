package View;

import Controller.ModuleController;
import Model.ModuleModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by alin.timu on 8/12/2014.
 */
public class ModuleView extends JPanel {
    private Boolean morphed = false;
    private ModuleModel moduleModel;
    private JButton popupHappened = new JButton("test");
    private JButton removeModule = new JButton("Remove Module");
    private JButton addThisModule = new JButton("Add Module");

    public ModuleView(ModuleModel moduleModel) {
        popupHappened.setVisible(false);
        this.moduleModel = moduleModel;
        this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        this.setBorder(BorderFactory.createTitledBorder("Templates WAR"));

        addThisModule.setPreferredSize(new Dimension(100,20));
        this.add(addThisModule);

    }

    public ModuleModel getModuleModel() {
        return moduleModel;
    }

    public void setModuleModel(ModuleModel moduleModel) {
        this.moduleModel = moduleModel;
    }

    public boolean isMorphed() {
        return morphed;
    }

    // TODO add actionListener to button inside of all these 3 bad-boys
    public void addRemoveModuleListener(ActionListener adm) {
        removeModule.addActionListener(adm);
    }

    public void addBuildModuleListener(ActionListener abm) {

    }

    public void addCheckboxStateListener(ActionListener acs) {

    }

    public void addAddModuleListener(ActionListener aam) {
        addThisModule.addActionListener(aam);
    }

    public void addPopupEventListener(ActionListener ape) {
        popupHappened.addActionListener(ape);
    }

    public void launchPopUp(int projectId, String projectName) {

        JPanel popupPanel = new JPanel();
        popupPanel.setLayout(new BoxLayout(popupPanel, BoxLayout.PAGE_AXIS));
        JTextField source_tf = new JTextField();

        source_tf.setPreferredSize(new Dimension(300, 25));

        popupPanel.add(new JLabel("Source path:"));
        popupPanel.add(source_tf);

        int result = JOptionPane.showConfirmDialog(null, popupPanel,
                "Enter the Source and Destination paths", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            popupHappened.doClick();
            morphView();
        }
    }

    public void morphView() {
        addThisModule.setVisible(false);
        morphed = true;
    }

}
