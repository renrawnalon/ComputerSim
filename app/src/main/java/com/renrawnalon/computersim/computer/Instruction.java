package com.renrawnalon.computersim.computer;

public enum Instruction {
    PLUS,  // : Pop the 2 arguments from the stack, add them and push the result back to the stack
    MINUS,  // : Pop the 2 arguments from the stack, subtract them and push the result back to the stack
    MULT,  // : Pop the 2 arguments from the stack, multiply them and push the result back to the stack
    DIV,  // : Pop the 2 arguments from the stack, divide them and push the result back to the stack
    CALL,  // : addr : Set the program counter (PC) to addr
    RET,   // : Pop address from stack and set PC to address
    STOP,  // : Exit the program
    PRINT, // : Pop value from stack and print it
    PUSH;  // : arg : Push argument to the stack
}
