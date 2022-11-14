package br.dev.wisentini.mysic.controller.artist;

import br.dev.wisentini.mysic.dto.search.artist.ArtistSearchDto;
import br.dev.wisentini.mysic.model.artist.Artist;
import br.dev.wisentini.mysic.model.genre.Genre;
import br.dev.wisentini.mysic.model.release.Release;
import br.dev.wisentini.mysic.model.track.Track;
import br.dev.wisentini.mysic.service.artist.ArtistService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/artists")
public class ArtistController {
    private final ArtistService artistService;

    @Autowired
    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @GetMapping()
    public List<Artist> findAll(@Valid ArtistSearchDto artistSearchDto) {
        return this.artistService.findAll(artistSearchDto);
    }

    @GetMapping("/{artistId}")
    public Artist findById(@PathVariable("artistId") int artistId) {
        return this.artistService.findById(artistId);
    }

    @GetMapping("/{artistId}/tracks")
    public List<Track> findAllTracks(@PathVariable("artistId") int artistId) {
        return this.artistService.findAllTracks(artistId);
    }

    @GetMapping("/{artistId}/tracks/{trackId}")
    public Track findTrack(@PathVariable("artistId") int artistId, @PathVariable("trackId") int trackId) {
        return this.artistService.findTrack(artistId, trackId);
    }

    @GetMapping("/{artistId}/releases")
    public List<Release> findAllReleases(@PathVariable("artistId") int artistId) {
        return this.artistService.findAllReleases(artistId);
    }

    @GetMapping("/{artistId}/releases/{releaseId}")
    public Release findRelease(@PathVariable("artistId") int artistId, @PathVariable("releaseId") int releaseId) {
        return  this.artistService.findRelease(artistId, releaseId);
    }

    @GetMapping("/{artistId}/genres")
    public List<Genre> findAllGenres(@PathVariable("artistId") int artistId) {
        return this.artistService.findAllGenres(artistId);
    }

    @GetMapping("/{artistId}/genres/{genreId}")
    public Genre findGenre(@PathVariable("artistId") int artistId, @PathVariable("genreId") int genreId) {
        return this.artistService.findGenre(artistId, genreId);
    }

    @PutMapping("/{artistId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable("artistId") int artistId, @RequestBody Artist artist) {
        this.artistService.update(artistId, artist);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Artist save(@RequestBody Artist artist) {
        return this.artistService.save(artist);
    }

    @DeleteMapping("/{artistId}")
    public void deleteById(@PathVariable("artistId") int artistId) {
        this.artistService.deleteById(artistId);
    }
}
