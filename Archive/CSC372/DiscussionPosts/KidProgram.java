package CSC372_projects.DiscussionPosts;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.GridLayout;

public class KidProgram {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Kid's Calculator!");
        frame.setSize(300, 300);
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(2, 2, 2, 2));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10 ,10 ,10));

        JTextField number1 = new JTextField();
        JTextField number2 = new JTextField();
        JTextField result = new JTextField();
        result.setEditable(false);
        JButton calculate = new JButton("Add!");

        calculate.addActionListener(_ -> {
            
            int num1 = Integer.valueOf(number1.getText());
            int num2 = Integer.valueOf(number2.getText());
            
            result.setText(String.valueOf(num1 - num2));
        });

        mainPanel.add(number1);
        mainPanel.add(number2);
        mainPanel.add(result);
        mainPanel.add(calculate);
        frame.add(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}