package View;

import Controller.MainController;
import Model.TextFieldModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * Created by alin.timu on 8/8/2014.
 */
public class MainWindowView extends JFrame implements AbstractWindowView, ItemListener {
    public JCheckBox templates_rb, cp_rb, selfService_rb;
    public JTextField temp_source_tf, temp_dest_tf, cp_tf, selfService_tf;
    private String VIEW_IDENTIFIER = "";
    private MainController controller;
    private TextFieldModel textFieldModel = new TextFieldModel();
    // Components
    private JPanel windowPanel, templatesPanel, cpPanel, selfServicePanel;
    private JLabel sourceLabel, destinationLabel;
    private JButton startButton, setPath;
    private int width = 200, height = 50;


    public MainWindowView(String mainPageView) {
        VIEW_IDENTIFIER = mainPageView;

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        windowPanel = new JPanel();
        windowPanel.setLayout(new BoxLayout(windowPanel, BoxLayout.PAGE_AXIS));

        startButton = new JButton("Start");
        startButton.setPreferredSize(new Dimension(100,20));

        startButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
//                getInput();
                //controller.runStartButton();
            }
        });

        windowPanel.add(constructTemplatesPanel());
        windowPanel.add(startButton);

        //controller.changeView(MainWindowView.class);
        //controller.changeView(VIEW_IDENTIFIER);

        //controller.getView("");
        //controller.getRepository("");

        this.add(windowPanel);
        this.setSize(1200, 400);
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
                return new Dimension(1100,
                        90);
            }

            public Dimension getMaximumSize() {
                return getPreferredSize();
            }
        };

        templatesPanel.setLayout(new BoxLayout(templatesPanel, BoxLayout.LINE_AXIS));
        templatesPanel.setBorder(BorderFactory.createTitledBorder("Templates WAR"));


        JPanel sourcePanel = new JPanel();
        JPanel temp_rb_panel = new JPanel();

        templates_rb = new JCheckBox("templates.war");
        templates_rb.addItemListener(this);


        sourceLabel = new JLabel("Source path:");
        destinationLabel = new JLabel("Destination path:");

        temp_source_tf = new JTextField();
        temp_dest_tf = new JTextField();

        templates_rb.setSelected(false);

        JPopupMenu popupMenu = new JPopupMenu();
        temp_rb_panel.add(templates_rb);


        // Creating and adding the text field.

        temp_source_tf.setPreferredSize(new Dimension(300, 20));


        sourcePanel.add(sourceLabel);
        sourcePanel.add(temp_source_tf);


        // Creating and adding the text field.

        temp_dest_tf.setPreferredSize(new Dimension(300, 20));


        JPanel destPanel = new JPanel();
        destPanel.add(destinationLabel);
        destPanel.add(temp_dest_tf);


        templatesPanel.add(temp_rb_panel);
        templatesPanel.add(sourcePanel);
        templatesPanel.add(destPanel);
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
                    this.controller.moveToWebapps();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else if (!templates_rb.isSelected()) {
                try {
                    this.controller.moveToUndeployed();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public void getInput(){
        textFieldModel.setTemplateSourcePath(temp_source_tf.getText());
        textFieldModel.setTemplateDestinationPath(temp_dest_tf.getText());
        //temp_dest_tf.setText(textFieldModel.getTemplateSourcePath());
    }
}
