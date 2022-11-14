package br.dev.wisentini.mysic.controller.track;

import br.dev.wisentini.mysic.dto.search.track.TrackSearchDto;
import br.dev.wisentini.mysic.model.artist.Artist;
import br.dev.wisentini.mysic.model.release.Release;
import br.dev.wisentini.mysic.model.track.Track;
import br.dev.wisentini.mysic.service.track.TrackService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/tracks")
public class TrackController {
    private final TrackService trackService;

    @Autowired
    public TrackController(TrackService trackService) {
        this.trackService = trackService;
    }

    @GetMapping()
    public List<Track> findAll(@Valid TrackSearchDto trackSearchDto) {
        return this.trackService.findAll(trackSearchDto);
    }

    @GetMapping("/{trackId}")
    public Track findById(@PathVariable("trackId") int trackId) {
        return this.trackService.findById(trackId);
    }

    @GetMapping("/{trackId}/artists")
    public List<Artist> findAllArtists(@PathVariable("trackId") int trackId) {
        return this.trackService.findAllArtists(trackId);
    }

    @GetMapping("/{trackId}/artists/{artistId}")
    public Artist findArtist(@PathVariable("trackId") int trackId, @PathVariable("artistId") int artistId) {
        return this.trackService.findArtist(trackId, artistId);
    }

    @GetMapping("/{trackId}/releases")
    public List<Release> findAllReleases(@PathVariable("trackId") int trackId) {
        return this.trackService.findAllReleases(trackId);
    }

    @GetMapping("/{trackId}/releases/{releaseId}")
    public Release findRelease(@PathVariable("trackId") int trackId, @PathVariable("releaseId") int releaseId) {
        return this.trackService.findRelease(trackId, releaseId);
    }

    @PutMapping("/{trackId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable("trackId") int trackId, @RequestBody Track track) {
        this.trackService.update(trackId, track);
    }

    @PostMapping()
    public Track save(@RequestBody Track track) {
        return this.trackService.save(track);
    }

    @DeleteMapping("/{trackId}")
    public void deleteById(@PathVariable("trackId") int trackId) {
        this.trackService.deleteById(trackId);
    }
}
