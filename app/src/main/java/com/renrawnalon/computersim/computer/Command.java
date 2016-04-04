package com.renrawnalon.computersim.computer;

public class Command<Instruction, Integer> {
    public enum Instruction {
        MULT,  // : Pop the 2 arguments from the stack, multiply them and push the result back to the stack
        CALL,  // : addr : Set the program counter (PC) to addr
        RET,   // : Pop address from stack and set PC to address
        STOP,  // : Exit the program
        PRINT, // : Pop value from stack and print it
        PUSH;  // : arg : Push argument to the stack
    }

    public final Instruction instruction;
    public final Integer value;

    public Command(Instruction instruction, Integer value) throws InvalidArgumentsException {
        if (instruction != Command.Instruction.CALL && instruction != Command.Instruction.PUSH && value != null) {
            throw new InvalidArgumentsException(new StringBuilder().append("Instruction").append(instruction).append("cannot be called with argument").append(value).append(".").toString());
        }

        this.instruction = instruction;
        this.value = value;
    }

    public Command(Instruction instruction) throws InvalidArgumentsException {
        if (instruction == Command.Instruction.CALL || instruction == Command.Instruction.PUSH) {
            throw new InvalidArgumentsException(new StringBuilder().append("Instruction").append(instruction).append("cannot be called with no arguments. Use `Command(Instruction instruction, Integer value)` instead.").toString());
        }

        this.instruction = instruction;
        this.value = null;
    }
}
