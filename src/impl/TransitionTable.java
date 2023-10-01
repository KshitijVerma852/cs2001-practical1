package impl;

import exceptions.BadInputException;
import exceptions.NDTransitionException;
import interfaces.ITransition;
import interfaces.ITransitionTable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    /**
     * @return true if the transition table contains transitions to illegal (non-existent) state (i.e. next_states that are not in the table as a current_state) and false otherwise
     */
    @Override
    public boolean hasTransitionsToIllegalStates() {
        for (int i = 0; i < transitions.size(); i++) {
            int target = transitions.get(i).getNextState();
            Optional<Integer> isPresentInCurrState = transitions.stream().map(x -> x.getCurrentState()).filter(x -> x == target).findAny();
            if (!isPresentInCurrState.isPresent()) {
                return true;
            }

        }
        return false;
    }

    @Override
    public boolean hasMissingInputs() {
        // TODO - check what's the ask?
        List<ITransition> missingInputs = transitions.stream().filter(x -> x.getInput() == ' ').toList();
        return missingInputs.isEmpty();
    }

    public List<ITransition> getTransitions() {
        return transitions;
    }

}
