package View;

import Controller.MainController;
import Controller.ModuleController;
import Model.ModuleModel;

import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * Created by alin.timu on 8/8/2014.
 */
public class MainWindowView extends JFrame {
    private String VIEW_IDENTIFIER = "";
    private ModuleController moduleController;
    private List<ModuleView> moduleViewList = new ArrayList<>();
    // Components
    private JPanel windowPanel;
    private JButton startButton, addModule;

    public MainWindowView(String mainPageView) {
        ModuleView initialModuleView = new ModuleView(new ModuleModel());
        moduleController = new ModuleController(initialModuleView);
        windowPanel = new JPanel();
        windowPanel.setLayout(new BoxLayout(windowPanel, BoxLayout.PAGE_AXIS));

        moduleViewList.add(initialModuleView);
        for (ModuleView m : moduleViewList) {
            windowPanel.add(m);
            if (m.isMorphed()) {
                moduleViewList.add(new ModuleView(new ModuleModel()));
                break;
            }
        }
        VIEW_IDENTIFIER = mainPageView;

        startButton = new JButton("Start");
        startButton.setPreferredSize(new Dimension(100,20));

        windowPanel.add(startButton);

        this.add(windowPanel);
        this.setSize(700, 400);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void addModuleView(ModuleView moduleView) {
        moduleViewList.add(moduleView);
    }

    public void addStartButtonListener(ActionListener asb) {

    }

    public void addWinListener(WindowListener aw) {
        this.addWindowListener(aw);
    }
}
