package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView resultTv, solutionTv;
    MaterialButton buttonC, buttonBracketOpen, buttonBracketClose;
    MaterialButton buttonDivide, buttonMultiply, buttonMinus, buttonPlus, buttonEquals;
    MaterialButton button0, button1, button2, button3, button4, button5, button6, button7, button8, button9;
    MaterialButton buttonAC, buttonDot;
    String data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultTv = findViewById(R.id.result_tv);
        solutionTv = findViewById(R.id.solution_tv);
        assignId(buttonC, R.id.button_c);
        assignId(buttonBracketClose, R.id.button_close_bracket);
        assignId(buttonBracketOpen, R.id.button_open_bracket);
        assignId(buttonDivide, R.id.button_divide);
        assignId(buttonMultiply, R.id.button_multiply);
        assignId(buttonMinus, R.id.button_minus);
        assignId(buttonPlus, R.id.button_plus);
        assignId(button0, R.id.button_0);
        assignId(button1, R.id.button_1);
        assignId(button2, R.id.button_2);
        assignId(button3, R.id.button_3);
        assignId(button4, R.id.button_4);
        assignId(button5, R.id.button_5);
        assignId(button6, R.id.button_6);
        assignId(button7, R.id.button_7);
        assignId(button8, R.id.button_8);
        assignId(button9, R.id.button_9);
        assignId(buttonAC, R.id.button_ac);
        assignId(buttonDot, R.id.button_dot);


    }

    void assignId(MaterialButton btn, int id) {
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttontext = button.getText().toString();
        String datatoCalculate = solutionTv.getText().toString();
        if (buttontext.equals("AC")) {
            solutionTv.setText("");
            resultTv.setText("0");
            return;
        }

        if (buttontext.equals("=")) {
            solutionTv.setText(resultTv.getText());
            return;
        }
        if (buttontext.equals("C")) {
            datatoCalculate = datatoCalculate.substring(0, datatoCalculate.length() - 1);
        } else {
            datatoCalculate = datatoCalculate + buttontext;
        }
        solutionTv.setText(datatoCalculate);
        String finalResult = getResult(datatoCalculate);
        if(!finalResult.equals("error")){
            resultTv.setText(finalResult);
        }
    }
    String getResult(String data){
    try{
        Context context = Context.enter();
        context.setOptimizationLevel(-1);
        Scriptable scriptable = context.initStandardObjects();
       String finalResult =  context.evaluateString(scriptable,data,"Javascript",1,null).toString();
       if(finalResult.endsWith(".0")){
           finalResult = finalResult.replace(".0","");
       }
        return finalResult;
    }catch(Exception e){
        return "error";
    }
    }


}