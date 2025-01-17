package CSC372_projects.CTA5;

import java.util.*;
import java.awt.*;
import javax.swing.*;

public class RecursionDemo {
    
    public static void MainGUI() {
        
        JFrame mainFrame = new JFrame("Recursion Program");
        mainFrame.setSize(800, 120);
        JPanel mainPanel = new JPanel();
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        mainPanel.setLayout(new GridLayout(2, 7, 2, 2));

        JLabel num1title = new JLabel("Number 1");
        JLabel num2title = new JLabel("Number 2");
        JLabel num3title = new JLabel("Number 3");
        JLabel num4title = new JLabel("Number 4");
        JLabel num5title = new JLabel("Number 5");
        JLabel fintitle = new JLabel("Final Answer!");

        JTextField num1 = new JTextField(10);
        JTextField num2 = new JTextField(10);
        JTextField num3 = new JTextField(10);
        JTextField num4 = new JTextField(10);
        JTextField num5 = new JTextField(10);
        JTextField finalAns = new JTextField(10);
        finalAns.setEditable(false);
        JButton calculate1 = new JButton("Calculate!");
        JButton reset = new JButton("Reset");

        calculate1.addActionListener(event -> {

            int index = 0;
            int result;
            String resultString;
            ArrayList<Integer> numbersList = new ArrayList<Integer>();

            numbersList.add(stringToInt(num1.getText()));
            numbersList.add(stringToInt(num2.getText()));
            numbersList.add(stringToInt(num3.getText()));
            numbersList.add(stringToInt(num4.getText()));
            numbersList.add(stringToInt(num5.getText()));

            result = recursiveMethod(numbersList, index);

            resultString = intToString(result);

            finalAns.setText(resultString);
        });

        reset.addActionListener(event -> {
            num1.setText("");
            num2.setText("");
            num3.setText("");
            num4.setText("");
            num5.setText("");
            finalAns.setText("");
        });

        mainPanel.add(num1title);
        mainPanel.add(num2title);
        mainPanel.add(num3title);
        mainPanel.add(num4title);
        mainPanel.add(num5title);
        mainPanel.add(fintitle);
        mainPanel.add(calculate1);

        mainPanel.add(num1);
        mainPanel.add(num2);
        mainPanel.add(num3);
        mainPanel.add(num4);
        mainPanel.add(num5);
        mainPanel.add(finalAns);
        mainPanel.add(reset);

        mainFrame.add(mainPanel);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
    }

    public static int stringToInt(String string){
        return Integer.parseInt(string);
    }

    public static String intToString(Integer result){
        return String.valueOf(result);
    }

    public static int recursiveMethod(ArrayList<Integer> numbersList, Integer index) {

        if (numbersList.size() == index){
            return 1;
        } else {
            return numbersList.get(index) * recursiveMethod(numbersList, index + 1);
        }
    }

    public static void main(String[] args) {

        MainGUI();
    }
}
