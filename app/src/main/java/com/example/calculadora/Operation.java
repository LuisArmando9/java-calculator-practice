package com.example.calculadora;

import android.content.Context;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Operation {


    public static final char DIVISION = '÷';
    public static  final char SUBTRACTION = '-';
    public static final char ADDITION = '+';
    public static final char MULTIPLICATION = 'x';
    public static final char EQUAL = '=';
    private static  final short STACK_END = 1;

    public static boolean  is(char symbol)
    {
        switch (symbol)
        {
            case Operation.DIVISION :
            case  Operation.ADDITION:
            case  Operation.MULTIPLICATION:
            case  Operation.SUBTRACTION:
                return  true;
            default:
               return false;

        }
    }
    private static double computeOperation(Double  numberOne, Double numberTwo, char symbol)
    {
        switch(symbol) {
            case Operation.ADDITION: return numberOne + numberTwo;
            case Operation.DIVISION:
                if (numberTwo == 0) {
                    return 0;
                }
                return numberOne / numberTwo;
            case Operation.MULTIPLICATION: return numberOne * numberTwo;
            case Operation.SUBTRACTION: return numberOne - numberTwo;
            default:
                return 0;
        }

    }
    private  static boolean isSymbolWithGreaterImportance(char symbol){
        if(symbol == Operation.DIVISION || symbol == Operation.MULTIPLICATION){
            return true;
        }
        return false;

    }
    private static Stack<String> getExpressionWithSymbolsFromMinorImportance(Stack<String> expresion)
    {
        Stack<String> newExpresion = new Stack<String>();
        while(!expresion.isEmpty()){
            if(expresion.size() <= STACK_END){
                newExpresion.push(expresion.pop());
                break;
            }
            String numberOne = expresion.pop();
            char symbol = expresion.pop().charAt(0);
            if(is(symbol) && isSymbolWithGreaterImportance(symbol)){
                String numberTwo = expresion.pop();
                Double dResult = computeOperation(Double.parseDouble(numberOne), Double.parseDouble(numberTwo), symbol);
                expresion.push(dResult.toString());
            }else{
                newExpresion.push(numberOne);
                newExpresion.push(String.valueOf(symbol));
            }
        }
        return  newExpresion;
    }
    public static  String eval(Stack<String> expresion){
        Stack<String> newExpresion = getExpressionWithSymbolsFromMinorImportance(expresion);
        while (!newExpresion.isEmpty()){
            if(newExpresion.size() <= STACK_END) {
                break;
            }
            Double numberOne = Double.parseDouble(newExpresion.pop());
            char symbol = newExpresion.pop().charAt(0);
            Double numberTwo = Double.parseDouble(newExpresion.pop());
            Double result = computeOperation(numberOne, numberTwo, symbol);
            newExpresion.push(result.toString());
        }
        return newExpresion.pop().toString();

    }
}
