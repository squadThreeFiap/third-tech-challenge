package br.com.fiap.squad3.restaurantfinder.interfaceadapters.converters.db;

import br.com.fiap.squad3.restaurantfinder.application.entities.Avaliacao;
import br.com.fiap.squad3.restaurantfinder.external.jpa.entities.AvaliacaoEntity;
import br.com.fiap.squad3.restaurantfinder.external.jpa.entities.ReservaEntity;

public class AvaliacaoEntityConverter implements EntityConverter<Avaliacao, AvaliacaoEntity> {

    @Override
    public AvaliacaoEntity toEntity(Avaliacao domainObj) {
        return new AvaliacaoEntity(
                domainObj.getId(),
                new ReservaEntity(domainObj.getIdReserva()),
                domainObj.getNota(),
                domainObj.getComentario()
        );
    }

    @Override
    public Avaliacao toDomainObj(AvaliacaoEntity avaliacaoEntity) {
        return new Avaliacao(
                avaliacaoEntity.getId(),
                avaliacaoEntity.getReservaEntity().getId(),
                avaliacaoEntity.getNota(),
                avaliacaoEntity.getComentario()
        );
    }
}
