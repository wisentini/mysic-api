package br.dev.wisentini.mysic.repository.track;

import br.dev.wisentini.mysic.model.artist.Artist;
import br.dev.wisentini.mysic.model.release.Release;
import br.dev.wisentini.mysic.model.track.Track;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TrackRepository extends JpaRepository<Track, Integer>, JpaSpecificationExecutor<Track> {
    @Query(value = "SELECT a FROM Artist AS a INNER JOIN TrackArtist AS ta ON a.id = ta.artist.id WHERE ta.track.id = :trackId")
    List<Artist> findAllArtists(@Param("trackId") int trackId);

    @Query(value = "SELECT a FROM Artist AS a INNER JOIN TrackArtist AS ta ON a.id = ta.artist.id WHERE ta.track.id = :trackId AND ta.artist.id = :artistId")
    Artist findArtist(@Param("trackId") int trackId, @Param("artistId") int artistId);

    @Query(value = "SELECT r FROM Release AS r INNER JOIN ReleaseTrack AS rt ON r.id = rt.release.id WHERE rt.track.id = :trackId")
    List<Release> findAllReleases(@Param("trackId") int trackId);

    @Query(value = "SELECT r FROM Release AS r INNER JOIN ReleaseTrack AS rt ON r.id = rt.release.id WHERE rt.track.id = :trackId AND rt.release.id = :releaseId")
    Release findRelease(@Param("trackId") int trackId, @Param("releaseId") int releaseId);
}
