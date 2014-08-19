package View;

import Controller.MainController;
import Controller.ModuleController;
import Model.ModuleModel;
import com.sun.xml.internal.ws.api.server.Module;

import java.io.File;
import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * Main application view.
 */
public class MainWindowView extends JFrame {
    // Components
    private String VIEW_IDENTIFIER = "";
    private MainController mainController;
    private List<ModuleView> moduleViewList = new ArrayList<>();
    private static JPanel windowPanel;
    private JPanel buttonsPanel;
    private JPanel addPanel;
    private JPanel startPanel;
    public static JPanel projectsPanel;
    private JButton startButton;
    private JButton addModule;
    private JScrollPane scrollPane;

    public MainWindowView(String mainPageView) {
        VIEW_IDENTIFIER = mainPageView;
        initializeComponents();
        scrollPane = new JScrollPane(windowPanel);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        this.add(scrollPane);
        this.setSize(900, 400);
        this.setResizable(false);
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
        addPanel = new JPanel();
        startPanel = new JPanel();

        windowPanel.setLayout(new BoxLayout(windowPanel, BoxLayout.PAGE_AXIS));
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.PAGE_AXIS));
        projectsPanel.setLayout(new BoxLayout(projectsPanel, BoxLayout.PAGE_AXIS));
        addPanel.setLayout(new BoxLayout(addPanel, BoxLayout.LINE_AXIS));
        startPanel.setLayout(new BoxLayout(startPanel, BoxLayout.LINE_AXIS));

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
        addPanel.add(addModule);
        startPanel.add(startButton);
        buttonsPanel.add(Box.createVerticalStrut(10));
        buttonsPanel.add(addPanel);
        buttonsPanel.add(Box.createVerticalStrut(20));
        buttonsPanel.add(startPanel);
        windowPanel.add(projectsPanel);
        windowPanel.add(buttonsPanel);
    }

    public void run(MainController mainController) {
        this.mainController = mainController;
    }

    public void launchPopUp() {
        JPanel popupPanel = new JPanel();
        popupPanel.setLayout(new BoxLayout(popupPanel, BoxLayout.PAGE_AXIS));
        JTextField projectName = new JTextField();
        projectName.setPreferredSize(new Dimension(300, 25));
        final JTextField projectPath = new JTextField();
        projectPath.setPreferredSize(new Dimension(300, 25));
        JButton browse = new JButton("Browse");
        browse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fileChooser(projectPath);
            }
        });

        popupPanel.add(new JLabel("Project name:"));
        popupPanel.add(projectName);
        popupPanel.add(new JLabel("Project root path:"));
        popupPanel.add(projectPath);
        popupPanel.add(Box.createVerticalStrut(6));
        popupPanel.add(browse);

        int result = JOptionPane.showConfirmDialog(null, popupPanel,
                "Enter the Source and Destination paths", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            createNewPanel(projectName.getText(), projectPath.getText());
        }
    }

    /**
     * Creates a new module and adds it to the main window
     * Don't use this when reading from the config, as it also writes to the config
     * @param name - The name of the new module
     */
    public void createNewPanel(String name, String path) {
        ModuleModel moduleModel = new ModuleModel(name, path);
        mainController.modelToConfig(moduleModel);

        ModuleView moduleView = new ModuleView(moduleModel);
        moduleView.addRemoveActionListener(new RemoveActionListener());

        ModuleController moduleController = new ModuleController(moduleView);

        projectsPanel.add(moduleView.widgetName, moduleView);
        revalidateRepaint();

        moduleViewList.add(moduleView);
    }

    public void addModuleFromConfig(ModuleModel moduleModel) {
        ModuleView moduleView = new ModuleView(moduleModel);
        moduleView.addRemoveActionListener(new RemoveActionListener());

        ModuleController moduleController = new ModuleController(moduleView);

        projectsPanel.add(moduleView.widgetName, moduleView);
        revalidateRepaint();

        moduleViewList.add(moduleView);
    }

    private void fileChooser(JTextField projectPath) {
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("."));
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.showOpenDialog(null);
        File selectedFile = chooser.getSelectedFile();
        if(selectedFile != null) {
            projectPath.setText(selectedFile.getAbsolutePath());
        }
    }

    public static void revalidateRepaint() {
        windowPanel.revalidate();
    }

    private class RemoveActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton removeButton = (JButton) e.getSource();
            ModuleView moduleView = (ModuleView) removeButton.getParent().getParent();
            mainController.removeModelFromConfig(moduleView.getModel());
        }
    }
}
