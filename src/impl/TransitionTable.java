package impl;

import exceptions.BadInputException;
import exceptions.NDTransitionException;
import interfaces.ITransition;
import interfaces.ITransitionTable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Class representing a transition table for an FSM.
 */
public class TransitionTable implements ITransitionTable {

    private final List<ITransition> transitions = new ArrayList<>();

    @Override
    public void addTransition(ITransition transition) throws NDTransitionException {
        // Check if the same StateAndInput already exists
        Optional<ITransition> transitionWithSameStateAndInput = transitions.stream().filter(x -> x.getInput() == transition.getInput() && x.getCurrentState() == transition.getCurrentState()).findFirst();
        if (transitionWithSameStateAndInput.isPresent()) {
            throw new NDTransitionException();
        }
        transitions.add(transition);
    }

    @Override
    public ITransition getTransition(int current_state, char input) throws BadInputException {
        Optional<ITransition> transition = transitions.stream().filter(x -> x.getCurrentState() == current_state && x.getInput() == input).findFirst();
        if (transition.isPresent()) {
            return transition.get();
        } else {
            throw new BadInputException();
        }

    }

    @Override
    public boolean hasTransitionsToIllegalStates() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean hasMissingInputs() {
        // TODO Auto-generated method stub
        return false;
    }


    public List<ITransition> getTransitions() {
        return transitions;
    }

}
