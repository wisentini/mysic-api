package br.dev.wisentini.mysic.model.releasetrack;

import br.dev.wisentini.mysic.model.release.Release;
import br.dev.wisentini.mysic.model.track.Track;

import lombok.*;

import javax.persistence.*;

@Entity(name = "ReleaseTrack")
@Table(name = "release_track", schema = "public", uniqueConstraints = {@UniqueConstraint(columnNames = {"release_id", "track_id"})})
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(doNotUseGetters = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ReleaseTrack {
    @Id
    @Column(name = "release_track_id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include()
    private int id;

    @Column(name = "track_number", nullable = false)
    private int trackNumber;

    @ManyToOne
    @JoinColumn(name = "release_id", nullable = false)
    private Release release;

    @ManyToOne
    @JoinColumn(name = "track_id", nullable = false)
    private Track track;
}
