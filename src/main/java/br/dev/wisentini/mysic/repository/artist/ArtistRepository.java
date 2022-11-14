package br.dev.wisentini.mysic.repository.artist;

import br.dev.wisentini.mysic.model.artist.Artist;
import br.dev.wisentini.mysic.model.genre.Genre;
import br.dev.wisentini.mysic.model.release.Release;
import br.dev.wisentini.mysic.model.track.Track;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArtistRepository extends JpaRepository<Artist, Integer>, JpaSpecificationExecutor<Artist> {
    @Query(value = "SELECT t FROM Track AS t INNER JOIN TrackArtist AS ta ON t.id = ta.track.id WHERE ta.artist.id = :artistId")
    List<Track> findAllTracks(@Param("artistId") int artistId);

    @Query(value = "SELECT t FROM Track AS t INNER JOIN TrackArtist AS ta ON t.id = ta.track.id WHERE ta.artist.id = :artistId AND ta.track.id = :trackId")
    Track findTrack(@Param("artistId") int artistId, @Param("trackId") int trackId);

    @Query(value = "SELECT r FROM Release AS r INNER JOIN ReleaseArtist AS ra ON r.id = ra.release.id WHERE ra.artist.id = :artistId")
    List<Release> findAllReleases(@Param("artistId") int artistId);

    @Query(value = "SELECT r FROM Release AS r INNER JOIN ReleaseArtist AS ra ON r.id = ra.release.id WHERE ra.artist.id = :artistId AND ra.release.id = :releaseId")
    Release findRelease(@Param("artistId") int artistId, @Param("releaseId") int releaseId);

    @Query(value = "SELECT DISTINCT g FROM TrackArtist AS ta INNER JOIN ReleaseTrack AS rt ON ta.track.id = rt.track.id INNER JOIN ReleaseGenre AS rg ON rt.release.id = rg.release.id INNER JOIN Genre AS g ON rg.genre.id = g.id WHERE ta.artist.id = :artistId")
    List<Genre> findAllGenres(@Param("artistId") int artistId);

    @Query(value = "SELECT DISTINCT g FROM TrackArtist AS ta INNER JOIN ReleaseTrack AS rt ON ta.track.id = rt.track.id INNER JOIN ReleaseGenre AS rg ON rt.release.id = rg.release.id INNER JOIN Genre AS g ON rg.genre.id = g.id WHERE ta.artist.id = :artistId AND g.id = :genreId")
    Genre findGenre(@Param("artistId") int artistId, @Param("genreId") int genreId);
}
