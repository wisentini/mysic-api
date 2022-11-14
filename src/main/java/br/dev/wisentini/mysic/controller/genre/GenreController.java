package br.dev.wisentini.mysic.controller.genre;

import br.dev.wisentini.mysic.dto.search.genre.GenreSearchDto;
import br.dev.wisentini.mysic.model.genre.Genre;
import br.dev.wisentini.mysic.model.release.Release;
import br.dev.wisentini.mysic.service.genre.GenreService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/genres")
public class GenreController {
    private final GenreService genreService;

    @Autowired
    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping()
    public List<Genre> findAll(@Valid GenreSearchDto genreSearchDto) {
        return this.genreService.findAll(genreSearchDto);
    }

    @GetMapping("/{genreId}")
    public Genre findById(@PathVariable("genreId") int genreId) {
        return this.genreService.findById(genreId);
    }

    @GetMapping("/{genreId}/releases")
    public List<Release> findAllReleases(@PathVariable("genreId") int genreId) {
        return this.genreService.findAllReleases(genreId);
    }

    @GetMapping("/{genreId}/releases/{releaseId}")
    public Release findRelease(@PathVariable("genreId") int genreId, @PathVariable("releaseId") int releaseId) {
        return this.genreService.findRelease(genreId, releaseId);
    }

    @PutMapping("/{genreId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable("genreId") int genreId, @RequestBody Genre genre) {
        this.genreService.update(genreId, genre);
    }

    @PostMapping()
    public Genre save(@RequestBody Genre genre) {
        return this.genreService.save(genre);
    }

    @DeleteMapping("/{genreId}")
    public void deleteById(@PathVariable("genreId") int genreId) {
        this.genreService.deleteById(genreId);
    }
}
