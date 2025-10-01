class Solution {
    public int numWaterBottles(int numBottles, int numExchange) {
        int drank = numBottles;   // drink all initial bottles
        int empty = numBottles;   // now we have this many empties

        while (empty >= numExchange) {
            int newBottles = empty / numExchange;     // how many new full bottles we get
            drank += newBottles;                      // drink them
            empty = empty % numExchange + newBottles; // leftover empties + new empties
        }

        return drank;
    }}
