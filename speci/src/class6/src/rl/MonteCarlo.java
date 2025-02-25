package rl;

import java.util.Random;

/**
 * Implementation of the classical Monte-Carlo method 
 * on the reinforcement learning domain.
 */
public class MonteCarlo {

    // parameters
    private int maxNumberOfEpisodes;
    private int episodeLength;
    private TaskSpecification ts;

    // decisioning process variables
    private DecisionFunction pi;
    private ActionValueFunction q;        
    private Random random;

    // calculated fields
    private int numberOfStates;
    private int episode;
    private int states[]; 
    private int actions[]; 
    private Reward r;

    /**
     * Initalization of the values.
     */
    public MonteCarlo(TaskSpecification ts) {
        this.ts = ts;

	numberOfStates = ts.getStates().length;

        pi = new DecisionFunction(ts.getStates());
	q = new ActionValueFunction(ts.getStates());

	// initialization
	random = new Random();
	episode = 0;
	maxNumberOfEpisodes = 0; // it must be higher to see anything
	episodeLength = 0; // it must be higher to see anything
    }

    /**
     * Generation of the experimenting periods.
     */
    public void episode() {
        states = new int[episodeLength + 1];
        actions = new int[episodeLength];
	r = new Reward();

	// selection of a starting state 
	int startState = random.nextInt(numberOfStates);
	states[0] = startState;

	// episode generation
	for( int i = 0; i < episodeLength; ++i ) {
	    // selecting the action using pi (decision function)
	    actions[i] = pi.getNextAction(states[i]);
	    // stepping to the next state using action
	    states[i+1] = ts.getNextState(states[i],actions[i]);
	    // calculating the reward
	    r.add(ts.getReward(states[i],actions[i],states[i+1]));
	}

	// reward setting
	for( int j = 0; j < states.length - 1; ++j ) {
	    q.addReward(states[j],actions[j],r);
	}

	// decision function modification
	for( int k = 0; k < states.length - 1; ++k ) {
	    pi.setNextAction(states[k],q.argmax(states[k])); 
	}
    }

    /**
     * Runs the Monte-Carlo method according to the previously set parameters.
     */
    public void run() {
	for( int i = 0; i < maxNumberOfEpisodes; ++i ) {
	    episode();
        }
    }    

    /**
     * Returns the number of episodes performed during run.
     */
    public int getMaxNumberOfEpisodes() {
	return maxNumberOfEpisodes;
    }

    /**
     * Sets the number of episodes performed during run.
     */
    public void setMaxNumberOfEpisodes(int noe) {
	maxNumberOfEpisodes = noe;
    }

    /**
     * Returns the length of episodes performed during run.
     */
    public int getEpisodeLength() {
	return episodeLength;
    }

    /**
     * Sets the length of episodes performed during run.
     */
    public void setEpisodeLength(int el) {
	episodeLength = el;
    }

    /**
     * Returns the uncertainity of an action selection.
     */
    public double getEpsilon() {
	return pi.getEpsilon();
    }

    /**
     * Sets the uncertainity of an action selection.
     */
    public void setEpsilon(double d) throws InvalidProbabilityException {
	pi.setEpsilon(d);
    }

    /**
     * Returns the reward of the last episode.
     */
    public Reward getLastEpisodeReward() {
	return r;
    }

    /**
     * Returns the visited states of the last episode.
     */
    public int[] getLastEpisodeStates() {
	return states;
    }

    /**
     * Returns the commited actions of the last episode.
     */
    public int[] getLastEpisodeActions() {
	return actions;
    }

    /**
     * Returns the decision function for debugging.
     */
    public DecisionFunction getDecisionFunction() {
	return pi;
    }

    /**
     * Returns the action value function for debugging.
     */
    public ActionValueFunction getActionValueFunction() {
	return q;
    }
}
