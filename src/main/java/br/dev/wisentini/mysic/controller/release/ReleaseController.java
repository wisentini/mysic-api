package br.dev.wisentini.mysic.controller.release;

import br.dev.wisentini.mysic.dto.search.release.ReleaseSearchDto;
import br.dev.wisentini.mysic.model.artist.Artist;
import br.dev.wisentini.mysic.model.genre.Genre;
import br.dev.wisentini.mysic.model.release.Release;
import br.dev.wisentini.mysic.model.track.Track;
import br.dev.wisentini.mysic.service.release.ReleaseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/releases")
public class ReleaseController {
    private final ReleaseService releaseService;

    @Autowired
    public ReleaseController(ReleaseService releaseService) {
        this.releaseService = releaseService;
    }

    @GetMapping()
    public List<Release> findAll(@Valid ReleaseSearchDto releaseSearchDto) {
        return this.releaseService.findAll(releaseSearchDto);
    }

    @GetMapping("/{releaseId}")
    public Release findById(@PathVariable("releaseId") int releaseId) {
        return this.releaseService.findById(releaseId);
    }

    @GetMapping("/{releaseId}/tracks")
    public List<Track> findAllTracks(@PathVariable("releaseId") int releaseId) {
        return this.releaseService.findAllTracks(releaseId);
    }

    @GetMapping("/{releaseId}/tracks/{trackId}")
    public Track findTrack(@PathVariable("releaseId") int releaseId, @PathVariable("trackId") int trackId) {
        return this.releaseService.findTrack(releaseId, trackId);
    }

    @GetMapping("/{releaseId}/artists")
    public List<Artist> findAllArtists(@PathVariable("releaseId") int releaseId) {
        return this.releaseService.findAllArtists(releaseId);
    }

    @GetMapping("/{releaseId}/artists/{artistsId}")
    public Artist findArtist(@PathVariable("releaseId") int releaseId, @PathVariable("artistsId") int artistsId) {
        return this.releaseService.findArtist(releaseId, artistsId);
    }

    @GetMapping("/{releaseId}/genres")
    public List<Genre> findAllGenres(@PathVariable("releaseId") int releaseId) {
        return this.releaseService.findAllGenres(releaseId);
    }

    @GetMapping("/{releaseId}/genres/{genreId}")
    public Genre findGenre(@PathVariable("releaseId") int releaseId, @PathVariable("genreId") int genreId) {
        return this.releaseService.findGenre(releaseId, genreId);
    }

    @PutMapping("/{releaseId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable("releaseId") int releaseId, @RequestBody Release release) {
        this.releaseService.update(releaseId, release);
    }

    @PostMapping()
    public Release save(@RequestBody Release release) {
        return this.releaseService.save(release);
    }

    @DeleteMapping("/{releaseId}")
    public void deleteById(@PathVariable("releaseId") int releaseId) {
        this.releaseService.deleteById(releaseId);
    }
}
