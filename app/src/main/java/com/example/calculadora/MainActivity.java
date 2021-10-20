package com.example.calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import java.util.Stack;

public class MainActivity extends AppCompatActivity {
    private TextView textViewResult;
    private TextView textViewStackOperations;
    private Stack<String> operations;
    private String number;
    private static final String EMPTY = "";
    private static byte MIN_EXPRESSIONS = 3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewResult = (TextView) findViewById(R.id.text_view_result);
        textViewStackOperations = (TextView) findViewById(R.id.text_view_stack_operation);
        operations = new Stack<String>();
        number = EMPTY;

    }
    private char getSymbolFromTopStack()
    {
        return operations.peek().charAt(0);
    }

    private void showStackOperations()
    {
        if(operations.isEmpty()) {
            return;

        }
        StringBuilder content = new StringBuilder(operations.size());
        for (int i =0; i< operations.size(); i++) {
            content.append(operations.get(i));
        }
        textViewStackOperations.setText(content.toString());

    }
    private double getResult()
    {
        return 0;
    }

    private void pushOperation(char symbol)
    {
        operations.push(number);
        number = EMPTY;
        operations.push(String.valueOf(symbol));
        showStackOperations();

    }

    public void onClickClear(View view)
    {
        number = EMPTY;
        operations.clear();
        textViewResult.setText(EMPTY);
        textViewStackOperations.setText(EMPTY);

    }
    private void updateOperationText(String str)
    {
        number += str;
        textViewStackOperations.setText(textViewStackOperations.getText()+str);
    }
    public void onClickAddDecimalPoint(View view)
    {
        if(number.contains(".") || number == EMPTY){
            return;
        }
        updateOperationText(".");
    }
    public void onClickBtn(View view)
    {
        char symbol = ((Button)view).getText().charAt(0);
        if(Operation.is(symbol)){
            pushOperation(symbol);
            return;
        }
        updateOperationText(String.valueOf(symbol));

    }
    public void onClickShowResult(View view)
    {
        String btnText = ((Button)view).getText().toString();
        if(Operation.is(getSymbolFromTopStack()) && number != EMPTY)
        {
            operations.push(number);
            String result = Operation.eval(operations);
            onClickClear(view);
            textViewResult.setText("="+result);
        }



    }
}