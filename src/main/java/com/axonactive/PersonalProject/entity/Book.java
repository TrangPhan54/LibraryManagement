package com.axonactive.PersonalProject.entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table (name ="book")
@SqlResultSetMapping(
        name = "BookAnalytic",
        classes = {
                @ConstructorResult(
                        targetClass = com.axonactive.PersonalProject.service.dto.BookAnalyticDTO.class,
                        columns ={
                                @ColumnResult(name = "name",type = String.class),
                                @ColumnResult(name = "numberOfPhysicalCopies", type = Long.class)})})
@NamedNativeQuery(
        name = "Book.getBookAnalytic",
        query = "Select b.name as name, count(b.name) as numberOfPhysicalCopies from physical_book pb join book b on pb.book_id = b.id " +
                "group by b.name",
        resultSetMapping = "BookAnalytic"
)
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Long id;

    @Column (name = "name")
    private String name;

    @Column (name = "image", length = 1000)
    private String bookImage;
    @Column (name = "content_summary", length = 1000)
    private String contentSummary;
    @Column (name = "date_publish")
    private LocalDate datePublish;
    @ManyToOne
    @JoinColumn (name = "author_id")
    private Author author;
    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER, mappedBy = "book")
    private List<PhysicalBook> physicalBookList = new ArrayList<>();
}
