package bookshop;

import bookshop.model.Book;
import bookshop.model.BookShop;
import bookshop.model.Genre;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ParseCsvFromURL {
    BookShop bookShop;

    public ParseCsvFromURL(BookShop bookShop) {
        this.bookShop = bookShop;
    }

    String DATA_URL = "https://raw.githubusercontent.com/dphi-official/Datasets/master/Amazon%20Top%2050%20Bestselling%20Books%202009%20-%202019.csv";

        public void fetchData () throws IOException, InterruptedException {


            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(DATA_URL))
                    .build();
            HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
            StringReader csvBodyReader = new StringReader(httpResponse.body());

            Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);
            int id = 1;
            for (CSVRecord record : records) {
                Book book = new Book();
                book.setId(id);
                book.setTitle(record.get("Name"));
                book.setAuthor(record.get("Author"));
                book.setGenre(Genre.valueOf(record.get("Genre").replaceAll("\\s","")));
                book.setYear(Integer.valueOf(record.get("Year")));
                book.setPrice(Double.parseDouble(record.get("Price")));
                id++;
                bookShop.addBook(book, 2);
            }

        }

}
