import java.util.HashSet;
import java.util.Set;

public class ProblemWaterJugs extends Problem {

    static final int jug12 = 0;
    static final int jug8  = 1;
    static final int jug3  = 2;

    boolean goal_test(Object state) {
        StateWaterJugs jug_state = (StateWaterJugs) state;
        
        if (jug_state.jugArray[jug12]==1 || jug_state.jugArray[jug8]==1 || jug_state.jugArray[jug3]==1)
            return true;
        else return false;
	}

    Set<Object> getSuccessors(Object state) {
    	
        Set<Object> set = new HashSet<Object>();
        StateWaterJugs jug_state = (StateWaterJugs) state;

        StateWaterJugs successor_state;

        //  Fill the 12
        successor_state = new StateWaterJugs(jug_state);
        successor_state.jugArray[jug12]  = 12;
        successor_state.jugArray[jug8]  += 0;
        successor_state.jugArray[jug3]  += 0;
        if (isValid(successor_state)) set.add(successor_state);

        //  Fill the 8
        successor_state = new StateWaterJugs(jug_state);
        successor_state.jugArray[jug12] += 0;
        successor_state.jugArray[jug8]   = 8;
        successor_state.jugArray[jug3]  += 0;
        if (isValid(successor_state)) set.add(successor_state);

        //  Fill the 3
        successor_state = new StateWaterJugs(jug_state);
        successor_state.jugArray[jug12] += 0;
        successor_state.jugArray[jug8]  += 0;
        successor_state.jugArray[jug3]   = 3;
        if (isValid(successor_state)) set.add(successor_state);

        //  Pour the 8 into the 12
        successor_state = new StateWaterJugs(jug_state);
        successor_state.jugArray[jug12] += 8;
        successor_state.jugArray[jug8]  -= 8;
        successor_state.jugArray[jug3]  += 0;
        if (isValid(successor_state)) set.add(successor_state);

        //  Pour the 3 into the 12
        successor_state = new StateWaterJugs(jug_state);
        successor_state.jugArray[jug12] += 3;
        successor_state.jugArray[jug8]  += 0;
        successor_state.jugArray[jug3]  -= 3;
        if (isValid(successor_state)) set.add(successor_state);

        //  Pour the 3 into the 8
        successor_state = new StateWaterJugs(jug_state);
        successor_state.jugArray[jug12] += 0;
        successor_state.jugArray[jug8]  += 3;
        successor_state.jugArray[jug3]  -= 3;
        if (isValid(successor_state)) set.add(successor_state);

        //  Pour the 12 into the 8
        successor_state = new StateWaterJugs(jug_state);
        int amountToPour = 8 - successor_state.jugArray[jug8];
        successor_state.jugArray[jug12] -= amountToPour;
        successor_state.jugArray[jug8]  += amountToPour;
        successor_state.jugArray[jug3]  += 0;
        if (isValid(successor_state)) set.add(successor_state);

        //  Pour the 12 into the 3
        successor_state = new StateWaterJugs(jug_state);
        amountToPour = 3 - successor_state.jugArray[jug3];
        successor_state.jugArray[jug12] -= amountToPour;
        successor_state.jugArray[jug8]  += 0;
        successor_state.jugArray[jug3]  -= amountToPour;
        if (isValid(successor_state)) set.add(successor_state);

        //  Pour the 8 into the 3
        successor_state = new StateWaterJugs(jug_state);
        amountToPour = 3 - successor_state.jugArray[jug3];
        successor_state.jugArray[jug12] += 0;
        successor_state.jugArray[jug8]  -= amountToPour;
        successor_state.jugArray[jug3]  += amountToPour;
        if (isValid(successor_state)) set.add(successor_state);

        //  Empty the 3
        successor_state = new StateWaterJugs(jug_state);
        successor_state.jugArray[jug12] += 0;
        successor_state.jugArray[jug8]  += 0;
        successor_state.jugArray[jug3]   = 0;
        if (isValid(successor_state)) set.add(successor_state);

        //  Empty the 8
        successor_state = new StateWaterJugs(jug_state);
        successor_state.jugArray[jug12] += 0;
        successor_state.jugArray[jug8]   = 0;
        successor_state.jugArray[jug3]  += 0;
        if (isValid(successor_state)) set.add(successor_state);

        //  Empty the 12
        successor_state = new StateWaterJugs(jug_state);
        successor_state.jugArray[jug12]  = 0;
        successor_state.jugArray[jug8]  += 0;
        successor_state.jugArray[jug3]  += 0;
        if (isValid(successor_state)) set.add(successor_state);

        return set;
    }

    public boolean isValid(StateWaterJugs state) {
        //  Check for negative jugs
        for (int i = 0; i < 3; i++) {
            if (state.jugArray[i] < 0) return false;
        }

        if ( (state.jugArray[jug12] > 12) || (state.jugArray[jug8] > 8) || (state.jugArray[jug3]  > 3) ) {
            return false;
        }

        return true;
    }

    double step_cost(Object fromState, Object toState) {
        StateWaterJugs from = (StateWaterJugs) fromState;
        StateWaterJugs to = (StateWaterJugs) toState;

        if ( (from.jugArray[jug12] - to.jugArray[jug12]) != 0 ) {
            return Math.abs(from.jugArray[jug12] - to.jugArray[jug12]);
        }

        if ( (from.jugArray[jug8] - to.jugArray[jug8]) != 0 ) {
            return Math.abs(from.jugArray[jug8] - to.jugArray[jug8]);
        }

        if ( (from.jugArray[jug3] - to.jugArray[jug3]) != 0 ) {
            return Math.abs(from.jugArray[jug3] - to.jugArray[jug3]);
        }

        return 100000000;
    }

    public double h(Object state) {
        //  TODO: check what the hell this is supposed to be doing
        return 0;
    }

    public static void main(String[] args) throws Exception {
		ProblemWaterJugs problem = new ProblemWaterJugs();
		int[] jugArray = {0, 0, 0};
		problem.initialState = new StateWaterJugs(jugArray); 
		
		Search search  = new Search(problem);
		
		System.out.println("BreadthFirstTreeSearch:\t\t" + search.BreadthFirstTreeSearch());

		System.out.println("BreadthFirstGraphSearch:\t" + search.BreadthFirstGraphSearch());

        //System.out.println("DepthFirstTreeSearch:\t\t" + search.DepthFirstTreeSearch());

		//System.out.println("DepthFirstGraphSearch:\t\t" + search.DepthFirstGraphSearch());

		System.out.println("UniformCostTreeSearch:\t\t" + search.UniformCostTreeSearch());

		System.out.println("UniformCostGraphSearch:\t\t" + search.UniformCostGraphSearch());

		//System.out.println("GreedyBestFirstTreeSearch:\t" + search.GreedyBestFirstTreeSearch());

		//System.out.println("GreedyBestFirstGraphSearch:\t" + search.GreedyBestFirstGraphSearch());

		//System.out.println("AstarTreeSearch:\t\t" + search.AstarTreeSearch());

		//System.out.println("AstarGraphSearch:\t\t" + search.AstarGraphSearch());

		//System.out.println("TreeSearchDepthLimited:\t\t" + search.DepthLimitedTreeSearch());

		//System.out.println("GraphSearchDepthLimited:\t" + search.DepthLimitedGraphSearch());

		//System.out.println("IterativeDeepeningTreeSearch:\t" + search.IterativeDeepeningTreeSearch(new FrontierLIFO()));

		//System.out.println("IterativeDeepeningGraphSearch:\t" + search.IterativeDeepeningGraphSearch(new FrontierLIFO()));
	}
}
