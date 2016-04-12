package com.renrawnalon.computersim.computer;

import com.renrawnalon.computersim.computer.Instruction;

public class Command {

    public final Instruction instruction;
    public final Integer value;
    public final String error;

    public Command(Instruction instruction, String value) {
        this.instruction = instruction;
        this.value = Integer.valueOf(value);
        this.error = null;
    }

    public Command(Instruction instruction, Integer value) {
        this.instruction = instruction;
        this.value = value;
        this.error = null;
    }

    public Command(Instruction instruction) {
        this.instruction = instruction;
        this.value = null;
        this.error = null;
    }

    public Command(String error) {
        this.instruction = null;
        this.value = null;
        this.error = error;
    }
}
