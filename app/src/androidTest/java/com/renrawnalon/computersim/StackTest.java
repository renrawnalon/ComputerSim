package com.renrawnalon.computersim;

import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.test.suitebuilder.annotation.MediumTest;

import com.renrawnalon.computersim.computer.Command;
import com.renrawnalon.computersim.computer.Instruction;
import com.renrawnalon.computersim.computer.Stack;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.lang.reflect.Field;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
@RunWith(AndroidJUnit4.class)
@MediumTest
public class StackTest {

    @Test
    public void initializeStackTest() {
        int stackSize = 100;
        Stack stack = new Stack(stackSize);
        Field field = null;
        try {
            Command[] commands = getCommands(stack);
            String failureString =  new StringBuilder()
                    .append("Expected stack size: ") .append(stackSize).append(". ")
                    .append("Actual stack size: ") .append(commands.length).append(".").toString();
            assertEquals(failureString, commands.length, stackSize);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            fail("NoSuchFieldException");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            fail("IllegalAccessException");
        }
    }

    @Test
    public void pushAndPopTest() {
        Stack stack = new Stack(100);
        Command pushCommand = new Command(Instruction.PUSH, 5);
        stack.push(pushCommand);
        Command popCommand = stack.pop();

        assertEquals(pushCommand.instruction, popCommand.instruction);
        assertEquals(pushCommand.value, popCommand.value);
        assertEquals(pushCommand.error, popCommand.error);

        try {
            int stackPointer = getStackPointer(stack);
            assertEquals(stackPointer, 0);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            fail("NoSuchFieldException");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            fail("IllegalAccessException");
        }
    }

    @Test
    public void pushAndPeek() {
        Stack stack = new Stack(100);
        Command pushCommand = new Command(Instruction.PUSH, 5);
        stack.push(pushCommand);
        Command peekCommand = stack.peek();

        assertEquals(pushCommand.instruction, peekCommand.instruction);
        assertEquals(pushCommand.value, peekCommand.value);
        assertEquals(pushCommand.error, peekCommand.error);

        try {
            int stackPointer = getStackPointer(stack);
            assertEquals(stackPointer, 1);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            fail("NoSuchFieldException");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            fail("IllegalAccessException");
        }
    }

    @Test
    public void insertTest() {
        Stack stack = new Stack(100);
        Command insertCommand = new Command(Instruction.PUSH, 5);
        int address = 50;
        stack.insert(insertCommand, address);

        try{
            Command[] commands = getCommands(stack);
            assertEquals(insertCommand, commands[address]);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            fail("NoSuchFieldException");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            fail("IllegalAccessException");
        }
    }

    @Test
    public void valueAtTest() {
        Stack stack = new Stack(100);
        Command insertCommand = new Command(Instruction.PUSH, 5);
        int address = 50;
        stack.insert(insertCommand, address);

        Command valueAtCommand = stack.valueAt(address);
        assertEquals(insertCommand, valueAtCommand);
    }

    // Use reflection to get private field "commands"
    public static Command[] getCommands(Stack stack) throws NoSuchFieldException, IllegalAccessException {
        Field field;
        field = Stack.class.getDeclaredField("commands");
        field.setAccessible(true);
        return ((Command[]) field.get(stack));
    }

    // Use reflection to get private field "stackPointer"
    public static int getStackPointer(Stack stack) throws NoSuchFieldException, IllegalAccessException {
        Field field;
        field = Stack.class.getDeclaredField("stackPointer");
        field.setAccessible(true);
        return ((int) field.get(stack));
    }
}