package View;

import Controller.ModuleController;
import Model.ModuleModel;
import Util.ImageUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.io.IOException;

/**
 * Created by alin.timu on 8/12/2014.
 */
public class ModuleView extends JPanel implements AbstractModuleView {
    private ModuleModel model;
    private JCheckBox enabler;
    protected String widgetName;
    protected JButton removeModule;
    protected JButton buildProject;
    protected String buildCommands;

    public ModuleView(ModuleModel moduleModel, String name) {
        this.model = moduleModel;
        this.widgetName = name;

        this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        this.setBorder(BorderFactory.createTitledBorder(widgetName));

        initializeComponents();
        this.add(enabler);
        this.add(buildProject);
        this.add(removeModule);
        this.setSize(600, 300);
        this.setVisible(true);
    }

    protected void initializeComponents() {
        enabler = new JCheckBox("Deploy " + widgetName);
        buildProject = new JButton("Build");
        removeModule = new JButton();
        ImageIcon removeIcon = new ImageIcon("src/resources/delete.png");
        removeIcon.setImage(ImageUtils.resizeImage(removeIcon.getImage(), 20, 20));
        removeModule.setIcon(removeIcon);
        removeModule.setOpaque(false);
        removeModule.setContentAreaFilled(false);
        removeModule.setBorderPainted(false);
        removeModule.setMaximumSize(new Dimension(50, 50));

        removeModule.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object source = e.getSource();
                if (source instanceof Component) {
                    Component comp = (Component)source;
                    MainWindowView.projectsPanel.remove(comp.getParent());
                    MainWindowView.revalidateRepaint();
                }
            }
        });
    }

    public JCheckBox getEnabler() {
        return enabler;
    }

    public void setEnabler(String enablerName) {
        this.enabler.setText(enablerName);
    }

    public String getWidgetName() {
        return widgetName;
    }

    public void setWidgetName(String widgetName) {
        this.widgetName = widgetName;
    }

    public String getBuildCommands() {
        return buildCommands;
    }

    public void setBuildCommands(String buildCommands) {
        this.buildCommands = buildCommands;
    }

    @Override
    public ModuleModel getModel() {
        return model;
    }

    @Override
    public void setModel(ModuleModel moduleModel) {
        this.model = moduleModel;
    }

    public void addBuildActionListener(ActionListener bal) {
        buildProject.addActionListener(bal);
    }

    public void addEnablerItemListener(ItemListener esl) {
        enabler.addItemListener(esl);
    }

}
