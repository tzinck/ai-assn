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

        //  coffee in green house

        //  ukranian drinks tea

        //  green house to right of ivory house

        //  oldgold smoker has snails

        //  kools smoked in the yellow house

        //  milk is drunk in the middle house

        //  norweigan first house on the left

        //  chesterfield smoker lives next to the fox owner

        //  kools smoked in the house next to the house where the horse is

        //  lucky strike smoker drinks orange juice

        //  japanese man smokes parliament

        //  norweigian lives next to the blue house

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

        //  coffee in green house

        //  ukranian drinks tea

        //  green house to right of ivory house

        //  oldgold smoker has snails

        //  kools smoked in the yellow house

        //  milk is drunk in the middle house

        //  norweigan first house on the left

        //  chesterfield smoker lives next to the fox owner

        //  kools smoked in the house next to the house where the horse is

        //  lucky strike smoker drinks orange juice

        //  japanese man smokes parliament

        //  norweigian lives next to the blue house


        //  Uniqueness Constraints


        //  Get the solution
        Search search = new Search(csp);
        System.out.println(search.BacktrackingSearch());
    }

}