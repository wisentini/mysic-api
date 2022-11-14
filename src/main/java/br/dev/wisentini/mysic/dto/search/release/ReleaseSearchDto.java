package br.dev.wisentini.mysic.dto.search.release;

import br.dev.wisentini.mysic.model.release.Release;
import br.dev.wisentini.mysic.model.release.ReleaseType;

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
public class ReleaseSearchDto {
    private String title;
    private ReleaseType type;
    private Integer minYear;
    private Integer maxYear;
    private Integer year;

    private Specification<Release> buildTitleSpecification() {
        return (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (this.title != null) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("title")), String.format("%%%s%%", this.title.toLowerCase(Locale.ROOT))));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    private Specification<Release> buildTypeSpecification() {
        return (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (this.type != null) {
                predicates.add(criteriaBuilder.equal(root.get("type"), this.type));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    private Specification<Release> buildMinYearSpecification() {
        return (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (this.minYear != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("year"), this.minYear));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    private Specification<Release> buildMaxYearSpecification() {
        return (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (this.maxYear != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("year"), this.maxYear));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    private Specification<Release> buildYearSpecification() {
        return (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (this.year != null) {
                predicates.add(criteriaBuilder.equal(root.get("year"), this.year));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    public Specification<Release> buildSpecification() {
        return this.buildTitleSpecification()
            .and(this.buildTypeSpecification())
            .and(this.buildMinYearSpecification())
            .and(this.buildMaxYearSpecification())
            .and(this.buildYearSpecification());
    }
}
