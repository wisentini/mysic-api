package br.dev.wisentini.mysic.service.release;

import br.dev.wisentini.mysic.dto.search.release.ReleaseSearchDto;
import br.dev.wisentini.mysic.model.artist.Artist;
import br.dev.wisentini.mysic.model.genre.Genre;
import br.dev.wisentini.mysic.model.release.Release;
import br.dev.wisentini.mysic.model.track.Track;
import br.dev.wisentini.mysic.repository.release.ReleaseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReleaseService {
    private final ReleaseRepository releaseRepository;

    @Autowired
    public ReleaseService(ReleaseRepository releaseRepository) {
        this.releaseRepository = releaseRepository;
    }

    public Release findById(int id) {
        Optional<Release> response = this.releaseRepository.findById(id);
        Release release = null;

        if (response.isPresent()) {
            release = response.get();
        }

        return release;
    }

    public List<Release> findAll(ReleaseSearchDto releaseSearchDto) {
        return this.releaseRepository.findAll(releaseSearchDto.buildSpecification());
    }

    public List<Track> findAllTracks(int id) {
        return this.releaseRepository.findAllTracks(id);
    }

    public Track findTrack(int releaseId, int trackId) {
        return this.releaseRepository.findTrack(releaseId, trackId);
    }

    public List<Artist> findAllArtists(int id) {
        return this.releaseRepository.findAllArtists(id);
    }

    public Artist findArtist(int releaseId, int artistId) {
        return this.releaseRepository.findArtist(releaseId, artistId);
    }

    public List<Genre> findAllGenres(int id) {
        return this.releaseRepository.findAllGenres(id);
    }

    public Genre findGenre(int releaseId, int genreId) {
        return this.releaseRepository.findGenre(releaseId, genreId);
    }

    public void update(int id, Release newRelease) {
        Optional<Release> response = this.releaseRepository.findById(id);

        if (response.isEmpty()) return;

        Release oldRelease = response.get();

        oldRelease.setTitle(newRelease.getTitle());
        oldRelease.setType(newRelease.getType());
        oldRelease.setYear(newRelease.getYear());

        this.releaseRepository.save(oldRelease);
    }

    public Release save(Release release) {
        return this.releaseRepository.save(release);
    }

    public void deleteById(int id) {
        this.releaseRepository.deleteById(id);
    }
}
