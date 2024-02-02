/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package calculator;

import java.util.Scanner;
import java.util.Stack;

/**
 *
 * @author bodyh
 */
public class PostFixConverter {

    //note this is stack for operators only 
     Stack<Character> st = new Stack<>();
     String postfix = "";
  
    
     int priority(char c) {
        if (c == '^') {
            return 3;
        } else if (c == '*' || c == '/') {
            return 2;
        } else if (c == '+' || c == '-') {
            return 1;
        }
        return -1;
    }

    void handelMultipleDigiteNumber(String equation, char c, int i) {
        char cAfter = (i < equation.length() - 1) ? equation.charAt(i + 1) : '$';
        if (cAfter != '$' && cAfter == '^' || cAfter == '*' || cAfter == '/' || cAfter == '+' || cAfter == '-' || cAfter == '(' || cAfter == ')') {
            postfix += c + " ";
        } else {
            postfix += c;
        }
    }

     public String convert(String equation) {
        int i = 0;
        while (i < equation.length()) {
            char c = equation.charAt(i);
            //check if char : 
            //operator : add it into stack
            if (c == '^' || c == '*' || c == '/' || c == '+' || c == '-') {
                //if stack empty or equation operator have higher priority than top of stack operator
                if (st.empty() || priority(c) > priority(st.peek())) {
                    st.push(c);
                } else {
                    //else pop all stack element and add all into postfix then push equation operator   
                    while (!st.empty() && priority(c) <= priority(st.peek())) {  //!st.empty() 
                        //while top has higher priority than input pop top and concate it with postfix 
                        if (st.peek() == '(') {
                            break;
                        }
                        postfix += st.pop();
                    }
                    st.push(c);
                }

            } else if (c == '(') {
                st.push(c);
            } else if (c == ')') {
                while (st.peek() != '(') {
                    postfix += st.pop();
                }
                st.pop();
            } else {
                //operand : add it into postfix 
                // if input : 333+5-(22*5) then we for example need to handel (333) number by using space 
                // the output will be : 333 5 +22 5 *-  rather than 3335+225*- 
                handelMultipleDigiteNumber(equation, c, i);
                //else: postfix += c;
            }
            i++;
        }

        //remove all stack elements after equation read and add all into postfix expression 
        postfix +=' ';
        while (!st.empty()) {
            postfix += st.pop();
        }

        // return postfix result
        return postfix;
    }
}
