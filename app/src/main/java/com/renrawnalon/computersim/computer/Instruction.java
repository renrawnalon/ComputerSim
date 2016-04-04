package com.renrawnalon.computersim.computer;

public enum Instruction {
    MULT,  // : Pop the 2 arguments from the stack, multiply them and push the result back to the stack
    CALL,  // : addr : Set the program counter (PC) to addr
    RET,   // : Pop address from stack and set PC to address
    STOP,  // : Exit the program
    PRINT, // : Pop value from stack and print it
    PUSH;  // : arg : Push argument to the stack
}
