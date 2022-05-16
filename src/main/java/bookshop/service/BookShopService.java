package bookshop.service;

import bookshop.exceptions.BookNotFoundExceptions;
import bookshop.model.Book;
import bookshop.model.Customer;
import bookshop.model.Genre;
import bookshop.model.Order;

import java.util.List;

public interface BookShopService {

    public List<Book> findBookByGenre(Genre genre);
    public List<Book> findBookByYear(int year);
    public List<Book> findBookByAuthor(String author);


    void showInventory();
    public void showOrders();

    public Book findBookById(Integer id) throws BookNotFoundExceptions;

    List<Book> findBooksByIds(Integer... id);

    void customerQeKaShpenzuarMeShume();

    Order createOrder(Customer customer, List<Book> bookList) throws Exception;

    Order createOrder2(Customer customer, Integer... bookId) throws BookNotFoundExceptions;
}
