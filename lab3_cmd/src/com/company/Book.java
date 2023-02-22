package com.company;
import com.company.BookShop;
import com.company.Type;

import java.io.Serializable;

public class Book extends BookShop implements Serializable {
    Type type;
    public Book()
    {
        super();
    }
    public void setType(String type)
    {
        switch (type) {
            case "BOOK":
                this.type = Type.BOOK;
                break;
            case "MAGAZINE":
                this.type = Type.MAGAZINE;
                break;
            case "POSTCARD":
                this.type = Type.POSTCARD;
                break;
            default:
                this.type = Type.BOOK;
                break;
        }
    }
    public Book(int price, String name,int year,Type type) throws Exception{
        super(price,name,year);
        this.type = type;
    }
    @Override
    public String toString(){
        return super.toString() + " type = " + type;
    }
}
