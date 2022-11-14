package br.dev.wisentini.mysic.dto.search.track;

import br.dev.wisentini.mysic.model.track.Track;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrackSearchDto {
    private String title;
    private Long minDuration;
    private Long maxDuration;

    private Specification<Track> buildTitleSpecification() {
        return (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (this.title != null) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("title")), String.format("%%%s%%", this.title.toLowerCase(Locale.ROOT))));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    private Specification<Track> buildMinDurationSpecification() {
        return (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (this.minDuration != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("durationMs"), this.minDuration));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    private Specification<Track> buildMaxDurationSpecification() {
        return (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (this.maxDuration != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("durationMs"), this.maxDuration));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    public Specification<Track> buildSpecification() {
        Specification<Track> titleSpecification = this.buildTitleSpecification();
        Specification<Track> minDurationSpecification = this.buildMinDurationSpecification();
        Specification<Track> maxDurationSpecification = this.buildMaxDurationSpecification();

        return titleSpecification.and(minDurationSpecification).and(maxDurationSpecification);
    }
}
