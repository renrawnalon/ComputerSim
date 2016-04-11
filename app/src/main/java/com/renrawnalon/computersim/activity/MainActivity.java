package com.renrawnalon.computersim.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.renrawnalon.computersim.R;
import com.renrawnalon.computersim.computer.Command;
import com.renrawnalon.computersim.computer.Computer;
import com.renrawnalon.computersim.computer.Instruction;
import com.renrawnalon.computersim.computer.InvalidArgumentsException;
import com.renrawnalon.computersim.computer.PrintHandler;

public class MainActivity extends AppCompatActivity implements PrintHandler {

    //region Constants
    private static final int PRINT_TENTEN_BEGIN = 50;
    private static final int MAIN_BEGIN = 0;
    //endregion

    //region Variables
    private Computer computer;
    //endregion

    //region Lifecycle
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    //endregion

    //region Print Callback
    @Override
    // Print value to screen by appending it to the TextView
    public void print(String value) {
//        TextView textView = (TextView) findViewById(R.id.textView);
//        String text = (String) textView.getText();
//        text += value;
//        text += "\n";
//        textView.setText(text);
    }
    //endregion

    //region Action Methods
    public void onClickNumberButton(View view) {
        TextView inputTextView = (TextView) findViewById(R.id.inputTextView);
        String text = (String) inputTextView.getText();
        text += ((Button)view).getText();
        inputTextView.setText(text);
    }

    public void onClickOperatorButton(View view) {
        TextView inputTextView = (TextView) findViewById(R.id.inputTextView);
        String inputPrefix = getString(R.string.prefixInput);
        String text = (String) inputTextView.getText();

        // Operator cannot be the first input.
        if (text.length() <= inputPrefix.length()) {
            return;
        }

        // If last input was an operator, remove it and replace it with the new operator.
        char lastChar = text.charAt(text.length() - 1);
        if (!Character.toString(lastChar).matches("[0-9]")) {
            text = text.substring(0, text.length() - 3);
        }

        text += " " + ((Button) view).getText() + " ";
        inputTextView.setText(text);
    }

    public void onClickDeleteButton(View view) {
        TextView inputTextView = (TextView) findViewById(R.id.inputTextView);
        String inputPrefix = getString(R.string.prefixInput);
        String text = (String) inputTextView.getText();

        // Operator cannot be the first input.
        if (text.length() <= inputPrefix.length()) {
            return;
        }

        // If last input was an operator, remove it and replace it with the new operator.
        char lastChar = text.charAt(text.length() - 1);
        if (Character.toString(lastChar).matches("[0-9]")) {
            text = text.substring(0, text.length() - 1);
        } else {
            text = text.substring(0, text.length() - 3);
        }

        inputTextView.setText(text);
    }

    public void onClickSolveButton(View view) {
        try {
            runProgram();
        } catch (InvalidArgumentsException e) {
            print("Bad input: " + e.getLocalizedMessage());
        }
    }
    //endregion

    //region Private Methods
    private void runProgram() throws InvalidArgumentsException {
        // Create new computer with a stack of 100 addresses
        computer = new Computer(100, this);

        // Instructions for the print_tenten function
        computer.setAddress(PRINT_TENTEN_BEGIN)
                .insert(new Command(Instruction.MULT))
                .insert(new Command(Instruction.PRINT))
                .insert(new Command(Instruction.RET));

        // The start of the main function
        computer.setAddress(MAIN_BEGIN)
                .insert(new Command(Instruction.PUSH, 1009))
                .insert(new Command(Instruction.PRINT));

        // Return address for when print_tenten function finishes
        computer.insert(new Command(Instruction.PUSH, 6));

        // Setup arguments and call print_tenten
        computer.insert(new Command(Instruction.PUSH, 101))
                .insert(new Command(Instruction.PUSH, 10))
                .insert(new Command(Instruction.CALL, PRINT_TENTEN_BEGIN));

        // Stop the program
        computer.insert(new Command(Instruction.STOP));

        // Execute
        computer.setAddress(MAIN_BEGIN).execute();
    }
    //endregion
}
