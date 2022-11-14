package br.dev.wisentini.mysic.model.genre;

import br.dev.wisentini.mysic.model.releasegenre.ReleaseGenre;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "Genre")
@Table(name = "genre", schema = "public")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(doNotUseGetters = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Genre {
    @Id
    @Column(name = "genre_id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include()
    private int id;

    @Column(name = "name", unique = true, length = 63, nullable = false)
    private String name;

    @OneToMany(mappedBy = "genre")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    Set<ReleaseGenre> releaseGenreSet;
}
