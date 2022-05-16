package bookshop.model;

import lombok.*;

import java.time.LocalDate;
import java.util.List;
@Setter
@Getter
public class Order  {
    Customer customer;
    List<Book> bookList;
    LocalDate date;
    double total;

    public Order() {
        //System.out.println("new order created");
    }

    public Order( Customer customer, List<Book> bookLists) {
        this.customer = customer;
        this.bookList = bookLists;
    }

    @Override
    public String toString() {
        return "Order{" +
                "customer=" + customer +
                ", bookList=" + bookList +
                ", date=" + date +
                ", total=" + total +
                '}';
    }
}
