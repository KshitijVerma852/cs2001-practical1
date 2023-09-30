package impl;

import interfaces.IFactory;
import interfaces.IFiniteStateMachine;
import interfaces.ITransition;
import interfaces.ITransitionTable;


/**
 * This class implements a singleton factory.
 */
public final class Factory implements IFactory {

    private static IFactory factoryInstance = null;

    private Factory() {

    }

    /**
     * Method which returns an instance of the singleton Factory class.
     *
     * @return the instance of the Factory
     */
    public static IFactory getInstance() {
        if (factoryInstance == null) {
            factoryInstance = new Factory();
        }
        return factoryInstance;
    }

    @Override
    public ITransition makeTransition(int current_state, char input, char output, int next_state) {
        return new Transition(current_state, input, output, next_state);
    }

    @Override
    public ITransitionTable makeTransitionTable() {
        ITransitionTable transitionTable = new TransitionTable();
        return transitionTable;
    }

    @Override
    public IFiniteStateMachine makeFiniteStateMachine() {
        IFiniteStateMachine fsm = new FiniteStateMachine();
        return fsm;
    }

}
