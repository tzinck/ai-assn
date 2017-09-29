public class StateWaterJugs
{
    int jugArray[];
    
    public StateWaterJugs(int[] jugArray) { this.jugArray = jugArray; }

    public StateWaterJugs(StateWaterJugs state) {
        jugArray = new int[3];
        for(int i = 0; i < 3; i++) {
            this.jugArray[i] = state.jugArray[i];
        }
    }

    public boolean equals(Object o)
    {
        StateWaterJugs state = (StateWaterJugs) o;

        for (int i = 0; i < 3; i++) {
            if (this.jugArray[i] != state.jugArray[i]) {
                return false;
            }
        }

        return true;
    }

    public int hashcode() {
        return jugArray[0]*100 + jugArray[1]*10 + jugArray[2];
    }

    public String toString() {
        String ret = "";
        ret += "\n12 Gallon: " + this.jugArray[0];
        ret += "\n8 Gallon: " + this.jugArray[1];
        ret += "\n3 Gallon: " + this.jugArray[2];

        return ret;
    }
}