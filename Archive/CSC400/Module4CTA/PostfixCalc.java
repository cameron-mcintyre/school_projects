package CSC400.Module4CTA;
import javax.swing.*;
import javax.swing.BorderFactory;
import java.awt.*;
import java.util.Stack;

public class PostfixCalc {
    
    //Set up value stacks
    private Stack<String> inputStack = new Stack<String>();
    private Stack<String> calcStack = new Stack<String>();

    //Postfix calculator.  Includes methods for add, subtract, multiply, divide, mod.
    //Includes calculator GUI using swing.
    public static void main(String[] args){
        new PostfixCalc().calcGUI();
    }

    public void calcGUI(){

        //Set up the panels and frame
        JFrame mainFrame = new JFrame("PostFix Calculator");
        JPanel mainPanel = new JPanel();
        JPanel displayPanel = new JPanel();
        JPanel buttonsPanel = new JPanel();
        JPanel calcButtonPanel = new JPanel();
        BorderLayout mainPanelLayout = new BorderLayout(10,10);
        GridLayout displayLayout = new GridLayout(2, 1, 10, 10);
        GridLayout buttonLayout = new GridLayout(5, 4, 10, 10);

        //Setup buttons
        JButton button0 = new JButton("0");
        JButton button1 = new JButton("1");
        JButton button2 = new JButton("2");
        JButton button3 = new JButton("3");
        JButton button4 = new JButton("4");
        JButton button5 = new JButton("5");
        JButton button6 = new JButton("6");
        JButton button7 = new JButton("7");
        JButton button8 = new JButton("8");
        JButton button9 = new JButton("9");
        JButton addButton = new JButton("+");
        JButton subButton = new JButton("-");
        JButton multButton = new JButton("x");
        JButton divButton = new JButton("\u00F7");
        JButton modButton = new JButton("%");
        JButton clrButton = new JButton("CLR");
        JButton calcButton = new JButton("Calculate");
        
        //Set up display
        JTextField displayField = new JTextField();
        displayField.setEditable(false);
        JTextField outputField = new JTextField();
        outputField.setEditable(false);

        //Staff the display panel
        displayPanel.setLayout(displayLayout);
        displayPanel.add(outputField);
        displayPanel.add(displayField);

        //Staff the button panel
        buttonsPanel.setLayout(buttonLayout);
        buttonsPanel.add(new JLabel(""));
        buttonsPanel.add(new JLabel(""));
        buttonsPanel.add(clrButton);
        buttonsPanel.add(addButton);
        buttonsPanel.add(button1);
        buttonsPanel.add(button2);
        buttonsPanel.add(button3);
        buttonsPanel.add(subButton);
        buttonsPanel.add(button4);
        buttonsPanel.add(button5);
        buttonsPanel.add(button6);
        buttonsPanel.add(multButton);
        buttonsPanel.add(button7);
        buttonsPanel.add(button8);
        buttonsPanel.add(button9);
        buttonsPanel.add(divButton);
        buttonsPanel.add(new JLabel(""));
        buttonsPanel.add(button0);
        buttonsPanel.add(new JLabel(""));
        buttonsPanel.add(modButton);

        //Staff the calculate panel
        calcButtonPanel.add(calcButton);

        //Staff main panel
        mainPanel.setLayout(mainPanelLayout);
        mainPanel.add(displayPanel, BorderLayout.PAGE_START);
        mainPanel.add(buttonsPanel, BorderLayout.CENTER);
        mainPanel.add(calcButtonPanel, BorderLayout.PAGE_END);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        //Staff the frame with the panels
        mainFrame.add(mainPanel);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
        mainFrame.pack();

        //Add a million action listeners
        clrButton.addActionListener(_ -> {
            inputStack.clear();
            calcStack.clear();
            displayField.setText("");
            outputField.setText("");
        });
        
        addButton.addActionListener(_ -> {
            displayField.setText(displayField.getText() + "+");
            inputStack.push("+");
        });

        subButton.addActionListener(_ -> {
            displayField.setText(displayField.getText() + "-");
            inputStack.push("-");
        });

        multButton.addActionListener(_ -> {
            displayField.setText(displayField.getText() + "x");
            inputStack.push("*");
        });

        divButton.addActionListener(_ -> {
            displayField.setText(displayField.getText() + "\u00F7");
            inputStack.push("/");
        });

        modButton.addActionListener(_ -> {
            displayField.setText(displayField.getText() + "%");
            inputStack.push("%");
        });
        
        button0.addActionListener(_ -> {
            displayField.setText(displayField.getText() + "0");
            inputStack.push("0");
        });

        button1.addActionListener(_ -> {
            displayField.setText(displayField.getText() + "1");
            inputStack.push("1");
        });

        button2.addActionListener(_ -> {
            displayField.setText(displayField.getText() + "2");
            inputStack.push("2");
        });

        button3.addActionListener(_ -> {
            displayField.setText(displayField.getText() + "3");
            inputStack.push("3");
        });

        button4.addActionListener(_ -> {
            displayField.setText(displayField.getText() + "4");
            inputStack.push("4");
        });

        button5.addActionListener(_ -> {
            displayField.setText(displayField.getText() + "5");
            inputStack.push("5");
        });

        button6.addActionListener(_ -> {
            displayField.setText(displayField.getText() + "6");
            inputStack.push("6");
        });

        button7.addActionListener(_ -> {
            displayField.setText(displayField.getText() + "7");
            inputStack.push("7");
        });

        button8.addActionListener(_ -> {
            displayField.setText(displayField.getText() + "8");
            inputStack.push("8");
        });

        button9.addActionListener(_ -> {
            displayField.setText(displayField.getText() + "9");
            inputStack.push("9");
        });

        calcButton.addActionListener(_ -> {
            //There's a lot going on with this button.  Commenting line by line.
            String postFixResult; //Set up a string to display the postfix conversion.
            if(checkInputStack(inputStack) == false){ //We've got to check for bad stacks.
                inputStack.clear(); //If the stack is bad, we'll clear it out.
                outputField.setText("Error!"); //And set an error message.
                return; //Then get out of this control flow.
            } else {
                String outputResult = calculate(inputStack); //If the stack is good we can calculate.
                postFixResult = infixToPostfix(inputStack).toString(); //Convert to postfix string for display.
                displayField.setText("Postfix Expression: " + postFixResult); //Output the postfix.
                outputField.setText(outputResult); //Display the calculated result.
            }
        });
    }

    private Boolean checkInputStack(Stack<String> input){
        //If a user enters two operators in a row this method will produce and error.
        int size = input.size();
        Boolean isGood = true;

        //If the first/last item is an operator or the size is 0, the stack is bad.
        if(size == 0){
            isGood = false;
            return isGood;
        } else if(isAnOperator(input.get(0)) == true || isAnOperator(input.get(size - 1)) == true) {
            isGood = false;
            return isGood;
        } else {
            //If two operators follow each other, the stack is bad.
            for(int i = 0; i < size - 1; i++){
            
                String first = input.get(i);
                String second = input.get(i + 1);
    
                if(isAnOperator(first) == true && isAnOperator(second) == true){ isGood = false; return isGood; } 
                else { isGood = true; }
            }
        }
        //If none of the above is true, the stack is good.
        return isGood;
    }

    private String calculate(Stack<String> input){
        Stack<String> postfix = infixToPostfix(input);
        int x;
        int y;
        int size = postfix.size();
        Stack<Integer> calculateStack = new Stack<Integer>();

        //Uses the postfix stack to perform calculations.
        //Rules: 
        //---- If a number, move it to the calculate stack.
        //---- If an operator, pop off the two last number from the calculate stack and calculate.
        //---- Return final value on calculate stack.
        for(int i = 0; i < size; i++){

            //Check for number or operator.
            if(isAnOperator(postfix.get(i)) == false)
            {

                //If it's not an operator (thus, a number), add to stack.
                calculateStack.add(Integer.parseInt(postfix.get(i)));
            } else {

                //Perform calculations.
                y = calculateStack.pop();
                x = calculateStack.pop();

                if(y % 1 == 0 && x % 1 == 0){
                    if(postfix.get(i).equals("+")) { calculateStack.push(x + y); }
                    else if (postfix.get(i).equals("-")) { calculateStack.push(x - y); }
                    else if (postfix.get(i).equals("*")) { calculateStack.push(x * y); }
                    else if (postfix.get(i).equals("/")) { 
                        //This is a clunky way of stopping /0 from crashing the program.
                        if(y == 0) { calculateStack.push(0); System.out.println("Cannot divide by zero!");}
                        else {calculateStack.push(x / y);} }
                    else if (postfix.get(i).equals("%")) { calculateStack.push(x % y); }
                } else { 
                    System.out.println("Please enter a valid expression.");
                    return "Error!";
                }
            }
        }
        return calculateStack.pop().toString();
    }

    private Stack<String> infixToPostfix(Stack<String> infix){
        //Convert for multidigit numbers
        Stack<String> convertedStack = numberBuilder(infix);
        Stack<String> postfixStack = new Stack<String>();
        Stack<String> operators = new Stack<String>();

        //Figure out the stack size before looping.
        int conSize = convertedStack.size();

        //Start looping through the user input stack
        for(int i = 0; i < conSize; i++){

            //Check to see if the value in the stack is a number or not.
            if(isAnOperator(convertedStack.get(i)) == false){

                //If it's a number, add it to the final output stack.
                postfixStack.add(convertedStack.get(i));
            } else {

                //If it isn't a number, its an operator.
                //Now check to operator precedence.  If the operator is less important the the current 
                //top operator in the operators stack, the operator stack operator is popped and added 
                //to the output stack.  This builds the postfix stack.
                while(!operators.isEmpty() && 
                (precedent(convertedStack.get(i)) < precedent(operators.peek()) || 
                 precedent(convertedStack.get(i)) == precedent(operators.peek()))){
                    postfixStack.push(operators.pop());
                }
                operators.push(convertedStack.get(i));
            }
        }
        //Any remaining operators in the operator stack have to be added to the final output.
        while(!operators.isEmpty()){
            postfixStack.push(operators.pop());
        }
        return postfixStack;
    }

    private static Stack<String> numberBuilder(Stack<String> inputStack){    
        //Multidigit numbers are problematic.  This method treats numbers without operators between them 
        //as single numbers.
        String multiDigitNum = "";
        Stack<String> convertedStack = new Stack<String>();
        for(int i = 0; i < inputStack.size(); i++){
            switch(inputStack.get(i)){
                case "1": multiDigitNum = multiDigitNum + "1"; break;
                case "2": multiDigitNum = multiDigitNum + "2"; break;
                case "3": multiDigitNum = multiDigitNum + "3"; break;
                case "4": multiDigitNum = multiDigitNum + "4"; break;
                case "5": multiDigitNum = multiDigitNum + "5"; break;
                case "6": multiDigitNum = multiDigitNum + "6"; break;
                case "7": multiDigitNum = multiDigitNum + "7"; break;
                case "8": multiDigitNum = multiDigitNum + "8"; break;
                case "9": multiDigitNum = multiDigitNum + "9"; break;
                case "0": multiDigitNum = multiDigitNum + "0"; break;
                case "+": convertedStack.push(multiDigitNum); multiDigitNum = ""; convertedStack.push("+"); break;
                case "-": convertedStack.push(multiDigitNum); multiDigitNum = ""; convertedStack.push("-"); break;
                case "*": convertedStack.push(multiDigitNum); multiDigitNum = ""; convertedStack.push("*"); break;
                case "/": convertedStack.push(multiDigitNum); multiDigitNum = ""; convertedStack.push("/"); break;
                case "%": convertedStack.push(multiDigitNum); multiDigitNum = ""; convertedStack.push("%"); break;
                case "": break;
            }
        }
        if(multiDigitNum != ""){
            convertedStack.add(multiDigitNum);
        }
        return convertedStack;
    }

    //Method to determine precedence of operators
    private int precedent(String operator){
        if(operator.equals("*") || operator.equals("/") || operator.equals("%")){
            return 2;
        } else if(operator.equals("+") || operator.equals("-")){
            return 1;
        } else { return -1; }
    }

    private Boolean isAnOperator(String input){
        //Checks to see if an value is an operator or not.  I was doing this a lot, so I ended up just making a separate method for it.
        Boolean x;
        switch(input)
        {
            case "+": x = true; break;
            case "-": x = true; break;
            case "*": x = true; break;
            case "/": x = true; break;
            case "%": x = true; break;
            default: x = false; break;
        }
        return x;
    }
}