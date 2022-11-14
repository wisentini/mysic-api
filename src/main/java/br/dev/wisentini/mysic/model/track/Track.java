package br.dev.wisentini.mysic.model.track;

import br.dev.wisentini.mysic.model.releasetrack.ReleaseTrack;
import br.dev.wisentini.mysic.model.trackartist.TrackArtist;
import br.dev.wisentini.mysic.util.StringUtil;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.*;

@Entity(name = "Track")
@Table(name = "track", schema = "public")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(doNotUseGetters = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Track {
    @Id
    @Column(name = "track_id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include()
    private int id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "duration_ms", nullable = false)
    @JsonIgnore
    private int durationMs;

    @OneToMany(mappedBy = "track")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    Set<TrackArtist> trackArtistSet;

    @OneToMany(mappedBy = "track")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    Set<ReleaseTrack> releaseTrackSet;

    public String getDuration() {
        return StringUtil.durationMsLongToString(this.durationMs);
    }
}
