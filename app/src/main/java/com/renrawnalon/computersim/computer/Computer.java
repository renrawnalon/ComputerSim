package com.renrawnalon.computersim.computer;

/**
 * Created by renrawnalon on 16/04/04.
 */
public class Computer {

    // Costants
    private static final int PRINT_TENTEN_BEGIN = 50;
    private static final int MAIN_BEGIN = 0;
    private static final int MIN_STACK_SIZE = 100;
    private static final int MAX_STACK_SIZE = 10000;

    // Variables
    private Command[] stack;
    private int currentAddress;

    // Initialize computer with a specified stack size.
    public Computer(Integer stackSize) throws InvalidArgumentsException {
        if (stackSize < MIN_STACK_SIZE) {
            throw new InvalidArgumentsException(new StringBuilder().append("Invalid stack size. Stack must be larger than ").append(MIN_STACK_SIZE).append(".").toString());
        }

        if (stackSize > MAX_STACK_SIZE) {
            throw new InvalidArgumentsException(new StringBuilder().append("Invalid stack size. Stack must be smaller than ").append(MAX_STACK_SIZE).append(".").toString());
        }

        stack = new Command[stackSize];
    }

    // Set stack address
    public Computer setAddress(Integer address) throws InvalidArgumentsException {
        if (address < MIN_STACK_SIZE || address > MAX_STACK_SIZE) {
            throw new InvalidArgumentsException(new StringBuilder().append("Address is out of bounds. Use address between ").append(MIN_STACK_SIZE).append(" and ").append(MAX_STACK_SIZE).append(".").toString());
        }

        return this;
    }

    // Do insertion
    public Computer insert(Command command) throws InvalidArgumentsException {
        if (currentAddress < MIN_STACK_SIZE || currentAddress > MAX_STACK_SIZE) {
            throw new InvalidArgumentsException("Current address is out of bounds.");
        }

        return this;
    }

    // Do execution
    public void execute() {
    }

    // Pop the 2 arguments from the stack, multiply them and push the result back to the stack
    private void mult() {

    }

    // Set the program counter (PC) to addr
    private void call() {

    }

    // Pop address from stack and set PC to address
    private void ret() {

    }

    // Exit the program
    private void stop() {

    }

    // Pop value from stack and print it
    private void print() {

    }

    // Push argument to the stack
    private void push() {

    }
}

