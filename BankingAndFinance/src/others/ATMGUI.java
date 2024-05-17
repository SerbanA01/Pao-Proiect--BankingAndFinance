/*
// ATMGUI.java
package others;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import service.ATM;

public class ATMGUI {
    private ATM atm;
    private JFrame frame;
    private JTextField accountField;
    private JTextField amountField;

    public ATMGUI(ATM atm) {
        this.atm = atm;
        frame = new JFrame("ATM");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);
        frame.setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        // ... same as before ...

        JButton depositButton = new JButton("Deposit");
        depositButton.setBounds(10, 80, 80, 25);
        depositButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                atm.deposit(accountField.getText(), Double.parseDouble(amountField.getText()));
            }
        });
        panel.add(depositButton);

        JButton withdrawButton = new JButton("Withdraw");
        withdrawButton.setBounds(100, 80, 80, 25);
        withdrawButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                atm.withdraw(accountField.getText(), Double.parseDouble(amountField.getText()));
            }
        });
        panel.add(withdrawButton);
    }
}*/
