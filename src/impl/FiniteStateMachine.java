package impl;

import exceptions.BadInputException;
import exceptions.BadTableException;
import exceptions.NDTransitionException;
import interfaces.IFiniteStateMachine;
import interfaces.ITransition;

import java.util.List;

/**
 * Class representing a finite state machine.
 */
public class FiniteStateMachine implements IFiniteStateMachine {

    TransitionTable transitionTable = new TransitionTable();

    @Override
    public void addTransition(ITransition transition) throws NDTransitionException {
        transitionTable.addTransition(transition);
    }

    // TODO - Add test for BadTableException
    // TODO - Add test for BadInputException
    @Override
    public String interpret(String input) throws BadTableException, BadInputException {
        StringBuilder outputs = new StringBuilder();
        if (input != null && !input.isEmpty()) {
            char[] inputs = input.toCharArray();
            List<ITransition> transitions = transitionTable.getTransitions();
            int currIndex = 0;
            for (char c : inputs) {
                for (int i = currIndex; i < transitions.size() + currIndex; i++) {
                    ITransition iTransition = transitions.get(i);
                    currIndex++;
                    if (currIndex == transitions.size()) {
                        currIndex = 0;
                    }
                    if (iTransition != null && iTransition.getInput() == c) {
                        outputs.append(iTransition.getOutput());
                        break;
                    }
                }
            }
        }
        return outputs.toString();
    }
}
