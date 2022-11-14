package br.dev.wisentini.mysic.model.release;

import br.dev.wisentini.mysic.database.type.PostgreSQLEnumType;
import br.dev.wisentini.mysic.model.releaseartist.ReleaseArtist;
import br.dev.wisentini.mysic.model.releasegenre.ReleaseGenre;
import br.dev.wisentini.mysic.model.releasetrack.ReleaseTrack;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.*;

@Entity(name = "Release")
@Table(name = "release", schema = "public")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(doNotUseGetters = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@TypeDef(name = "pgsql_enum", typeClass = PostgreSQLEnumType.class)
public class Release {
    @Id
    @Column(name = "release_id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include()
    private int id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    @Type(type = "pgsql_enum")
    private ReleaseType type;

    @Column(name = "year", nullable = false)
    private int year;

    @OneToMany(mappedBy = "release")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    Set<ReleaseArtist> releaseArtistSet;

    @OneToMany(mappedBy = "release")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    Set<ReleaseTrack> releaseTrackSet;

    @OneToMany(mappedBy = "release")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    Set<ReleaseGenre> releaseGenreSet;
}
