package br.com.fiap.squad3.restaurantfinder.interfaceadapters.converters.db;

import br.com.fiap.squad3.restaurantfinder.application.entities.Reserva;
import br.com.fiap.squad3.restaurantfinder.application.entities.ReservaDetalhada;
import br.com.fiap.squad3.restaurantfinder.external.jpa.entities.ReservaEntity;

public class ReservaEntityConverter implements EntityConverter<Reserva, ReservaEntity> {
    private final UsuarioEntityConverter usuarioEntityConverter;

    public ReservaEntityConverter(UsuarioEntityConverter usuarioEntityConverter) {
        this.usuarioEntityConverter = usuarioEntityConverter;
    }

    @Override
    public ReservaEntity toEntity(Reserva domainObj) {
        ReservaEntity reservaEntity = new ReservaEntity();
        reservaEntity.setId(domainObj.getId());

//        UsuarioEntity usuarioEntity = new UsuarioEntity();
//        usuarioEntity.setId(domainObj.getIdUsuario());
//        reservaEntity.setUsuarioEntity(usuarioEntity);

//        RestauranteEntity restauranteEntity = new RestauranteEntity();
//        restauranteEntity.setId(domainObj.getIdRestaurante());
//        reservaEntity.setRestauranteEntity(restauranteEntity);

        reservaEntity.setQuantidadePessoas(domainObj.getQuantidadePessoas());
        reservaEntity.setDataHoraInicio(domainObj.getDataHoraInicio());
        reservaEntity.setDataHoraFim(domainObj.getDataHoraFim());
        reservaEntity.setStatus(domainObj.getStatus());

        return reservaEntity;
    }

    @Override
    public Reserva toDomainObj(ReservaEntity reservaEntity) {
        return new Reserva(
                reservaEntity.getId(),
                reservaEntity.getUsuarioEntity().getId(),
                reservaEntity.getRestauranteEntity().getId(),
                reservaEntity.getQuantidadePessoas(),
                reservaEntity.getDataHoraInicio(),
                reservaEntity.getDataHoraFim(),
                reservaEntity.getStatus()
        );
    }

    public ReservaDetalhada toDetailedDomainObj(ReservaEntity reservaEntity) {
        return new ReservaDetalhada(
                reservaEntity.getId(),
                usuarioEntityConverter.toDomainObj(reservaEntity.getUsuarioEntity()),
                reservaEntity.getQuantidadePessoas(),
                reservaEntity.getDataHoraInicio(),
                reservaEntity.getDataHoraFim(),
                reservaEntity.getStatus()
        );
    }
}
