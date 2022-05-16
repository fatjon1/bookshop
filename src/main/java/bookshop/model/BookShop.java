package bookshop.model;


import java.util.*;
import java.util.stream.Collectors;

public class BookShop {

   private List<Book> books = new ArrayList<>();
    private List<Order> orders = new ArrayList<>();
    private HashMap<Integer, Integer> inventory = new HashMap<>();



    public BookShop() {
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    public List<Order> getOrders() {
        return orders;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void addBook(Book book, Integer quantity) {
        books.add(book);
        setInventory(book.getId(),quantity);
    }

    @Override
    public String toString() {
        return "BookShop{" + "\n" +
                "Books=" + books.stream()
                .map(r -> r.toString())
                .collect(Collectors.joining("\n")) + "\n" +
                "Orders=" + orders + "\n" +
                '}';
    }


    private void setInventory(Integer bookId, Integer quantity) {
            inventory.put(bookId, quantity);
    }

    public HashMap<Integer, Integer> getInventory() {
            return inventory;
    }
}
