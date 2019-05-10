package com.example.nytimes_best_seller;

import com.example.nytimes_best_seller.Book_API.Model.BookResults;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class SortByWeeksAscendingTest {
    @Test
    public void testEqual(){
        SortByWeeksAscending byWeeksAscending = new SortByWeeksAscending();
        BookResults br1 = BookResults.testSampleBook(6);
        BookResults br2 = BookResults.testSampleBook(6);
        assertEquals(byWeeksAscending.compare(br1,br2), 0);
    }

    @Test
    public void testGreater(){
        SortByWeeksAscending byWeeksAscending = new SortByWeeksAscending();
        BookResults br1 = BookResults.testSampleBook(8);
        BookResults br2 = BookResults.testSampleBook(6);
        assertTrue(byWeeksAscending.compare(br1,br2) > 0);
    }

    @Test
    public void testLessThan(){
        SortByWeeksAscending byWeeksAscending = new SortByWeeksAscending();
        BookResults br1 = BookResults.testSampleBook(6);
        BookResults br2 = BookResults.testSampleBook(8);
        assertTrue(byWeeksAscending.compare(br1,br2) < 0);
    }



}