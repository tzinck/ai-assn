public class StateNim extends State {
    
    public int coinStack;
 
    public StateNim() {
        coinStack = 13;
        player  = 1;
    }

    public StateNim(StateNim state) {
        this.coinStack = state.coinStack;
        player = state.player;
    }

    public String toString() {
        String ret = "";

        for(int i = 0; i < coinStack; i++) {
            ret += "O\n";
        }
        return ret;
    }
}