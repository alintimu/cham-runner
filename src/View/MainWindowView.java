package View;

import Controller.MainController;
import Model.TextFieldModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by alin.timu on 8/8/2014.
 */
public class MainWindowView extends JFrame implements AbstractWindowView, ItemListener {
    public JCheckBox templates_rb, cp_rb, selfService_rb;
    private String VIEW_IDENTIFIER = "";
    private MainController controller;
    private TextFieldModel textFieldModel = new TextFieldModel();
    // Components
    private JPanel windowPanel, templatesPanel, cpPanel, selfServicePanel;
    private JButton startButton, setPath;
    private JButton setPathTemplate = new JButton("Set path");
    private JButton setPathCp = new JButton("Set path");
    private JButton setPathSelfService = new JButton("Set path");

    public MainWindowView(String mainPageView) {
        VIEW_IDENTIFIER = mainPageView;

        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                controller.moveOnClose();
                System.exit(0);
            }
        });

        windowPanel = new JPanel();
        windowPanel.setLayout(new BoxLayout(windowPanel, BoxLayout.PAGE_AXIS));

        startButton = new JButton("Start");
        startButton.setPreferredSize(new Dimension(100,20));

        startButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
//                getInput();
                //controller.runStartButton();
                launchPopUp(1);
            }
        });

        windowPanel.add(constructTemplatesPanel());
        windowPanel.add(startButton);

        this.add(windowPanel);
        this.setSize(700, 400);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    /**
     * Construct the panel for the templates module.
     *
     * @return
     */
    private JPanel constructTemplatesPanel() {
        templatesPanel = new JPanel() {
            public Dimension getMinimumSize() {
                return getPreferredSize();
            }

            public Dimension getPreferredSize() {
                return new Dimension(600,
                        90);
            }

            public Dimension getMaximumSize() {
                return getPreferredSize();
            }
        };

        setPathTemplate.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                launchPopUp(1);
            }
        });

        templatesPanel.setLayout(new BoxLayout(templatesPanel, BoxLayout.LINE_AXIS));
        templatesPanel.setBorder(BorderFactory.createTitledBorder("Templates WAR"));

        JPanel temp_rb_panel = new JPanel();

        templates_rb = new JCheckBox("templates.war");
        templates_rb.addItemListener(this);

        templates_rb.setSelected(false);

        JPopupMenu popupMenu = new JPopupMenu();
        temp_rb_panel.add(templates_rb);

        templatesPanel.add(temp_rb_panel);
        templatesPanel.add(setPathTemplate);
        templatesPanel.add(popupMenu);

        return templatesPanel;
    }

    @Override
    public void run(MainController mainController) {
        this.controller = mainController;
        this.setVisible(true);
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getItem() == templates_rb) {
            if (templates_rb.isSelected()) {
                try {
                    this.controller.deployApp("templates", 1);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else if (!templates_rb.isSelected()) {
                try {
                    this.controller.deployApp("templates", 0);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }

        if (e.getItem() == cp_rb) {
            if (cp_rb.isSelected()) {
                try {
                    this.controller.deployApp("cp", 1);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else if (!cp_rb.isSelected()) {
                try {
                    this.controller.deployApp("cp", 0);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }

        if (e.getItem() == selfService_rb) {
            if (selfService_rb.isSelected()) {
                try {
                    this.controller.deployApp("selfService", 1);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else if (!templates_rb.isSelected()) {
                try {
                    this.controller.deployApp("selfService", 0);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public void launchPopUp(int app) {

        JPanel popupPanel = new JPanel();
        popupPanel.setLayout(new BoxLayout(popupPanel, BoxLayout.PAGE_AXIS));
        JTextField source_tf = new JTextField();
        JTextField dest_tf = new JTextField();

        source_tf.setPreferredSize(new Dimension(300, 25));
        dest_tf.setPreferredSize(new Dimension(300, 25));


        popupPanel.add(new JLabel("Source path:"));
        popupPanel.add(source_tf);
        popupPanel.add(new JLabel("Destination path:"));
        popupPanel.add(dest_tf);


        int result = JOptionPane.showConfirmDialog(null, popupPanel,
                "Enter the Source and Destination paths", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            switch (app){
                case 1: {
                    textFieldModel.setTemplateSourcePath(source_tf.getText());
                    textFieldModel.setTemplateDestinationPath(dest_tf.getText());
                }
                case 2: {
                    textFieldModel.setCpSourcePath(source_tf.getText());
                    textFieldModel.setCpDestinationPath(dest_tf.getText());
                }
                case 3: {
                    textFieldModel.setSelfServiceSourcePath(source_tf.getText());
                    textFieldModel.setSelfServiceDestinationPath(dest_tf.getText());
                }
            }

        }

    }
}
