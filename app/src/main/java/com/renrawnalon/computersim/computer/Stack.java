package com.renrawnalon.computersim.computer;

import android.util.Log;
import java.util.Arrays;

public class Stack {

    private Command[] stack;
    private int stackPointer = 0;

    public Stack(int size) {
        stack = new Command[size];
    }

    public void insert(Command command, int address) {
        stack[address] = command;

        if (stackPointer < address + 1) {
            stackPointer = address + 1;
        }
    }

    // Push value onto stack
    public void push(Command command) {
        stack[stackPointer] = command;
        stackPointer++;
    }

    // Pop value off of stack
    public Command pop() {
        stackPointer--;
        Command command = stack[stackPointer];
        stack[stackPointer] = null;

        return command;
    }

    // Peek value on top of stack
    public Command peek() {
        return stack[stackPointer - 1];
    }

    public Command valueAt(int address) {
        return stack[address];
    }

    // Print stack
    public void print() {
        for (Command command: stack) {
            if (command != null) {
                if (command.value != null) {
                    Log.i("debug", new StringBuilder()
                            .append("Address: ").append(Arrays.asList(stack).indexOf(command))
                            .append(", Command: ").append(command.instruction).append(" ").append(command.value).toString());
                } else {
                    Log.i("debug", new StringBuilder()
                            .append("Address: ").append(Arrays.asList(stack).indexOf(command))
                            .append(", Command: ").append(command.instruction).toString());
                }
            }
        }

        Log.i("debug", new StringBuilder().append("Stack Pointer Start Location: ").append(stackPointer).toString());
    }
}
