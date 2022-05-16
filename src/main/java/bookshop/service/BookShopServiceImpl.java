package bookshop.service;

import bookshop.exceptions.BookNotFoundExceptions;
import bookshop.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BookShopServiceImpl implements BookShopService{

    public BookShop bookShop;

    public BookShopServiceImpl(BookShop bookShop) {
        this.bookShop = bookShop;
    }

    @Override
    public List<Book> findBookByGenre(Genre genre){
        List<Book> res = new ArrayList<>();
        for (Book book: bookShop.getBooks()
        ) {
            if(book.getGenre() == genre){
                res.add(book);
            }
        }
        return res;
    }

    @Override
    public List<Book> findBookByYear(int year) {
        List<Book> res = new ArrayList<>();
        for (Book book: bookShop.getBooks()
             ) {
            if (book.getYear().equals(year)){
                res.add(book);
            }
        }
        return res;
    }

    @Override
    public List<Book> findBookByAuthor(String author) {
        List<Book> res = new ArrayList<>();
        for (Book book: bookShop.getBooks()
        ) {
            if (book.getAuthor().equals(author)){
                res.add(book);
            }
        }
        return res;
    }

    @Override
    public void showInventory() {
        bookShop.getInventory().forEach((K,V)->{
            System.out.println("BookId: " + K+ " " + "Quantity: " +V);
        });
    }
    @Override
    public Book findBookById(Integer id) throws BookNotFoundExceptions {
        for (Book book: bookShop.getBooks()
        ) {
            if (book.getId().equals(id)){
                return book;
            }
        }
        throw new BookNotFoundExceptions("Book with id: " + id + " not found");
    }
    @Override
    public List<Book> findBooksByIds(Integer... id) {
        List<Book> bks = new ArrayList<>();
        for (Book book: bookShop.getBooks()
        ) {
            for (Integer bokId: id
            ) {
                if (book.getId().equals(bokId)){
                    bks.add(book);
                }
            }
        }
        return bks;
    }

    // printon faturen me vleren me te larte - customer qe kane shpenzuar me shume
    @Override
    public void customerQeKaShpenzuarMeShume(){
        List<Order> value =  bookShop
                .getOrders()
                .stream()
                .max(Comparator
                        .comparing(Order::getTotal))
                .stream()
                .toList();
        //System.out.println(value); -> ***** get faturen me vleren me te larte ****
        for (Order order: value
        ) {
            System.out.println("Customer "
                    + order.getCustomer().getName()
                    + " "
                    + order.getCustomer().getSurname()
                    + " ka shpenzuar: "
                    + order.getTotal()
                    + "!");
        }
    }

    @Override
    public Order createOrder(Customer customer, List<Book> bookList){
        Order order = new Order();
        double price = 0.0;
        for (Book book: bookList
        ) {
            price +=  book.getPrice();
            for (Map.Entry entry: bookShop.getInventory().entrySet()
            ) {
                if (entry.getKey().equals(book.getId())){
                    if ((Integer)entry.getValue() >= 1){
                        bookShop.getInventory().put(book.getId(), (Integer) entry.getValue() - 1);
                    }else {
                        throw new IllegalArgumentException("Book " + book.getTitle() + " is not aviable!!!");
                    }
                }//else System.out.println("Book " + book.getTitle() + " is not aviable in the shop!!!");
            }
        }
        order.setCustomer(customer);
        order.setBookList(bookList);
        order.setDate(LocalDate.now());
        order.setTotal(price);
        bookShop.addOrder(order);
        return order;
    }
    @Override
    public Order createOrder2(Customer customer, Integer... bookId) throws BookNotFoundExceptions {
        Order order = new Order();
        List<Book> bookList = new ArrayList<>(findBooksByIds(bookId));
        double price = 0.0;
        for (Book book: bookList
        ) {
            price +=  book.getPrice();
            for (Map.Entry entry: bookShop.getInventory().entrySet()
            ) {
                if (entry.getKey().equals(book.getId())){
                    if ((Integer)entry.getValue() >= 1){
                        bookShop.getInventory().put(book.getId(), (Integer) entry.getValue() - 1);
                    }else {
                        throw new IllegalArgumentException("Book " + book.getTitle() + " is not aviable!!!");
                    }
                }//else System.out.println("Book " + book.getTitle() + " is not aviable in the shop!!!");
            }
        }
        order.setCustomer(customer);
        order.setBookList(bookList);
        order.setDate(LocalDate.now());
        order.setTotal(price);
        bookShop.addOrder(order);
        return order;
    }
    public void showOrders(){
        for (Order order: bookShop.getOrders()
             ) {
            System.out.println(
                    order.getBookList().stream()
                            .map(r -> r.toString())
                            .collect(Collectors.joining("\n")) + "\n" +
                            order.getCustomer() + "\n" +
                            order.getDate() + "\n" +
                            "Total: "+ order.getTotal() + " $" + "\n" +
                            "---------------------------------------------------"
            );
        }
    }
}
