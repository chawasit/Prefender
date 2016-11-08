package th.ac.cmu.eng.cpe.cpe200;

import th.ac.cmu.eng.cpe.cpe200.bases.State;

import java.util.Stack;

/**
 * Created by zalzer on 11/8/2016 AD.
 */
public class StateManager {

    Stack<State> states;

    public StateManager() {
        states = new Stack<State>();
    }

    public void pop() {
        if(states.empty())
            throw new RuntimeException("States is Empty!");
        states.pop().dispose();
    }

    public void push(State state) {
        states.push(state);
    }

    public State peek() {
        return states.peek();
    }

    public void popPush(State state) {
        this.pop();
        this.push(state);
    }

    public void dispose()
    {
        while(!states.empty())
            pop();
    }

}
