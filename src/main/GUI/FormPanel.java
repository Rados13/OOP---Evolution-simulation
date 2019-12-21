package GUI;

import logic.ReadJson;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class FormPanel extends JPanel {

    private JLabel textLabel;
    private JTextField textField;
    private ArrayList<JTextField> listOfTexts;
    private ArrayList<String> listOfKeys;

    private JButton simulationButton;
    private int numberOfRows;

    FormPanel(JButton simulationButton) {
        this.simulationButton = simulationButton;
        Dimension dim = getPreferredSize();
        dim.width = 500;
        setPreferredSize(dim);

        Border innerBorder = BorderFactory.createTitledBorder("Change parameters of world");
        Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));

        setLayout(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();

        gc.anchor = GridBagConstraints.NORTH;
        gc.weightx = 1;
        gc.weighty = 1;

        ArrayList<ArrayList<String>> result = ReadJson.readFileForm();
        listOfTexts = new ArrayList<JTextField>();
        listOfKeys = new ArrayList<String>();
        for (int y = 0; y < result.size(); y++) {
            gc.gridx = 0;
            gc.gridy = y;

            textLabel = new JLabel(result.get(y).get(0));
            listOfKeys.add(textLabel.getText());
            add(textLabel, gc);
            gc.gridx = 1;
            textField = new JTextField(result.get(y).get(1), 10);
            listOfTexts.add(textField);
            add(textField, gc);
        }

        gc.gridy++;
        gc.gridx = 1;
        gc.anchor = GridBagConstraints.CENTER;
        simulationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                Object source = event.getSource();
                if (source == simulationButton) {
                    saveAllData();
                }
            }
        });

        add(simulationButton, gc);
        numberOfRows = gc.gridy;
    }

    private void saveAllData() {
        ArrayList<Double> values = new ArrayList<Double>();
        for (JTextField elem : listOfTexts) {
            values.add(Double.parseDouble(elem.getText()));
        }
        ReadJson.saveChanges(listOfKeys, values);
    }


}
