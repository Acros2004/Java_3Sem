package com.company;

import java.util.ArrayList;
import java.util.logging.Logger;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.*;
public class Main {
    private static final Logger LOG = Logger.getLogger(String.valueOf(Main.class));
    public static void main(String[] args) throws Exception {
        LOG.info("Starting program");
        try {
            ArrayList<BookShop> books = new ArrayList<>();
            books.add(new Book(100, "С днём святого Валентина", 2023, Type.POSTCARD));
            books.add(new Book(120, "Гарри Никита", 2004, Type.BOOK));
            books.add(new Book(140, "Крутой журнал", 2011, Type.MAGAZINE));
            System.out.println("--------------------------------------------");
            for (BookShop book : books) {
                System.out.println(book);
            }
            System.out.println("--------------------------------------------");
            books.sort(new SortByYear());
            for (BookShop book : books) {
                System.out.println(book);
            }
            System.out.println("--------------------------------------------");
            Admin admin = new Admin();
            admin.CountPrice(books);
            admin.SearchBook(books, "Гарри Никита");
            System.out.println("--------------------------------------------");
            admin.Sold(books, "Гарри Никита");
            for (BookShop book : books) {
                System.out.println(book);
            }
        }
        catch (Exception e){
            LOG.info(e.getMessage());
        }
        System.out.println("----------лб 4------------");
        final File schemaFile = new File("./files/Xsd.xsd");
        final Source xmlFile = new StreamSource("./files/Xml.xml");
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        try {
            Schema schema = schemaFactory.newSchema(schemaFile);
            Validator validator = schema.newValidator();
            validator.validate(xmlFile);
            System.out.println(xmlFile.getSystemId() + " is valid");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        ArrayList<BookShop> books1;
        final String fileName = "C:\\Users\\nikit\\Desktop\\univer\\2cource2sem\\Java\\lab3\\files\\Xml.xml";
        Parser parser = new Parser();
        books1 = parser.CreateBookFromXml(fileName);
        books1.add(new Book(200, "Никитины рассказы", 2003, Type.BOOK));
        books1.add(new Book(500,"Комикс", 2016, Type.MAGAZINE));
        books1.sort(new SortByYear() ) ;// comparator
        System.out.println("--------Serialization--------------");
        String filePath = "./files/books.json";
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath)))
        {
            oos.writeObject(books1);
            System.out.println("File has been written");
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        LOG.info("JSON serialized");
        var NBOOK = new ArrayList<BookShop>();
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath)))
        {
            NBOOK =((ArrayList<BookShop>)ois.readObject());
            for (BookShop book:NBOOK)
            {
                System.out.println(book);
            }
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        LOG.info("JSON deserialized");
        System.out.println("----------StreamAPI---------");
        books1.stream().filter(e -> e.getPrice() < 300).forEach(System.out::println);

        LOG.info("Stream API");
        LOG.info("Final\n");
    }
}