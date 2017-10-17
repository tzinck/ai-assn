import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;

public class CSPZebra extends CSP {

    static Set<Object> varCol = new HashSet<Object>(
        Arrays.asList(new String[]{"blue","green","ivory","red","yellow"}));

    static Set<Object> varDri = new HashSet<Object>(
        Arrays.asList(new String[]{"coffee","milk","orange-juice","tea","water"}));

    static Set<Object> varNat = new HashSet<Object>(
        Arrays.asList(new String[]{"englishman","japanese","norwegian","spaniard","ukranian"}));

    static Set<Object> varPet = new HashSet<Object>(
        Arrays.asList(new String[]{"dog","fox","horse","snails","zebra"}));

    static Set<Object> varCig = new HashSet<Object>(
        Arrays.asList(new String[]{"chesterfield","kools","lucky-strike","old-gold","parliament"}));

    public boolean isGood (Object X, Object Y, Object x, Object y) {
        
        if(!C.containsKey(X))
            return true;

        if(!C.get(X).contains(Y))
            return true;

        //  Englishman in the red house
        if(X.equals("englishman") && Y.equals("red") && !x.equals(y))
            return false;
        
        //  spaniard owns a dog
        if(X.equals("spaniard") && Y.equals("dog") && !x.equals(y))
            return false;

        //  coffee in green house
        if(X.equals("coffee") && Y.equals("green") && !x.equals(y))
            return false;

        //  ukranian drinks tea
        if(X.equals("ukranian") && Y.equals("tea") && !x.equals(y))
            return false;

        //  green house directly to right of ivory house
        if(X.equals("green") && Y.equals("ivory") && (Integer) x != (Integer) y + 1)
            return false;
        
        if(X.equals("ivory") && Y.equals("green") && (Integer) x + 1 != (Integer) y)
            return false;

        //  oldgold smoker has snails
        if(X.equals("old-gold") && Y.equals("snails") && !x.equals(y))
            return false;

        //  kools smoked in the yellow house
        if(X.equals("kools") && Y.equals("yellow") && !x.equals(y))
            return false;

        //  milk is drunk in the middle house
        if(X.equals("milk") && (Integer) x != 3)
            return false;

        //  norweigan first house on the left
        if(X.equals("norwegian") && (Integer) x != 1)
            return false;

        //  chesterfield smoker lives next to the fox owner
        if(X.equals("chesterfield") && Y.equals("fox") && (Math.abs((Integer) x - (Integer) y) != 1))
            return false;

        //  kools smoked in the house next to the house where the horse is
        if(X.equals("kools") && Y.equals("horse") && (Math.abs((Integer) x - (Integer) y) != 1))
            return false;

        //  lucky strike smoker drinks orange juice
        if(X.equals("lucky-strike") && Y.equals("orange-juice") && !x.equals(y))
            return false;

        //  japanese man smokes parliament
        if(X.equals("japanese") && Y.equals("parliament") && !x.equals(y))
            return false;

        //  norweigian lives next to the blue house
        if(X.equals("norwegian") && Y.equals("blue") && (Math.abs((Integer) x - (Integer) y) != 1))
            return false;

        if(X.equals("blue") && Y.equals("norwegian") && (Math.abs((Integer) x - (Integer) y) != 1))
            return false;

        //  Uniqueness Constraints
        if(varCol.contains(X) && varCol.contains(Y) && !X.equals(Y) && x.equals(y))
            return false;
        
        if(varDri.contains(X) && varDri.contains(Y) && !X.equals(Y) && x.equals(y))
            return false;
        
        if(varNat.contains(X) && varNat.contains(Y) && !X.equals(Y) && x.equals(y))
            return false;
        
        if(varPet.contains(X) && varPet.contains(Y) && !X.equals(Y) && x.equals(y))
            return false;
        
        if(varCig.contains(X) && varCig.contains(Y) && !X.equals(Y) && x.equals(y))
            return false;

        return true;
    }

    public static void main(String[] args) throws Exception {
        CSPZebra csp = new CSPZebra();

        Integer[] dom = {1, 2, 3, 4, 5};

        for(Object X: varCol)
            csp.addDomain(X, dom);

        for(Object X: varDri)
            csp.addDomain(X, dom);

        for(Object X: varNat)
            csp.addDomain(X, dom);
        
        for(Object X: varPet)
            csp.addDomain(X, dom);

        for(Object X: varCig)
            csp.addDomain(X, dom);


        //  Add Unary Constraints


        //  Binary Constraints

        //  Englishman in the red house
        csp.addBidirectionalArc("englishman", "red");

        //  spaniard owns a dog
        csp.addBidirectionalArc("spaniard", "dog");

        //  coffee in green house
        csp.addBidirectionalArc("coffee", "green");

        //  ukranian drinks tea
        csp.addBidirectionalArc("ukranian", "tea");

        //  green house to right of ivory house
        csp.addBidirectionalArc("green", "ivory");

        //  oldgold smoker has snails
        csp.addBidirectionalArc("old-gold", "snails");

        //  kools smoked in the yellow house
        csp.addBidirectionalArc("kools", "yellow");

        //  chesterfield smoker lives next to the fox owner
        csp.addBidirectionalArc("chesterfield", "fox");

        //  kools smoked in the house next to the house where the horse is
        csp.addBidirectionalArc("kools", "horse");

        //  lucky strike smoker drinks orange juice
        csp.addBidirectionalArc("lucky-strike", "orange-juice");

        //  japanese man smokes parliament
        csp.addBidirectionalArc("japanese", "parliament");

        //  norweigian lives next to the blue house
        csp.addBidirectionalArc("norwegian", "blue");


        //  Uniqueness Constraints
        for(Object X : varCol) {
            for(Object Y : varCol) {
                csp.addBidirectionalArc(X, Y);
            }
        }

        for(Object X : varDri) {
            for(Object Y : varDri) {
                csp.addBidirectionalArc(X, Y);
            }
        }

        for(Object X : varNat) {
            for(Object Y : varNat) {
                csp.addBidirectionalArc(X, Y);
            }
        }

        for(Object X : varPet) {
            for(Object Y : varPet) {
                csp.addBidirectionalArc(X, Y);
            }
        }

        for(Object X : varCig) {
            for(Object Y : varCig) {
                csp.addBidirectionalArc(X, Y);
            }
        }

        //  Get the solution
        Search search = new Search(csp);
        System.out.println(search.BacktrackingSearch());
    }

}