package br.com.fiap.squad3.restaurantfinder.external.jpa.entities.util;

import java.util.ArrayList;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import br.com.fiap.squad3.restaurantfinder.external.jpa.entities.RestauranteEntity;
import br.com.fiap.squad3.restaurantfinder.external.restapi.dtos.RestauranteFiltroDto;
import jakarta.persistence.criteria.Predicate;

public class RestauranteSpecification {

    public static Specification<RestauranteEntity> comFiltro(RestauranteFiltroDto filtro) {
        return (root, query, criteriaBuilder) -> {
            var predicates = new ArrayList<Predicate>();

            if (StringUtils.hasText(filtro.nome())) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("nomeFantasia")), "%" + filtro.nome().toLowerCase() + "%"));
            }
            if (StringUtils.hasText(filtro.culinaria())) {
                predicates.add(criteriaBuilder.equal(root.get("culinaria"), filtro.culinaria()));
            }
            if (StringUtils.hasText(filtro.estado())) {
                predicates.add(criteriaBuilder.equal(root.get("uf"), filtro.estado()));
            }
            if (StringUtils.hasText(filtro.cidade())) {
                predicates.add(criteriaBuilder.equal(root.get("cidade"), filtro.cidade()));
            }
            if (StringUtils.hasText(filtro.bairro())) {
                predicates.add(criteriaBuilder.equal(root.get("bairro"), filtro.bairro()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}