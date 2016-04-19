package com.renrawnalon.computersim;

import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.SmallTest;

import com.renrawnalon.computersim.computer.Command;
import com.renrawnalon.computersim.computer.Instruction;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by renrawnalon on 16/04/20.
 */
@RunWith(AndroidJUnit4.class)
@SmallTest
public class CommandTest {

    @Test
    public void initWithInstructionAndStringTest() {
        Instruction instruction = Instruction.PLUS;
        String string = "5";
        Command command = new Command(instruction, string);

        assertEquals(instruction, command.instruction);
        assertEquals(Integer.valueOf(string), command.value);
        assertNull(command.error);
    }

    @Test
    public void initWithInstructionAndIntegerTest() {
        Instruction instruction = Instruction.PLUS;
        Integer integer = 5;
        Command command = new Command(instruction, integer);

        assertEquals(instruction, command.instruction);
        assertEquals(integer, command.value);
        assertNull(command.error);
    }

    @Test
    public void initWithInstructionTest() {
        Instruction instruction = Instruction.PLUS;
        Command command = new Command(instruction);

        assertEquals(instruction, command.instruction);
        assertNull(command.value);
        assertNull(command.error);
    }

    @Test
    public void initWithErrorTest() {
        String error = "Error";
        Command command = new Command(error);

        assertNull(command.instruction);
        assertNull(command.value);
        assertEquals(error, command.error);
    }
}
