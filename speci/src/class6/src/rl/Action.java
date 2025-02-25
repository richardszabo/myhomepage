package rl;

public abstract class Action {
    /**
     * Returns the probability of the successing states 
     * after the performance of the action in parametered state.
     * Probabilities should sum up to 1.0.
     */
    public abstract Probability a(State act,State next);

    /**
     * Returns the immediate reward after the performance of the action 
     * in parametered state resulting a new state.
     */
    public abstract Reward r(State act,State next);
}
