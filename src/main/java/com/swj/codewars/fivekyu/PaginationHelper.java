package com.swj.codewars.fivekyu;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author shouguouo~
 * @date 2020/9/10 - 19:43
 */
public class PaginationHelper<I> {

    private List<I> collection;

    private int itemsPerPage;

    /**
     * The constructor takes in an array of items and a integer indicating how many
     * items fit within a single page
     */
    public PaginationHelper(List<I> collection, int itemsPerPage) {
        this.collection = collection;
        this.itemsPerPage = itemsPerPage;
    }

    /**
     * returns the number of items within the entire collection
     */
    public int itemCount() {
        return collection.size();
    }

    /**
     * returns the number of pages
     */
    public int pageCount() {
        if (collection.size() % itemsPerPage == 0) {
            return collection.size() / itemsPerPage;
        } else {
            return collection.size() / itemsPerPage + 1;
        }
    }

    /**
     * returns the number of items on the current page. page_index is zero based.
     * this method should return -1 for pageIndex values that are out of range
     */
    public int pageItemCount(int pageIndex) {
        int actual = pageIndex + 1;
        int pageCount = pageCount();
        if (actual < 1 || actual > pageCount) {
            return -1;
        }
        if (pageCount == actual) {
            return collection.size() - itemsPerPage * (pageCount - 1);
        }
        return itemsPerPage;
    }

    /**
     * determines what page an item is on. Zero based indexes
     * this method should return -1 for itemIndex values that are out of range
     */
    public int pageIndex(int itemIndex) {
        if (itemIndex < 0 || itemIndex > collection.size() - 1) {
            return -1;
        }
        return itemIndex / itemsPerPage;
    }

    public static void main(String[] args) {
        PaginationHelper<Character> helper = new PaginationHelper<>(Arrays.asList('a', 'b', 'c', 'd', 'e', 'f'), 4);
        assertEquals(2, helper.pageCount()); //should == 2
        assertEquals(6, helper.itemCount()); //should == 6
        assertEquals(4, helper.pageItemCount(0)); //should == 4
        assertEquals(2, helper.pageItemCount(1)); // last page - should == 2
        assertEquals(-1, helper.pageItemCount(2)); // should == -1 since the page is invalid

        // pageIndex takes an item index and returns the page that it belongs on
        assertEquals(1, helper.pageIndex(5)); //should == 1 (zero based index)
        assertEquals(0, helper.pageIndex(2)); //should == 0
        assertEquals(-1, helper.pageIndex(20)); //should == -1
        assertEquals(-1, helper.pageIndex(-10)); //should == -1
    }
}
