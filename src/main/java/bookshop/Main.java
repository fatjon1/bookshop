package bookshop;

import bookshop.exceptions.BookNotFoundExceptions;
import bookshop.model.BookShop;
import bookshop.model.Customer;
import bookshop.service.BookShopService;
import bookshop.service.BookShopServiceImpl;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException, BookNotFoundExceptions {
        BookShop bookShop = new BookShop();
        BookShopService bookShopService = new BookShopServiceImpl(bookShop);
        ParseCsvFromURL urlData = new ParseCsvFromURL(bookShop);
        urlData.fetchData(); //fetch data from url and insert into bookshop inventory

        Customer customer1 = new Customer("Frank", "Been", "Fier", "99999");
        Customer customer2 = new Customer("Bob", "Dohnathon", "Fier", "99999");
        Customer customer3 = new Customer("Kevin", "Glennjamin", "Fier", "99999");
        Customer customer4 = new Customer("Steve", "Thonkenstein", "Fier", "99999");
        Customer customer5 = new Customer("Blinky", "Darrence", "Fier", "99999");


        bookShopService.createOrder2(customer1, 2, 3);
        bookShopService.createOrder2(customer2, 2, 5);
        bookShopService.createOrder2(customer3, 9, 9);
        bookShopService.createOrder2(customer3, 99);
        bookShopService.createOrder2(customer5, 398, 489);
        bookShopService.createOrder2(customer4, 77);
        bookShopService.createOrder2(customer1, 123, 56);
        bookShopService.createOrder2(customer2, 245);
        bookShopService.createOrder2(customer5, 33,108);


        //1.customer qe kane shpenzuar me shume
        bookShopService.customerQeKaShpenzuarMeShume();

        //2.Listoni librat me te shitur
//        for (Order order : bookShop.getOrders()
//        ) {
//            Map<Integer, Long> frequencyMap = new HashMap<>();
//            for (Book book : order.getBookList()) {
//
//
//                    frequencyMap.merge(book.getId(), 1L, Long::sum);
//
//                //frequencyMap.compute(book.getId(), (key, val) -> val + entry.getValue());
//
//
////            for (Map.Entry<Integer, Long> entry : frequencyMap.entrySet()) {
////                System.out.println(entry.getKey() + ": " + entry.getValue());
////            }
////                Map<Book, Long> counted = order.getBookList().stream()
////                        .collect(Collectors.groupingByConcurrent(Function.identity(), Collectors.counting()));
////                System.out.println(counted);
//                System.out.println(frequencyMap);
//
//            }
//        }

        //3.Listoni librat te cilet kan ngel pa shitur
        //bookShopService.showInventory();

        //4. Find book by genre
        //System.out.println(bookShopService.findBookByGenre(Genre.NonFiction));

        //5. Find book by year
        //System.out.println(bookShopService.findBookByYear(2016));

        //6. Find book by author
        //System.out.println(bookShopService.findBookByAuthor("JJ Smith"));

        //7. Show all orders
        // bookShopService.showOrders();
    }
}
