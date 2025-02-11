package CSC372_projects.CTA5;

import java.util.*;
import java.awt.*;
import javax.swing.*;

public class Recursion_CTA5_GUI extends Recursion_CTA5_recursiveMethod {
    
    //Creates the main frame and panels for the GUI.  Two buttons have action listeners - one to start the recursive calculation, and one to reset the UI back to empty fields.
    public static void MainGUI() {
        
        JFrame mainFrame = new JFrame("Recursion Program");
        mainFrame.setSize(1000, 120);
        JPanel mainPanel = new JPanel();
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        mainPanel.setLayout(new GridLayout(2, 7, 2, 2));

        JLabel num1title = new JLabel("Number 1");
        JLabel num2title = new JLabel("Number 2");
        JLabel num3title = new JLabel("Number 3");
        JLabel num4title = new JLabel("Number 4");
        JLabel num5title = new JLabel("Number 5");
        JLabel fintitle = new JLabel("Final Answer!");

        JTextField num1 = new JTextField(5);
        JTextField num2 = new JTextField(5);
        JTextField num3 = new JTextField(5);
        JTextField num4 = new JTextField(5);
        JTextField num5 = new JTextField(5);
        JTextField finalAns = new JTextField(20);
        finalAns.setEditable(false);
        JButton calculate1 = new JButton("Calculate!");
        JButton reset = new JButton("Reset");

        calculate1.addActionListener(event -> {

            int index = 0;
            Long result;
            String resultString;
            ArrayList<Long> numbersList = new ArrayList<Long>();

            numbersList.add(stringToInt(checkInputNumLength(num1.getText())));
            numbersList.add(stringToInt(checkInputNumLength(num2.getText())));
            numbersList.add(stringToInt(checkInputNumLength(num3.getText())));
            numbersList.add(stringToInt(checkInputNumLength(num4.getText())));
            numbersList.add(stringToInt(checkInputNumLength(num5.getText())));

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

    //This method converts the string inputs from JTextFields into longs for use in the recursive calculator.  A string is input into the method, and the method returns a long, or generates an error message in the event a non-number is entered.
    public static Long stringToInt(String string){
        try {
            return Long.parseLong(string);
        } catch (NumberFormatException e) {
            System.out.println("Error - invalid entry made!");
            return Long.valueOf(-1);
        }
        
    }

    //This method accepts the Long that is the result of the recursive calculations and returns it as a string for display in the GUI.
    public static String intToString(Long result){
        return String.valueOf(result);
    }

    //Overly large numbers tend to blow up during calculation - this method will generate an error message if large numbers are entered as values.  If a number is entered that is overly large (>4 digits), a 0 is returned and an error message displayed.
    public static String checkInputNumLength(String string) {
        if (string.length() > 4) {
            System.out.println("Values cannot be more than four digits!");
            return "0";
        } else {
            return string;
        }
    }

    //Main method for entry.
    public static void main(String[] args) {

        MainGUI();
    }
}
