package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ParametersPanel extends JPanel implements ActionListener {


    private IChangePanelListener listener;
    private JButton backButton;
    private FormPanel form;


    public ParametersPanel(IChangePanelListener listener) {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEtchedBorder());
        this.listener = listener;

        backButton = new JButton("Back");
        backButton.addActionListener(this);

        form = new FormPanel();

        add(form);

        form.addBackButton(backButton);
    }


    @Override
    public void actionPerformed(ActionEvent event) {
        Object source = event.getSource();

        if (source == backButton) {
            listener.goBackToMenu();
        }
    }
}
