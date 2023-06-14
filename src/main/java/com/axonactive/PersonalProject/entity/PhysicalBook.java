package com.axonactive.PersonalProject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name ="physical_book")
public class PhysicalBook {
    @Id
    private Long id;
    @Column (name = "import_price")
    private Double importPrice;
    @Column (name = "import_date")
    private LocalDate importDate;
    @ManyToOne
    @JoinColumn(name = "publishing_house_id")
    private PublishingHouse publishingHouse;
    @ManyToOne
    @JoinColumn (name = "book_id")
    private Book book;
    @Column (name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;
    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, mappedBy = "physicalBook")
    private List<BorrowNoteDetail> borrowNoteDetailList = new ArrayList<>();
}
