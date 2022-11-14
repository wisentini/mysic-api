package br.dev.wisentini.mysic.model.releaseartist;

import br.dev.wisentini.mysic.model.artist.Artist;
import br.dev.wisentini.mysic.model.release.Release;

import lombok.*;

import javax.persistence.*;

@Entity(name = "ReleaseArtist")
@Table(name = "release_artist", schema = "public", uniqueConstraints = {@UniqueConstraint(columnNames = {"release_id", "artist_id"})})
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(doNotUseGetters = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ReleaseArtist {
    @Id
    @Column(name = "release_artist_id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include()
    private int id;

    @ManyToOne
    @JoinColumn(name = "release_id", nullable = false)
    private Release release;

    @ManyToOne
    @JoinColumn(name = "artist_id", nullable = false)
    private Artist artist;
}
