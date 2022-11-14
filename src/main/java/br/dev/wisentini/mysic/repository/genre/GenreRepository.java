package br.dev.wisentini.mysic.repository.genre;

import br.dev.wisentini.mysic.model.genre.Genre;
import br.dev.wisentini.mysic.model.release.Release;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GenreRepository extends JpaRepository<Genre, Integer>, JpaSpecificationExecutor<Genre> {
    @Query(value = "SELECT r FROM Release AS r INNER JOIN ReleaseGenre AS rg ON r.id = rg.release.id WHERE rg.genre.id = :genreId")
    List<Release> findAllReleases(@Param("genreId") int genreId);

    @Query(value = "SELECT r FROM Release AS r INNER JOIN ReleaseGenre AS rg ON r.id = rg.release.id WHERE rg.genre.id = :genreId AND rg.release.id = :releaseId")
    Release findRelease(@Param("genreId") int genreId, @Param("releaseId") int releaseId);
}
