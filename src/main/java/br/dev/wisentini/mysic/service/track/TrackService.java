package br.dev.wisentini.mysic.service.track;

import br.dev.wisentini.mysic.dto.search.track.TrackSearchDto;
import br.dev.wisentini.mysic.model.artist.Artist;
import br.dev.wisentini.mysic.model.release.Release;
import br.dev.wisentini.mysic.model.track.Track;
import br.dev.wisentini.mysic.repository.track.TrackRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TrackService {
    private final TrackRepository trackRepository;

    @Autowired
    public TrackService(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    public Track findById(int id) {
        Optional<Track> response = this.trackRepository.findById(id);
        Track track = null;

        if (response.isPresent()) {
            track = response.get();
        }

        return track;
    }

    public List<Track> findAll(TrackSearchDto trackSearchDto) {
        return this.trackRepository.findAll(trackSearchDto.buildSpecification());
    }

    public List<Artist> findAllArtists(int id) {
        return this.trackRepository.findAllArtists(id);
    }

    public Artist findArtist(int trackId, int artistId) {
        return this.trackRepository.findArtist(trackId, artistId);
    }

    public List<Release> findAllReleases(int id) {
        return this.trackRepository.findAllReleases(id);
    }

    public Release findRelease(int trackId, int releaseId) {
        return this.trackRepository.findRelease(trackId, releaseId);
    }

    public void update(int id, Track newTrack) {
        Optional<Track> response = this.trackRepository.findById(id);

        if (response.isEmpty()) return;

        Track oldTrack = response.get();

        oldTrack.setTitle(newTrack.getTitle());
        oldTrack.setDurationMs(newTrack.getDurationMs());

        this.trackRepository.save(oldTrack);
    }

    public Track save(Track track) {
        return this.trackRepository.save(track);
    }

    public void deleteById(int id) {
        this.trackRepository.deleteById(id);
    }
}
