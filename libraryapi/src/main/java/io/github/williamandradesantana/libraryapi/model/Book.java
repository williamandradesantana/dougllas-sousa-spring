package io.github.williamandradesantana.libraryapi.model;

import io.github.williamandradesantana.libraryapi.model.enums.Gender;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tb_books", schema = "public")
@Data
@ToString(exclude = "author")
@EntityListeners(AuditingEntityListener.class)
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "isbn", length = 20, nullable = false, unique = true)
    private String isbn;

    @Column(name = "title", length = 150, nullable = false)
    private String title;

    @Column(name = "publish_date", nullable = false)
    private LocalDate publishDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", length = 30, nullable = false)
    private Gender gender;

    @Column(name = "price", precision = 18, scale = 2)
    private BigDecimal price;

    @ManyToOne(//cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "author_id")
    private Author author;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "user_id")
    private UUID userId;
}
