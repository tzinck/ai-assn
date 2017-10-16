public class CSPZebra extends CSP {

    static final int yellow=0, water=0, norwegian=0, fox=0, kools=0;
    static final int blue=1, tea=1, ukrainian=1, horse=1, chesterfield=1;
    static final int red=2, milk=2, englishman=2, snails=2, oldgold=2;
    static final int ivory=3, juice=3, spaniard=3, dog=3, luckystrike=3;
    static final int green=4, coffee=4, japanese=4, zebra=4, parliament=4;

    public boolean isGood (Object X, Object Y, Object x, Object y) {
        return true;
    }

    public static void main(String[] args) throws Exception {
        CSPZebra csp = new CSPZebra();

        Integer[] houses = {0, 1, 2, 3, 4};
        Integer[] color = new Integer[5];
        Integer[] nationality = new Integer[5];
        Integer[] drink = new Integer[5];
        Integer[] smoke = new Integer[5];
        Integer[] pet = new Integer[5];

        //  TODO: add the implicit constraints
        //  each house has unique color

        //  each house has unique drink

        //  each house has unique pet

        //  each house has unique smoke

        //  each house has unique nationality

        //  TODO: add the problem constraints somehow
        //  englishman in red house

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

        for(Object X : color)
            csp.addDomain(X, houses);
        
        for(Object X : nationality)
            csp.addDomain(X, houses);

        for(Object X : drink)
            csp.addDomain(X, houses);
        
        for(Object X : smoke)
            csp.addDomain(X, houses);

        for(Object X : pet)
            csp.addDomain(X, houses);

        
        Search search = new Search(csp);
        System.out.println(search.BacktrackingSearch());
    }

}