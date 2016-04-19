package com.renrawnalon.computersim;

import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.util.Log;

import com.renrawnalon.computersim.computer.Command;
import com.renrawnalon.computersim.computer.Computer;
import com.renrawnalon.computersim.computer.Instruction;
import com.renrawnalon.computersim.computer.InvalidArgumentsException;
import com.renrawnalon.computersim.computer.PrintHandler;
import com.renrawnalon.computersim.computer.Stack;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Created by renrawnalon on 16/04/20.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class ComputerTest implements PrintHandler {

    String mPrintedValue = "";

    @Test
    public void initializeComputerTest() {
        int stackSize = 100;

        try {
            Computer computer = new Computer(stackSize, this);
            Stack stack = getStack(computer);
            Command[] commands = StackTest.getCommands(stack);

            assertEquals(stackSize, commands.length);
        } catch (InvalidArgumentsException e) {
            e.printStackTrace();
            fail("InvalidArgumentsException");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            fail("IllegalAccessException");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            fail("NoSuchFieldException");
        }
    }

    @Test
    public void setAddressTest() {
        int address = 5;

        try {
            Computer computer = new Computer(100, this);
            computer.setAddress(address);

            int programCounter = getProgramCounter(computer);

            assertEquals(address, programCounter);
        } catch (InvalidArgumentsException e) {
            e.printStackTrace();
            fail("InvalidArgumentsException");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            fail("IllegalAccessException");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            fail("NoSuchFieldException");
        }
    }

    @Test
    public void insertTest() {
        int address = 5;
        Command insertCommand = new Command(Instruction.PUSH, 5);

        try {
            Computer computer = new Computer(100, this);
            computer.setAddress(address);
            computer.insert(insertCommand);

            Stack stack = getStack(computer);
            Command[] commands = StackTest.getCommands(stack);
            Command testCommand = commands[address];

            assertEquals(insertCommand, testCommand);
        } catch (InvalidArgumentsException e) {
            e.printStackTrace();
            fail("InvalidArgumentsException");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            fail("IllegalAccessException");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            fail("NoSuchFieldException");
        }
    }

    @Test
    public void executePlusTest() {
        try {
            Computer computer = new Computer(100, this);
            computer.setAddress(0);
            computer.insert(new Command(null, 1));
            computer.insert(new Command(null, 2));

            Method method = Computer.class.getDeclaredMethod("executePlus");
            method.setAccessible(true);
            method.invoke(computer);

            Stack stack = getStack(computer);
            Command testCommand = stack.peek();

            assertEquals(Integer.valueOf(3), testCommand.value);
        } catch (InvalidArgumentsException e) {
            e.printStackTrace();
            fail("InvalidArgumentsException");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            fail("IllegalAccessException");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            fail("NoSuchFieldException");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            fail("NoSuchMethodException");
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            fail("InvocationTargetException");
        }
    }

    @Test
    public void executeMinusTest() {
        try {
            Computer computer = new Computer(100, this);
            computer.setAddress(0);
            computer.insert(new Command(null, 5));
            computer.insert(new Command(null, 2));

            Method method = Computer.class.getDeclaredMethod("executeMinus");
            method.setAccessible(true);
            method.invoke(computer);

            Stack stack = getStack(computer);
            Command testCommand = stack.peek();

            assertEquals(Integer.valueOf(3), testCommand.value);
        } catch (InvalidArgumentsException e) {
            e.printStackTrace();
            fail("InvalidArgumentsException");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            fail("IllegalAccessException");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            fail("NoSuchFieldException");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            fail("NoSuchMethodException");
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            fail("InvocationTargetException");
        }
    }

    @Test
    public void executeMultTest() {
        try {
            Computer computer = new Computer(100, this);
            computer.setAddress(0);
            computer.insert(new Command(null, 5));
            computer.insert(new Command(null, 2));

            Method method = Computer.class.getDeclaredMethod("executeMult");
            method.setAccessible(true);
            method.invoke(computer);

            Stack stack = getStack(computer);
            Command testCommand = stack.peek();

            assertEquals(Integer.valueOf(10), testCommand.value);
        } catch (InvalidArgumentsException e) {
            e.printStackTrace();
            fail("InvalidArgumentsException");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            fail("IllegalAccessException");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            fail("NoSuchFieldException");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            fail("NoSuchMethodException");
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            fail("InvocationTargetException");
        }
    }

    @Test
    public void executeMultOverflowTest() {
        try {
            Computer computer = new Computer(100, this);
            computer.setAddress(0);
            computer.insert(new Command(null, 999999));
            computer.insert(new Command(null, 999999));

            Method method = Computer.class.getDeclaredMethod("executeMult");
            method.setAccessible(true);
            method.invoke(computer);

            Stack stack = getStack(computer);
            Command testCommand = stack.peek();

            assertEquals("OVERFLOW ERR", testCommand.error);
        } catch (InvalidArgumentsException e) {
            e.printStackTrace();
            fail("InvalidArgumentsException");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            fail("IllegalAccessException");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            fail("NoSuchFieldException");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            fail("NoSuchMethodException");
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            fail("InvocationTargetException");
        }
    }

    @Test
    public void executeDivTest() {
        try {
            Computer computer = new Computer(100, this);
            computer.setAddress(0);
            computer.insert(new Command(null, 10));
            computer.insert(new Command(null, 2));

            Method method = Computer.class.getDeclaredMethod("executeDiv");
            method.setAccessible(true);
            method.invoke(computer);

            Stack stack = getStack(computer);
            Command testCommand = stack.peek();

            assertEquals(Integer.valueOf(5), testCommand.value);
        } catch (InvalidArgumentsException e) {
            e.printStackTrace();
            fail("InvalidArgumentsException");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            fail("IllegalAccessException");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            fail("NoSuchFieldException");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            fail("NoSuchMethodException");
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            fail("InvocationTargetException");
        }
    }

    @Test
    public void executeDivBy0Test() {
        try {
            Computer computer = new Computer(100, this);
            computer.setAddress(0);
            computer.insert(new Command(null, 10));
            computer.insert(new Command(null, 0));

            Method method = Computer.class.getDeclaredMethod("executeDiv");
            method.setAccessible(true);
            method.invoke(computer);

            Stack stack = getStack(computer);
            Command testCommand = stack.peek();

            assertEquals("DIV BY 0 ERR", testCommand.error);
        } catch (InvalidArgumentsException e) {
            e.printStackTrace();
            fail("InvalidArgumentsException");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            fail("IllegalAccessException");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            fail("NoSuchFieldException");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            fail("NoSuchMethodException");
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            fail("InvocationTargetException");
        }
    }

    @Test
    public void executeCallTest() {
        int address = 10;

        try {
            Computer computer = new Computer(100, this);

            Method method = Computer.class.getDeclaredMethod("executeCall", Integer.class);
            method.setAccessible(true);
            method.invoke(computer, address);

            int programCounter = getProgramCounter(computer);

            assertEquals(address, programCounter);
        } catch (InvalidArgumentsException e) {
            e.printStackTrace();
            fail("InvalidArgumentsException");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            fail("IllegalAccessException");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            fail("NoSuchFieldException");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            fail("NoSuchMethodException");
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            fail("InvocationTargetException");
        }
    }

    @Test
    public void executeRetTest() {
        int address = 10;

        try {
            Computer computer = new Computer(100, this);
            computer.insert(new Command(null, address));

            Method method = Computer.class.getDeclaredMethod("executeRet");
            method.setAccessible(true);
            method.invoke(computer);

            int programCounter = getProgramCounter(computer);

            assertEquals(address, programCounter);
        } catch (InvalidArgumentsException e) {
            e.printStackTrace();
            fail("InvalidArgumentsException");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            fail("IllegalAccessException");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            fail("NoSuchFieldException");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            fail("NoSuchMethodException");
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            fail("InvocationTargetException");
        }
    }

    @Test
    public void executePrintTest() {
        String testValue = "10";
        try {
            Computer computer = new Computer(100, this);
            computer.setAddress(0);
            computer.insert(new Command(null, testValue));

            Method method = Computer.class.getDeclaredMethod("executePrint");
            method.setAccessible(true);
            method.invoke(computer);

            assertEquals(testValue, mPrintedValue);
        } catch (InvalidArgumentsException e) {
            e.printStackTrace();
            fail("InvalidArgumentsException");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            fail("IllegalAccessException");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            fail("NoSuchMethodException");
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            fail("InvocationTargetException");
        }
    }

    @Test
    public void executePrintErrorTest() {
        String testValue = "Error message";
        try {
            Computer computer = new Computer(100, this);
            computer.setAddress(0);
            computer.insert(new Command(testValue));

            Method method = Computer.class.getDeclaredMethod("executePrint");
            method.setAccessible(true);
            method.invoke(computer);

            assertEquals(testValue, mPrintedValue);
        } catch (InvalidArgumentsException e) {
            e.printStackTrace();
            fail("InvalidArgumentsException");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            fail("IllegalAccessException");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            fail("NoSuchMethodException");
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            fail("InvocationTargetException");
        }
    }

    @Test
    public void executePushTest() {
        Integer pushValue = 10;
        try {
            Computer computer = new Computer(100, this);
            computer.setAddress(0);

            Method method = Computer.class.getDeclaredMethod("executePush", Integer.class);
            method.setAccessible(true);
            method.invoke(computer, pushValue);

            int programCounter = getProgramCounter(computer);

            assertEquals(1, programCounter);

            Stack stack = getStack(computer);
            Command command = stack.peek();

            assertEquals(pushValue, command.value);
        } catch (InvalidArgumentsException e) {
            e.printStackTrace();
            fail("InvalidArgumentsException");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            fail("IllegalAccessException");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            fail("NoSuchFieldException");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            fail("NoSuchMethodException");
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            fail("InvocationTargetException");
        }
    }

    // Use reflection to get private field "stack"
    public static Stack getStack(Computer computer) throws NoSuchFieldException, IllegalAccessException {
        Field field = Computer.class.getDeclaredField("stack");
        field.setAccessible(true);
        return ((Stack) field.get(computer));
    }

    // Use reflection to get private field "programCounter"
    public static int getProgramCounter(Computer computer) throws NoSuchFieldException, IllegalAccessException {
        Field field = Computer.class.getDeclaredField("programCounter");
        field.setAccessible(true);
        return ((int) field.get(computer));
    }

    @Override
    public void print(String value) {
        mPrintedValue = value;
    }
}
