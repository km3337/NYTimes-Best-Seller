package com.example.nytimes_best_seller;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RankTest {
    @Test
    public void noRankChangeTest(){
        int rank = 0, dSymbol = R.string.nochg;
        String stringDisplayed = dSymbol + " no change since last week";
        assertEquals(getRankDelta(rank), stringDisplayed); ;

    }

    @Test
    public void rankMoveUpTest(){
        int rank = -3, dSymbol = R.string.up;
        String stringDisplayed = dSymbol + " by " + 3 + " since last week";
        assertEquals(getRankDelta(rank), stringDisplayed);
    }

    @Test
    public void rankMoveDownTest(){
        int rank = 4, dSymbol = R.string.down;
        String stringDisplayed = dSymbol + " by " + 4 + " since last week";
        assertEquals(getRankDelta(rank), stringDisplayed); ;

    }





    //Copied logic from BookDetailsActivity.java
    public String getRankDelta(int rank) {
        int D1 = 0;
        int D2 = Math.abs((D1 - rank));
        int dSymbol;
        if (rank < D1) {
            dSymbol = (R.string.up);
        }
        else if (rank > D1) {
            dSymbol =(R.string.down);
        } else {
            dSymbol = (R.string.nochg);
            return dSymbol + " no change since last week";
        }
        return dSymbol + " by " + D2 + " since last week";

    }

}
