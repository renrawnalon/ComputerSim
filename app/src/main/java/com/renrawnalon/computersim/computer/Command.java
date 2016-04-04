package com.renrawnalon.computersim.computer;

import com.renrawnalon.computersim.computer.Instruction;

public class Command {

    public final Instruction instruction;
    public final Integer value;

    public Command(Instruction instruction, Integer value) {
        this.instruction = instruction;
        this.value = value;
    }

    public Command(Instruction instruction) {
        this.instruction = instruction;
        this.value = null;
    }
}
