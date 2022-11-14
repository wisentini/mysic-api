package br.dev.wisentini.mysic.dto.search.genre;

import br.dev.wisentini.mysic.model.genre.Genre;

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
public class GenreSearchDto {
    private String name;

    public Specification<Genre> buildSpecification() {
        return (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (this.name != null) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), String.format("%%%s%%", this.name.toLowerCase(Locale.ROOT))));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
