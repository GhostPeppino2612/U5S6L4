package giuseppeacquaviva.U5S6L4.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "blogposts")
public class BlogPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String categoria;
    private String titolo;
    private String cover;
    private String content;
    private double tempoDiLettura;

    @ManyToOne
    @JoinColumn(name = "authorId")
    private Author autore;
}
