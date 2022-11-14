package br.dev.wisentini.mysic.model.artist;

import br.dev.wisentini.mysic.model.releaseartist.ReleaseArtist;
import br.dev.wisentini.mysic.model.trackartist.TrackArtist;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.*;

@Entity(name = "Artist")
@Table(name = "artist", schema = "public")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(doNotUseGetters = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Artist {
    @Id
    @Column(name = "artist_id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include()
    private int id;

    @Column(name = "name", unique = true, length = 127, nullable = false)
    private String name;

    @OneToMany(mappedBy = "artist")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    Set<TrackArtist> trackArtistSet;

    @OneToMany(mappedBy = "artist")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    Set<ReleaseArtist> releaseArtistSet;
}
