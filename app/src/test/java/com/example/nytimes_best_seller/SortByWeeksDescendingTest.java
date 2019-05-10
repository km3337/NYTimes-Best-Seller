package com.example.nytimes_best_seller;

import com.example.nytimes_best_seller.Book_API.Model.BookResults;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SortByWeeksDescendingTest {
    @Test
    public void testEqual(){
        SortByWeeksDescending byWeeksDescending= new SortByWeeksDescending();
        BookResults br1 = BookResults.testSampleBook(6);
        BookResults br2 = BookResults.testSampleBook(6);
        assertEquals(byWeeksDescending.compare(br1,br2), 0);
    }

    @Test
    public void testGreater(){
        SortByWeeksDescending byWeeksDescending= new SortByWeeksDescending();
        BookResults br1 = BookResults.testSampleBook(8);
        BookResults br2 = BookResults.testSampleBook(6);
        assertTrue(byWeeksDescending.compare(br1,br2) < 0);
    }

    @Test
    public void testLessThan(){
        SortByWeeksDescending byWeeksDescending= new SortByWeeksDescending();
        BookResults br1 = BookResults.testSampleBook(6);
        BookResults br2 = BookResults.testSampleBook(8);
        assertTrue(byWeeksDescending.compare(br1,br2) > 0);
    }
}
