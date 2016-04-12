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
    private static final int PLUS_BEGIN = 50;
    private static final int MINUS_BEGIN = 53;
    private static final int MULTIPLY_BEGIN = 56;
    private static final int DIVIDE_BEGIN = 59;
    private static final int MAIN_BEGIN = 0;
    //endregion

    //region Variables
    private Computer computer;
    private String lhs = "";
    private String rhs = "";
    private String result = "";
    private String operator = "";

    //endregion

    //region Lifecycle
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        try {
            initializeComputer();
        } catch (InvalidArgumentsException e) {
            print("Could not initialize computer: " + e.getLocalizedMessage());
        }
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
        result = value;
        TextView textView = (TextView) findViewById(R.id.outputTextView);
        String outputPrefix = getString(R.string.prefixOutput);
        String output = new StringBuilder()
                .append(outputPrefix)
                .append(" ")
                .append(result).toString();
        textView.setText(output);
    }
    //endregion

    //region Action Methods
    public void onClickNumberButton(View view) {
        String input = ((Button)view).getText().toString();
        if (!result.isEmpty()) {
            lhs = "";
            rhs = "";
            operator = "";

            print("");
        }

        if (operator.isEmpty()) {
            try  {
                String newLhs = lhs + input;
                Integer.valueOf(newLhs);
                lhs = newLhs;
            } catch (NumberFormatException e) {
            }
        } else {
            try  {
                String newRhs = rhs + input;
                Integer.valueOf(newRhs);
                rhs = newRhs;
            } catch (NumberFormatException e) {
            }
        }

        printInput();
    }

    public void onClickOperatorButton(View view) {
        String input = ((Button)view).getText().toString();
        if (!result.isEmpty()) {
            try  {
                Integer.valueOf(result);
                lhs = result;
            } catch (NumberFormatException e){
                lhs = "";
            }
            rhs = "";
            operator = "";

            print("");
        }

        if (!lhs.isEmpty()) {
            operator = input;
        }

        printInput();
    }

    // Delete input from right to left>
    public void onClickDeleteButton(View view) {
        if (!rhs.isEmpty()) {
            rhs = rhs.substring(0, rhs.length() - 1);
        } else if (!operator.isEmpty()) {
            operator = "";
        } else if (!lhs.isEmpty()) {
            lhs = lhs.substring(0, lhs.length() - 1);
        }

        printInput();
    }

    // Execute calculation with given input.
    public void onClickSolveButton(View view) {
        if (lhs == null || (rhs != null && operator == null)) {
            return;
        }

        try {
            runProgram();
        } catch (InvalidArgumentsException e) {
            print("Bad input: " + e.getLocalizedMessage());
        }
    }
    //endregion

    //region Private Methods

    private void printInput() {
        StringBuilder sb = new StringBuilder()
                .append(getString(R.string.prefixInput))
                .append(" ")
                .append(lhs);
        if (operator != null) {
            sb.append(" ").append(operator);
        }
        if (rhs != null) {
            sb.append(" ").append(rhs);
        }

        TextView inputTextView = (TextView) findViewById(R.id.inputTextView);
        inputTextView.setText(sb.toString());
    }

    private void initializeComputer() throws InvalidArgumentsException {
        // Create new computer with a stack of 1000 addresses
        computer = new Computer(1000, this);

        // Instructions for the add function
        computer.setAddress(PLUS_BEGIN)
                .insert(new Command(Instruction.PLUS))
                .insert(new Command(Instruction.PRINT))
                .insert(new Command(Instruction.RET));

        // Instructions for the subtract function
        computer.setAddress(MINUS_BEGIN)
                .insert(new Command(Instruction.MINUS))
                .insert(new Command(Instruction.PRINT))
                .insert(new Command(Instruction.RET));

        // Instructions for the multiply function
        computer.setAddress(MULTIPLY_BEGIN)
                .insert(new Command(Instruction.MULT))
                .insert(new Command(Instruction.PRINT))
                .insert(new Command(Instruction.RET));

        // Instructions for the divide function
        computer.setAddress(DIVIDE_BEGIN)
                .insert(new Command(Instruction.DIV))
                .insert(new Command(Instruction.PRINT))
                .insert(new Command(Instruction.RET));
    }

    private void printSingleOutput() throws InvalidArgumentsException {
        // Set initial address of program
        computer.setAddress(MAIN_BEGIN);

        // Print input value
        computer.insert(new Command(Instruction.PUSH, lhs))
                .insert(new Command(Instruction.PRINT));

        // Stop the program
        computer.insert(new Command(Instruction.STOP));

        // Execute
        computer.setAddress(MAIN_BEGIN).execute();
    }

    private void printOperationOutput() throws InvalidArgumentsException {
        // Set initial address of program
        computer.setAddress(MAIN_BEGIN);

        // Print input value
        // Setup arguments and call print_tenten
        computer.insert(new Command(Instruction.PUSH, 4))
                .insert(new Command(Instruction.PUSH, lhs))
                .insert(new Command(Instruction.PUSH, rhs));

        switch (operator) {
            case "+":
                computer.insert(new Command(Instruction.CALL, PLUS_BEGIN));
                break;
            case "-":
                computer.insert(new Command(Instruction.CALL, MINUS_BEGIN));
                break;
            case "*":
                computer.insert(new Command(Instruction.CALL, MULTIPLY_BEGIN));
                break;
            case "/":
                computer.insert(new Command(Instruction.CALL, DIVIDE_BEGIN));
                break;
        }

        // Stop the program
        computer.insert(new Command(Instruction.STOP));

        // Execute
        computer.setAddress(MAIN_BEGIN).execute();
    }

    private void printTenTen() throws InvalidArgumentsException {
        // The start of the main function
        computer.setAddress(MAIN_BEGIN);

        // Setup arguments and call print_tenten
        computer.insert(new Command(Instruction.PUSH, 4))
                .insert(new Command(Instruction.PUSH, 101))
                .insert(new Command(Instruction.PUSH, 10))
                .insert(new Command(Instruction.CALL, MULTIPLY_BEGIN));

        // Stop the program
        computer.insert(new Command(Instruction.STOP));

        // Execute
        computer.setAddress(MAIN_BEGIN).execute();
    }

    private void runProgram() throws InvalidArgumentsException {
        if (!lhs.isEmpty() && ! rhs.isEmpty()) {
            printOperationOutput();
        } else if (!lhs.isEmpty() && operator.isEmpty()) {
            printSingleOutput();
        } else {
            printTenTen();
        }
    }
    //endregion
}
