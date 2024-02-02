/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template (line 41)
 */
package calculator;

import java.util.Stack;

/**
 *
 * @author bodyh
 */
public class EvaluatePostFix {

    //note this is stack for numbers only 
    private static Stack<Double> st = new Stack<>();
    private static double result;

    //evaluate
    static double evaluate(String postFix) {

        //note to use foreach on string you need to convert it into array firstly
        String operand = "";
        for (char c : postFix.toCharArray()) {

            if (c == '^' || c == '*' || c == '/' || c == '+' || c == '-') {
                // if a or b == -0.0 retun can't resolve
                double num1 = (!st.empty()) ? st.pop() : 0.0;
                double num2 = (!st.empty()) ? st.pop() : 0.0;
                // calc 2 top of stack numbers according to operator 
                result = makeDecision(num2, num1, c);
                // push result into stack
                st.push(result);
                continue;
            }
            if (c == ' ') {
                //  handel (parseDouble("")) empty string
                if (operand == "") {
                    operand = "0";
                }
                //System.out.println(Double.parseDouble(operand));
                st.push(Double.parseDouble(operand));
                operand = "";
                continue;
            }
            operand += c;
        }
        // result always top of stack
        result = st.peek();
        return result;
    }

    //makeDecision
    static double makeDecision(double a, double b, char operator) {
        double result = 0.0;
        //else
        switch (operator) {
            case '+':
                result = add(a, b);
                break;
            case '-':
                result = subtract(a, b);
                break;
            case '*':
                result = multiply(a, b);
                break;
            case '/':
                result = divide(a, b);
                break;
            case '^':
                result = expon(a, b);
                break;

        }
        // if a or b == -0.0 retun can't resolve (a == -0.0 || b == -0.0) ? -0.0 : result
        return result;
    }

    //addition // Double.parseDouble(a)
    private static double add(double a, double b) {
        return a + b;
    }

    //Subtraction
    private static double subtract(double a, double b) {
        return a - b;
    }

    //Multiplication
    private static double multiply(double a, double b) {
        return a * b;
    }

    //division
    private static double divide(double a, double b) {
        return a / b;
    }

    //exponential
    private static double expon(double a, double b) {
        return Math.pow(a, b);
    }

}
