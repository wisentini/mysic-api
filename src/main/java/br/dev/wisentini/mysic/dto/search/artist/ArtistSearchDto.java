package br.dev.wisentini.mysic.dto.search.artist;

import br.dev.wisentini.mysic.model.artist.Artist;

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
public class ArtistSearchDto {
    private String name;

    public Specification<Artist> buildSpecification() {
        return (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (this.name != null) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), String.format("%%%s%%", this.name.toLowerCase(Locale.ROOT))));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
