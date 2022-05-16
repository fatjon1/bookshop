package bookshop.model;

import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Getter
@EqualsAndHashCode
public class Book {
    private Integer id;
    private String title;
    private String author;
    private Genre genre;
    private LocalDate releaseDate;
    private Integer year;
    private double price;

}