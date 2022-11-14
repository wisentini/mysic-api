package br.dev.wisentini.mysic.service.genre;

import br.dev.wisentini.mysic.dto.search.genre.GenreSearchDto;
import br.dev.wisentini.mysic.model.genre.Genre;
import br.dev.wisentini.mysic.model.release.Release;
import br.dev.wisentini.mysic.repository.genre.GenreRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenreService {
    private final GenreRepository genreRepository;

    @Autowired
    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public Genre findById(int id) {
        Optional<Genre> response = this.genreRepository.findById(id);
        Genre genre = null;

        if (response.isPresent()) {
            genre = response.get();
        }

        return genre;
    }

    public List<Genre> findAll(GenreSearchDto genreSearchDto) {
        return this.genreRepository.findAll(genreSearchDto.buildSpecification());
    }

    public List<Release> findAllReleases(int id) {
        return this.genreRepository.findAllReleases(id);
    }

    public Release findRelease(int genreId, int releaseId) {
        return this.genreRepository.findRelease(genreId, releaseId);
    }

    public void update(int id, Genre newGenre) {
        Optional<Genre> response = this.genreRepository.findById(id);

        if (response.isEmpty()) return;

        Genre oldGenre = response.get();

        oldGenre.setName(newGenre.getName());

        this.genreRepository.save(oldGenre);
    }

    public Genre save(Genre genre) {
        return this.genreRepository.save(genre);
    }

    public void deleteById(int id) {
        this.genreRepository.deleteById(id);
    }
}
