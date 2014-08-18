package View;

import Model.ModuleModel;
import javax.swing.*;
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

    public ModuleView(ModuleModel moduleModel, String name) {
        this.model = moduleModel;

        /* if model has data, construct View according to Model */
        if (model.getPath() != null) {
            constructViewFromModel(model);
        } else {
            constructModelFromView(this);
        }

        this.widgetName = name;

        /* Initialize components and add them to the panel */
        initializeComponents();
        addComponentsToPanel();
        configurePanel();

    }

    /**
     * Initializes any components that need to be added to the ModuleView Panel
     */
    protected void initializeComponents() {
        enabler = new JCheckBox("Deploy " + widgetName);
        removeModule = new JButton("Remove Module");
        buildProject = new JButton("Build");

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

    /**
     * Adds initialized components to the ModuleView Panel
     */
    protected void addComponentsToPanel() {
        this.add(enabler);
        this.add(removeModule);
        this.add(buildProject);
    }

    /**
     * Settings for the ModuleView Panel
     */
    protected void configurePanel() {
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

}
