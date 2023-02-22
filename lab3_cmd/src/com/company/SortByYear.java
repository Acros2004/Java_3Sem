package com.company;
import com.company.BookShop;
import java.util.Comparator;

public class SortByYear implements Comparator<BookShop> {

    @Override
    public int compare(BookShop first, BookShop second ) {
        return Integer.compare(first.getYear(), second.getYear());
    }
}