package test;

import exceptions.BadInputException;
import exceptions.NDTransitionException;
import impl.Factory;
import impl.Transition;
import interfaces.IFactory;
import interfaces.ITransition;
import interfaces.ITransitionTable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * This is a JUnit test class for the FSM ADT.
 */
public class TestTransitionTable {

    private IFactory factory;
    private ITransitionTable transitionTable;


    /**
     * JUnit setup method to run before every other test.
     */
    @BeforeEach
    public void setup() {
        factory = Factory.getInstance();
        transitionTable = factory.makeTransitionTable();
    }


    /**
     * This checks that the factory was able to call a sensible constructor to get a non-null instance of ITransitionTable.
     */
    @Test
    public void transitionTableCreationNonNull() {
        assertNotNull(transitionTable);
    }

    @Test
    void addsTransitionSuccessfully() throws NDTransitionException, BadInputException {
        ITransition tt = new Transition(1, 'z', 'a', 2);
        transitionTable.addTransition(tt);

        ITransition t = transitionTable.getTransition(1, 'z');

        Assertions.assertTrue(tt.getCurrentState() == t.getCurrentState()
                && tt.getInput() == t.getInput()
                && tt.getOutput() == t.getOutput()
                && tt.getNextState() == t.getNextState());
    }

    //TODO - add test to add multiple transtions

    //TODO - add test for BadInputException
    // getTransition MUST throw BadInputException when combination current_state, char input does not exist in transitions list
    @Test
    void throwsNDTransitionExceptionWhenDuplicateStateAndInputAreProvided() throws NDTransitionException, BadInputException {
        ITransition tt = new Transition(1, 'z', 'a', 2);
        transitionTable.addTransition(tt);
        Assertions.assertThrows(NDTransitionException.class, () -> transitionTable.addTransition(tt));
    }

    @Test
    void checksIfAnyTransitionHasMissingInputs() {
        // transitionTable -> 1, 'z'
        // TODO - @Kshitij
    }

    @Test
    void returnsTrueWhenTransitionRoutesToIllegalStates() throws NDTransitionException {
        ITransition t1 = new Transition(1, 'z', 'a', 2);
        ITransition t2 = new Transition(2, 'z', 'a', 3);
        ITransition t3 = new Transition(3, 'z', 'a', 100);

        transitionTable.addTransition(t1);
        transitionTable.addTransition(t2);
        transitionTable.addTransition(t3);

        Assertions.assertTrue(transitionTable.hasTransitionsToIllegalStates());
    }

    @Test
    void returnsFalseWhenTransitionDoesNotRoutesToIllegalStates() throws NDTransitionException {
        ITransition t1 = new Transition(1, 'z', 'a', 2);
        ITransition t2 = new Transition(2, 'z', 'a', 3);
        ITransition t3 = new Transition(3, 'z', 'a', 1);

        transitionTable.addTransition(t1);
        transitionTable.addTransition(t2);
        transitionTable.addTransition(t3);

        Assertions.assertFalse(transitionTable.hasTransitionsToIllegalStates());
    }

}
