package br.dev.wisentini.mysic.repository.release;

import br.dev.wisentini.mysic.model.artist.Artist;
import br.dev.wisentini.mysic.model.genre.Genre;
import br.dev.wisentini.mysic.model.release.Release;
import br.dev.wisentini.mysic.model.track.Track;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReleaseRepository extends JpaRepository<Release, Integer>, JpaSpecificationExecutor<Release> {
    @Query(value = "SELECT t FROM Track AS t INNER JOIN ReleaseTrack AS rt ON t.id = rt.track.id WHERE rt.release.id = :releaseId")
    List<Track> findAllTracks(@Param("releaseId") int releaseId);

    @Query(value = "SELECT t FROM Track AS t INNER JOIN ReleaseTrack AS rt ON t.id = rt.track.id WHERE rt.release.id = :releaseId AND rt.track.id = :trackId")
    Track findTrack(@Param("releaseId") int releaseId, @Param("trackId") int trackId);

    @Query(value = "SELECT a FROM Artist AS a INNER JOIN ReleaseArtist AS ra ON a.id = ra.artist.id WHERE ra.release.id = :releaseId")
    List<Artist> findAllArtists(@Param("releaseId") int releaseId);

    @Query(value = "SELECT a FROM Artist AS a INNER JOIN ReleaseArtist AS ra ON a.id = ra.artist.id WHERE ra.release.id = :releaseId AND ra.artist.id = :artistId")
    Artist findArtist(@Param("releaseId") int releaseId, @Param("artistId") int artistId);

    @Query(value = "SELECT g FROM Genre AS g INNER JOIN ReleaseGenre AS rg ON g.id = rg.genre.id WHERE rg.release.id = :releaseId")
    List<Genre> findAllGenres(@Param("releaseId") int releaseId);

    @Query(value = "SELECT g FROM Genre AS g INNER JOIN ReleaseGenre AS rg ON g.id = rg.genre.id WHERE rg.release.id = :releaseId AND rg.genre.id = :genreId")
    Genre findGenre(@Param("releaseId") int releaseId, @Param("genreId") int genreId);
}
