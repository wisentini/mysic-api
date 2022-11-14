package br.dev.wisentini.mysic.service.artist;

import br.dev.wisentini.mysic.dto.search.artist.ArtistSearchDto;
import br.dev.wisentini.mysic.model.artist.Artist;
import br.dev.wisentini.mysic.model.genre.Genre;
import br.dev.wisentini.mysic.model.release.Release;
import br.dev.wisentini.mysic.model.track.Track;
import br.dev.wisentini.mysic.repository.artist.ArtistRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArtistService {
    private final ArtistRepository artistRepository;

    @Autowired
    public ArtistService(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    public Artist findById(int id) {
        Optional<Artist> response = this.artistRepository.findById(id);
        Artist artist = null;

        if (response.isPresent()) {
            artist = response.get();
        }

        return artist;
    }

    public List<Artist> findAll(ArtistSearchDto artistSearchDto) {
        return this.artistRepository.findAll(artistSearchDto.buildSpecification());
    }

    public List<Track> findAllTracks(int id) {
        return this.artistRepository.findAllTracks(id);
    }

    public Track findTrack(int artistId, int trackId) {
        return this.artistRepository.findTrack(artistId, trackId);
    }

    public List<Release> findAllReleases(int id) {
        return this.artistRepository.findAllReleases(id);
    }

    public Release findRelease(int artistId, int releaseId) {
        return this.artistRepository.findRelease(artistId, releaseId);
    }

    public List<Genre> findAllGenres(int id) {
        return this.artistRepository.findAllGenres(id);
    }

    public Genre findGenre(int artistId, int genreId) {
        return this.artistRepository.findGenre(artistId, genreId);
    }

    public void update(int id, Artist newArtist) {
        Optional<Artist> response = this.artistRepository.findById(id);

        if (response.isEmpty()) return;

        Artist oldArtist = response.get();

        oldArtist.setName(newArtist.getName());

        this.artistRepository.save(oldArtist);
    }

    public Artist save(Artist artist) {
        return this.artistRepository.save(artist);
    }

    public void deleteById(int id) {
        this.artistRepository.deleteById(id);
    }
}
