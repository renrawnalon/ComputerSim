package com.renrawnalon.computersim.computer;

import android.util.Log;

import com.renrawnalon.computersim.computer.Instruction;

/**
 * Created by renrawnalon on 16/04/04.
 */
public class Computer {

    //region Constants
    private static final int MIN_STACK_SIZE = 100;
    private static final int MAX_STACK_SIZE = 10000;
    //endregion

    //region Variables
    private Stack stack;
    private int programCounter;

    private PrintHandler printHandler;
    //endregion

    //region Initialization
    // Initialize computer with a specified stack size.
    public Computer(Integer stackSize, PrintHandler printHandler) throws InvalidArgumentsException {
        if (stackSize < MIN_STACK_SIZE) {
            throw new InvalidArgumentsException(new StringBuilder().append("Invalid stack size. Stack must be larger than ").append(MIN_STACK_SIZE).append(".").toString());
        }

        if (stackSize > MAX_STACK_SIZE) {
            throw new InvalidArgumentsException(new StringBuilder().append("Invalid stack size. Stack must be smaller than ").append(MAX_STACK_SIZE).append(".").toString());
        }

        stack = new Stack(stackSize);
        this.printHandler = printHandler;
    }
    //endregion

    //region Computer Interface
    // Set stack address
    public Computer setAddress(Integer address) throws InvalidArgumentsException {
        if (address < 0 || address > MAX_STACK_SIZE) {
            throw new InvalidArgumentsException(new StringBuilder().append("Address is out of bounds. Use address between ").append(MIN_STACK_SIZE).append(" and ").append(MAX_STACK_SIZE).append(".").toString());
        }

        this.programCounter = address;

        return this;
    }

    // Do insertion
    public Computer insert(Command command) throws InvalidArgumentsException {
        if (programCounter < 0 || programCounter > MAX_STACK_SIZE) {
            throw new InvalidArgumentsException("Current address '" + programCounter + "' is out of bounds.");
        }

        stack.insert(command, programCounter);
        programCounter++;

        return this;
    }

    // Do execution
    public void execute() {
        stack.print();
        while (stack.valueAt(programCounter).instruction != Instruction.STOP) {
            Command command = stack.valueAt(programCounter);

            switch (command.instruction) {
                case MULT:
                    executeMult();
                    break;
                case CALL:
                    executeCall(command.value);
                    break;
                case RET:
                    executeRet();
                    break;
                case STOP:
                    executeStop();
                    break;
                case PRINT:
                    executePrint();
                    break;
                case PUSH:
                    executePush(command.value);
                    break;
            }
        }
    }
    //endregion

    //region Instruction execution
    // Pop the 2 arguments from the stack, multiply them and push the result back to the stack
    private void executeMult() {
        Integer value1 = stack.pop().value;
        Integer value2 = stack.pop().value;
        stack.push(new Command(null, value1 * value2));

        programCounter++;
    }

    // Set the program counter (PC) to addr
    private void executeCall(Integer address) {
        programCounter = address;
    }

    // Pop address from stack and set PC to address
    private void executeRet() {
        Command command = stack.pop();
        programCounter = command.value;
    }

    // Exit the program
    private void executeStop() {
        Log.i("debug", "Done");
    }

    // Pop value from stack and print it
    private void executePrint() {
        Command command = stack.pop();
        printHandler.print(command.value.toString());

        programCounter++;
    }

    // Push argument to the stack
    private void executePush(Integer argument) {
        stack.push(new Command(null, argument));

        programCounter++;
    }
    //endregion
}

