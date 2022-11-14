package br.dev.wisentini.mysic.model.trackartist;

import br.dev.wisentini.mysic.model.artist.Artist;
import br.dev.wisentini.mysic.model.track.Track;

import lombok.*;

import javax.persistence.*;

@Entity(name = "TrackArtist")
@Table(name = "track_artist", schema = "public", uniqueConstraints = {@UniqueConstraint(columnNames = {"track_id", "artist_id"})})
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(doNotUseGetters = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class TrackArtist {
    @Id
    @Column(name = "track_artist_id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include()
    private int id;

    @ManyToOne
    @JoinColumn(name = "track_id", nullable = false)
    private Track track;

    @ManyToOne
    @JoinColumn(name = "artist_id", nullable = false)
    private Artist artist;
}
