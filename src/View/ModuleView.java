package View;

import Model.ModuleModel;
import Util.ImageUtils;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;

/**
 * Panel View for project modules.
 * Era bine daca faceam cate o interfata pentru fiecare clasa, cu toate functiile publice, comentate frumos ca sa stim exact ce putem folosi si cum.
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

    public ModuleView(ModuleModel moduleModel) {
        this.model = moduleModel;

        /* if model has data, construct View according to Model */
        if (model.getPath() != null) {
            constructViewFromModel(model);
        } else {
            constructModelFromView(this);
        }

        this.widgetName = model.getName();

        /* Initialize components and add them to the panel */
        initializeComponents();
        addComponentsToPanel();
        configureMainPanel();
    }

    /**
     * Initializes any components that need to be added to the ModuleView Panel
     */
    protected void initializeComponents() {
        final ImageIcon removeIcon = new ImageIcon("resources/images/delete.png");
        final ImageIcon removeIconHover = new ImageIcon("resources/images/delete_hover.png");

        enablerPanel = new JPanel();
        enablerPanel.setLayout(new BoxLayout(enablerPanel, BoxLayout.LINE_AXIS));
        buildPanel = new JPanel();
        buildPanel.setLayout(new BoxLayout(buildPanel, BoxLayout.LINE_AXIS));
        deletePanel = new JPanel();
        deletePanel.setLayout(new BoxLayout(deletePanel, BoxLayout.LINE_AXIS));
        deletePanel.setAlignmentX(Component.RIGHT_ALIGNMENT);

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

    /**
     * Adds initialized components to the ModuleView Panel
     */
    protected void addComponentsToPanel() {
        this.add(enablerPanel);
        this.add(buildPanel);
        this.add(deletePanel);
    }

    /**
     * Settings for the ModuleView Panel
     */
    protected void configureMainPanel() {
        this.setPreferredSize(new Dimension(800, 70));
        this.setMaximumSize(new Dimension(800, 70));
        this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        this.setBorder(BorderFactory.createTitledBorder(widgetName));
        this.setSize(600, 300);
        this.setVisible(true);
    }

    /* TODO Priority HIGH implement this motherfucker */
    protected void constructViewFromModel(ModuleModel moduleModel) {

    }

    /* TODO Priority HIGH implement this motherfucker */
    protected void constructModelFromView(ModuleView moduleView) {

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

    public void addRemoveActionListener(ActionListener ral) {
        removeModule.addActionListener(ral);
    }

}
