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

        // Listener for windowClosing -> move all remaining files to undeployed on exit
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
                //launchPopUp(1);
            }
        });

        windowPanel.add(constructTemplatesPanel());
        windowPanel.add(constructCpPanel());
        windowPanel.add(constructSelfServicePanel());
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

    /**
     * Construct the panel for the control panel module.
     *
     * @return
     */
    private JPanel constructCpPanel() {
        cpPanel = new JPanel() {
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

        setPathCp.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                launchPopUp(2);
            }
        });

        cpPanel.setLayout(new BoxLayout(cpPanel, BoxLayout.LINE_AXIS));
        cpPanel.setBorder(BorderFactory.createTitledBorder("Control Panel WAR"));

        JPanel cp_rb_panel = new JPanel();

        cp_rb = new JCheckBox("cp.war");
        cp_rb.addItemListener(this);

        cp_rb.setSelected(false);

        JPopupMenu popupMenu = new JPopupMenu();
        cp_rb_panel.add(cp_rb);

        cpPanel.add(cp_rb_panel);
        cpPanel.add(setPathCp);
        cpPanel.add(popupMenu);

        return cpPanel;
    }

    private JPanel constructSelfServicePanel() {
        selfServicePanel = new JPanel() {
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

        setPathSelfService.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                launchPopUp(3);
            }
        });

        selfServicePanel.setLayout(new BoxLayout(selfServicePanel, BoxLayout.LINE_AXIS));
        selfServicePanel.setBorder(BorderFactory.createTitledBorder("Self Service WAR"));

        JPanel selfService_rb_panel = new JPanel();

        selfService_rb = new JCheckBox("selfService.war");
        selfService_rb.addItemListener(this);

        selfService_rb.setSelected(false);

        JPopupMenu popupMenu = new JPopupMenu();
        selfService_rb_panel.add(selfService_rb);

        selfServicePanel.add(selfService_rb_panel);
        selfServicePanel.add(setPathSelfService);
        selfServicePanel.add(popupMenu);

        return selfServicePanel;
    }

    @Override
    public void run(MainController mainController) {
        this.controller = mainController;
        this.setVisible(true);
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        // if checkbox ticked
        if (e.getItem() == templates_rb) {
            if (templates_rb.isSelected()) {
                try {
                    // move templates in webapps
                    this.controller.deployApp("templates", 1);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else if (!templates_rb.isSelected()) {
                try {
                    // move templates out of webapps
                    this.controller.deployApp("templates", 0);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }

        // if checkbox ticked
        if (e.getItem() == cp_rb) {
            if (cp_rb.isSelected()) {
                try {
                    // move cp into webapps
                    this.controller.deployApp("cp", 1);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else if (!cp_rb.isSelected()) {
                try {
                    // move cp out of webapps
                    this.controller.deployApp("cp", 0);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }

        // if checkbox ticked
        if (e.getItem() == selfService_rb) {
            if (selfService_rb.isSelected()) {
                try {
                    // move selfService into webapps
                    this.controller.deployApp("selfService", 1);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else if (!templates_rb.isSelected()) {
                try {
                    // move selfService out of webapps
                    this.controller.deployApp("selfService", 0);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public void launchPopUp(int projectId) {

        JPanel popupPanel = new JPanel();
        popupPanel.setLayout(new BoxLayout(popupPanel, BoxLayout.PAGE_AXIS));
        JTextField source_tf = new JTextField();

        source_tf.setPreferredSize(new Dimension(300, 25));


        popupPanel.add(new JLabel("Source path:"));
        popupPanel.add(source_tf);


        int result = JOptionPane.showConfirmDialog(null, popupPanel,
                "Enter the Source and Destination paths", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            switch (projectId){
                case 1: {
                    textFieldModel.setTemplateSourcePath(source_tf.getText());
                }
                case 2: {
                    textFieldModel.setCpSourcePath(source_tf.getText());
                }
                case 3: {
                    textFieldModel.setSelfServiceSourcePath(source_tf.getText());
                }
            }

        }

    }
}
