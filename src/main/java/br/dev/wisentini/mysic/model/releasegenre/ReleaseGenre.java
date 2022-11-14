package br.dev.wisentini.mysic.model.releasegenre;

import br.dev.wisentini.mysic.model.genre.Genre;
import br.dev.wisentini.mysic.model.release.Release;

import lombok.*;

import javax.persistence.*;

@Entity(name = "ReleaseGenre")
@Table(name = "release_genre", schema = "public", uniqueConstraints = {@UniqueConstraint(columnNames = {"release_id", "genre_id"})})
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(doNotUseGetters = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ReleaseGenre {
    @Id
    @Column(name = "release_genre_id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include()
    private int id;

    @ManyToOne
    @JoinColumn(name = "release_id", nullable = false)
    private Release release;

    @ManyToOne
    @JoinColumn(name = "genre_id", nullable = false)
    private Genre genre;
}
