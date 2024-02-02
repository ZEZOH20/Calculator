/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
#1A212D
 */
package calculator;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

/**
 *
 * @author bodyh
 */
public class Controller implements Initializable {

    @FXML
    private TextArea resultArea;
    @FXML
    private Text screenEquation;

    @FXML
    private Text screenResult;

    private static String savedResult;

    void calc(String equation) {
        String postFix = new PostFixConverter().convert(equation);
        double result = EvaluatePostFix.evaluate(postFix);
        savedResult = String.valueOf(result);
        screenResult.setText(savedResult);
        //temp
        //System.out.println("equation: "+sEquation);
        System.out.println("postFix: " + postFix);
        System.out.println("result: " + result);
    }

    void setOperand(String Operand) {
        String sResult = screenResult.getText();
        //System.out.println(sResult);
        if (sResult.equals("0") || sResult.equals(savedResult)) { //add new number
            screenResult.setText(Operand);
        } else {                                   //concate on previous number
            screenResult.setText(sResult + Operand);
        }
        if(screenResult.getX()<resultArea.getWidth()-20){
//         screenResult.setStyle("-fx-font : 12 arial;");
        }
//        screenResult.setX(screenResult.getX() - 4);

    }

    void setOperator(String operator) {
        String sEquation = screenEquation.getText();
        String sResult = screenResult.getText();
        if (sEquation.equals("") && sResult.equals("0")) { //screenEquation: " " , screenResult: 0
            sEquation = "0" + operator;
            savedResult = "0";
        } else if (sEquation.equals("")) { //screenEquation: " " , screenResult: anyNumber
            sEquation = sResult + operator;
            savedResult = sResult;
        } else if (sResult.equals(savedResult)) { //screenEquation: " savedResult ex: 5+2 " , screenResult: "sResult ex: 7" 
            sEquation = sEquation.substring(0, sEquation.length() - 1) + operator;
        } else {                                        //screenEquation: " savedResult ex: 5+2 " , screenResult: "sResult ex: 88" 
            sEquation += sResult + operator;

            //find result
            calc(sEquation);

        }
        screenEquation.setText(sEquation);
    }

    void setEqual() {
        String sEquation = screenEquation.getText();
        String sResult = screenResult.getText();
        if (sResult.equals("0")) {
            sEquation = "0";
            savedResult = "0";
        } else if (sEquation.equals("")) { //screenEquation: " " , screenResult: anyNumber
            sEquation = sResult;
            savedResult = sResult;
        } else {
            sEquation += sResult;
            //find result
            calc(sEquation);
            sEquation = savedResult;
        }
        screenEquation.setText(sEquation);
    }
    
    void setClear(){
       screenEquation.setText(""); 
       screenResult.setText("0"); 
       savedResult= "0";
    }

    @FXML
    void btn0(MouseEvent event) {
        setOperand("0");
    }

    @FXML
    void btn1(MouseEvent event) {
        setOperand("1");
    }

    @FXML
    void btn2(MouseEvent event) {
        setOperand("2");
    }

    @FXML
    void btn3(MouseEvent event) {
        setOperand("3");
    }

    @FXML
    void btn4(MouseEvent event) {
        setOperand("4");
    }

    @FXML
    void btn5(MouseEvent event) {
        setOperand("5");
    }

    @FXML
    void btn6(MouseEvent event) {
        setOperand("6");
    }

    @FXML
    void btn7(MouseEvent event) {
        setOperand("7");
    }

    @FXML
    void btn8(MouseEvent event) {
        setOperand("8");
    }

    @FXML
    void btn9(MouseEvent event) {
        setOperand("9");
    }

    @FXML
    void btnDot(MouseEvent event) {
        setOperand(".");
    }

    @FXML
    void btnMinus(MouseEvent event) {
        setOperator("-");
    }

    @FXML
    void btnMultiply(MouseEvent event) {
        setOperator("*");
    }

    @FXML
    void btnPlus(MouseEvent event) {
        setOperator("+");
    }

    @FXML
    void btnPow(MouseEvent event) {
        setOperator("^");
    }

    @FXML
    void btndivide(MouseEvent event) {
        setOperator("/");
    }

    @FXML
    void btnOpen(MouseEvent event) {
//        setOperator("(");
    }

    @FXML
    void btnClose(MouseEvent event) {
//        setOperator(")");
    }

    @FXML
    void btnEqual(MouseEvent event) {
        setEqual();
    }

    @FXML
    void btnClear(MouseEvent event) {
        setClear();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // show postfix result
//        Scanner s = new Scanner(System.in);
//        String equation = s.nextLine();
//        String postFix = new PostFixConverter().convert(equation);
//        System.out.println(postFix);
//        System.out.println(EvaluatePostFix.evaluate(postFix));
    }

}
