package rl;

/**
 * Wrapping class of the actual and cumulated reward.
 */
public class Reward implements Comparable {
    double r;
    
    public Reward() {
	r = 0.0;
    }

    public Reward(double r) {
	this.r = r;
    }

    /**
     * Returns the previously set reward.
     */
    public double getReward() {
	return r;
    }

    /**
     * Sets the reward.
     */
    public void setReward(double rew) {
	r = rew;
    }

    public int compareTo(Object o) {
	if( o instanceof Reward ) {
	    Reward r = (Reward)o;
	    return (int)(this.r - r.r);
	} else {
	    throw new ClassCastException();
	}	    
    }

    public void add(Reward r) {
        this.r += r.r;
    } 
}
