import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class GameNim extends Game {

    int WinningScore = 10;
    int LosingScore = -10;
    int NeutralScore = 0;

    public GameNim() {
        currentState = new StateNim();
    }

    public boolean isWinState(State state) {
        StateNim tstate = (StateNim) state;
        int previous_player = (state.player==0 ? 1: 0);
        if (tstate.coinStack == 1) {
            return true;
        }
        return false;
    }

    public boolean isStuckState(State state) {
        //  TODO: figure out what this should be
        if(isWinState(state)) {
            return false;
        }

        StateNim tstate = (StateNim) state;

        if (tstate.coinStack < 0) {
            return true;
        }

        return false;
    }

    public Set<State> getSuccessors(State state) {

        if (isWinState(state) || isStuckState(state)) {
            return null;
        }

        Set<State> successors = new HashSet<State>();
        StateNim tstate = (StateNim) state;

        StateNim successor_state;

        successor_state = new StateNim(tstate);
        successor_state.coinStack = tstate.coinStack - 1;
        successor_state.player = (tstate.player==0 ? 1: 0);
        successors.add(successor_state);

        successor_state.coinStack = tstate.coinStack - 2;
        successor_state.player = (tstate.player==0 ? 1 : 0);
        successors.add(successor_state);

        successor_state.coinStack = tstate.coinStack - 3;
        successor_state.player = (tstate.player==0 ? 1 : 0);
        successors.add(successor_state);

        return successors;
    }

    public double eval(State state) {
        if(isWinState(state)) {
            int previous_player = (state.player==0 ? 1 : 0);

            if (previous_player == 0) {
                return WinningScore;
            } else {
                return LosingScore;
            }
        }
        return NeutralScore;
    }

    public static void main(String[] args) throws Exception {
        Game game = new GameNim();
        Search search = new Search(game);
        int depth = 13;

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        while (true) {

            StateNim nextState = null;

            switch ( game.currentState.player ) {
                case 1:
                    //  Human
                    System.out.print("Enter your *valid* move> ");
                    int move = Integer.parseInt( in.readLine() );

                    nextState = new StateNim((StateNim)game.currentState);
                    nextState.player = 1;
                    nextState.coinStack = nextState.coinStack - move;
                    System.out.println("Human: \n" + nextState);
                    break;

                case 0:
                    //  Computer
                    nextState = (StateNim)search.bestSuccessorState(depth);
                    nextState.player = 0;
                    System.out.println("Computer: \n" + nextState);
                    break;
            }

            game.currentState = nextState;

            game.currentState.player = (game.currentState.player==0 ? 1: 0);

            if (game.isWinState(game.currentState) ) {

                if (game.currentState.player == 1) {
                    System.out.println("Computer wins!");
                } else {
                    System.out.println("You win!");
                }

                break;
            }

            if ( game.isStuckState(game.currentState) ) {
                System.out.println("Game is stuck!");
                break;
            }
        }
    }
}