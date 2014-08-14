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
    // Components
    private String VIEW_IDENTIFIER = "";
    private MainController mainController;
    private List<ModuleView> moduleViewList = new ArrayList<>();
    private static JPanel windowPanel;
    private JPanel buttonsPanel;
    public static JPanel projectsPanel;
    private JButton startButton;
    private JButton addModule;

    public MainWindowView(String mainPageView) {
        VIEW_IDENTIFIER = mainPageView;
        initializeComponents();

        this.add(windowPanel);
        this.setSize(700, 400);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        // Listener for windowClosing -> move all remaining files to undeployed on exit
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                mainController.moveOnClose();
                System.exit(0);
            }
        });

    }

    private void initializeComponents() {
        windowPanel = new JPanel();
        projectsPanel = new JPanel();
        buttonsPanel = new JPanel();

        windowPanel.setLayout(new BoxLayout(windowPanel, BoxLayout.PAGE_AXIS));
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.LINE_AXIS));
        projectsPanel.setLayout(new BoxLayout(projectsPanel, BoxLayout.PAGE_AXIS));

        startButton = new JButton("Start");
        //startButton.setPreferredSize(new Dimension(100,20));
        startButton.setBounds(150, 30, 50, 20);

        addModule = new JButton("Add Module");
        addModule.setPreferredSize(new Dimension(100, 20));
        addModule.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                launchPopUp();
            }
        });
        buttonsPanel.add(addModule);
        buttonsPanel.add(startButton);
        windowPanel.add(projectsPanel);
        windowPanel.add(buttonsPanel);
    }

    public void run(MainController mainController) {
        this.mainController = mainController;
    }

    public void launchPopUp() {
        JPanel popupPanel = new JPanel();
        popupPanel.setLayout(new BoxLayout(popupPanel, BoxLayout.PAGE_AXIS));
        JTextField source_tf = new JTextField();

        source_tf.setPreferredSize(new Dimension(300, 25));

        popupPanel.add(new JLabel("Project name:"));
        popupPanel.add(source_tf);

        int result = JOptionPane.showConfirmDialog(null, popupPanel,
                "Enter the Source and Destination paths", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {

            createNewPanel(source_tf.getText());
        }
    }

    public void createNewPanel(String name) {
        ModuleModel moduleModel = new ModuleModel();
        mainController.modelToConfig(moduleModel);

        ModuleView moduleView = new ModuleView(moduleModel, name);
        ModuleController moduleController = new ModuleController(moduleView);

        projectsPanel.add(moduleView.widgetName, moduleView);
        windowPanel.revalidate();

        moduleViewList.add(moduleView);
    }

    public static void revalidateRepaint() {
        windowPanel.revalidate();
    }

}
