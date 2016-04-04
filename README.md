# ComputerSim
A simple android project that simulates a computer running a program.

## Assumptions
Since the only direct interaction with this app is the button event that executes the program, and not the input to the program itself, it is assumed that the program will always execute with valid input. Therefore, error handling regarding the validity of the program input is generally ignored. 

I did add checks to make sure that `Computer` is properly initialized with at least 100 but no more than 10,000 address slots. This can be verified by changing the line of code that instanciates `Computer` in `MainActivity` to take a number outside of this range. In this case, an error message will be printed to the screen instead of the expected output.

## Improvements.

### Error Handling
As mentioned above, error handling is the first major improvement that coud be done to increase safety and usabilitiy of this app. However, even though the possible instructions of the simulated computer are very limited, implementing this error handling would be no small task. If done properly, it would involve generating compiler to assure validity around all edge cases for possible inputs.

### Unit Tests
This would be a way to ensure that the error handling mentioned above works in all cases, and would be necessary to ensure that future development of this app doesn't introduce new bugs or break old code.

### UI Improvements
Ideally, this app would contain a user interface that allows for the user to write "code" that `Computer` would execute. This could be as simple as giving the use the option of creating functions and doing some combination of multiplying numbers and printing things to the screen. This UI could be restrictive to ensure to a maximum degree that the program entered by the user is always valid, or it could be more freeform, which would require more rigorous checks and error handling.
