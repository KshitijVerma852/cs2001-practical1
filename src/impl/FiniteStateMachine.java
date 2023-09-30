package impl;

import exceptions.BadInputException;
import exceptions.BadTableException;
import exceptions.NDTransitionException;
import interfaces.IFiniteStateMachine;
import interfaces.ITransition;

/**
 * Class representing a finite state machine.
 */
public class FiniteStateMachine implements IFiniteStateMachine {
    ITransition[] fsm = new Transition[10];
    int currIndex = 0;

    @Override
    public void addTransition(ITransition transition) throws NDTransitionException {
        fsm[currIndex] = transition;
        currIndex++;
        // TODO: Not sure about length 5 of the array.
        // TODO: Check the length of array before adding new values.
    }

    @Override
    public String interpret(String input) throws BadTableException, BadInputException {
        // aaa -> 'a' 'a' 'a'
        StringBuilder outputs = new StringBuilder();
        if (input != null && !input.isEmpty()) {
            int t2 = 0;
            char[] inputs = input.toCharArray();
            int currIndex = 0;
            for (char c : inputs) {
                for (int i = currIndex; i < fsm.length + currIndex; i++) {
                    ITransition iTransition = fsm[currIndex];
                    currIndex++;
                    if (currIndex == fsm.length - 1) {
                        currIndex = 0;
                    }
                    if (iTransition != null && iTransition.getInput() == c) {
                        outputs.append(iTransition.getOutput());
                        break;
                    }
                }
            }
        }
        System.out.println("outputs = " + outputs);
        return outputs.toString();
    }
}
