package View;

import Controller.ModuleController;
import Model.ModuleModel;
import Util.ImageUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
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
    private JPanel enablerPanel;
    private JPanel buildPanel;
    private JPanel deletePanel;

    public ModuleView(ModuleModel moduleModel, String name) {
        this.model = moduleModel;
        model.setName(name);
        this.widgetName = name;

        this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        this.setBorder(BorderFactory.createTitledBorder(widgetName));

        initializeComponents();
        this.add(enablerPanel);
        this.add(buildPanel);
        deletePanel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        this.add(deletePanel);
        this.setPreferredSize(new Dimension(800, 70));
        this.setMaximumSize(new Dimension(800, 70));
        this.setVisible(true);
    }

    protected void initializeComponents() {
        final ImageIcon removeIcon = new ImageIcon("resources/images/delete.png");
        final ImageIcon removeIconHover = new ImageIcon("resources/images/delete_hover.png");

        enablerPanel = new JPanel();
        enablerPanel.setLayout(new BoxLayout(enablerPanel, BoxLayout.LINE_AXIS));
        buildPanel = new JPanel();
        buildPanel.setLayout(new BoxLayout(buildPanel, BoxLayout.LINE_AXIS));
        deletePanel = new JPanel();
        deletePanel.setLayout(new BoxLayout(deletePanel, BoxLayout.LINE_AXIS));

        enabler = new JCheckBox("Deploy " + widgetName);
        enabler.setMaximumSize(new Dimension(700, 30));
        enabler.setPreferredSize(new Dimension(700, 30));
        buildProject = new JButton("Build");
        buildProject.setMaximumSize(new Dimension(100, 30));
        removeModule = new JButton();
        removeIcon.setImage(ImageUtils.resizeImage(removeIcon.getImage(), 20, 20));
        removeIconHover.setImage(ImageUtils.resizeImage(removeIconHover.getImage(), 20, 20));
        removeModule.setIcon(removeIcon);
        removeModule.getModel().addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                ButtonModel model = (ButtonModel) e.getSource();
                if (model.isRollover()) {
                    removeModule.setIcon(removeIconHover);
                } else {
                    removeModule.setIcon(removeIcon);
                }
            }
        });
        removeModule.setOpaque(false);
        removeModule.setContentAreaFilled(false);
        removeModule.setBorderPainted(false);

        removeModule.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object source = e.getSource();
                if (source instanceof Component) {
                    Component comp = (Component)source;
                    MainWindowView.projectsPanel.remove(comp.getParent().getParent());
                    MainWindowView.revalidateRepaint();
                }
            }
        });
        enablerPanel.add(enabler);
        buildPanel.add(buildProject);
        deletePanel.add(removeModule);
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
