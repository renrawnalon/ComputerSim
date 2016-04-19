package com.renrawnalon.computersim.computer;

import android.util.Log;
import java.util.Arrays;

public class Stack {

    private Command[] commands;
    private int stackPointer = 0;

    public Stack(int size) {
        commands = new Command[size];
    }

    public void insert(Command command, int address) {
        commands[address] = command;

        if (stackPointer < address + 1) {
            stackPointer = address + 1;
        }
    }

    // Push value onto commands
    public void push(Command command) {
        commands[stackPointer] = command;
        stackPointer++;
    }

    // Pop value off of commands
    public Command pop() {
        stackPointer--;
        Command command = commands[stackPointer];
        commands[stackPointer] = null;

        return command;
    }

    // Peek value on top of commands
    public Command peek() {
        return commands[stackPointer - 1];
    }

    public Command valueAt(int address) {
        return commands[address];
    }

    // Print commands
    public void print() {
        for (Command command: commands) {
            if (command != null) {
                if (command.value != null) {
                    Log.i("debug", new StringBuilder()
                            .append("Address: ").append(Arrays.asList(commands).indexOf(command))
                            .append(", Command: ").append(command.instruction).append(" ").append(command.value).toString());
                } else {
                    Log.i("debug", new StringBuilder()
                            .append("Address: ").append(Arrays.asList(commands).indexOf(command))
                            .append(", Command: ").append(command.instruction).toString());
                }
            }
        }

        Log.i("debug", new StringBuilder().append("Stack Pointer Start Location: ").append(stackPointer).toString());
    }
}
