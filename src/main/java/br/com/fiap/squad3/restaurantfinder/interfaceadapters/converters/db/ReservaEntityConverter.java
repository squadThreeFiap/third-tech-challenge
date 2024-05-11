package br.com.fiap.squad3.restaurantfinder.interfaceadapters.converters.db;

import br.com.fiap.squad3.restaurantfinder.application.entities.Reserva;
import br.com.fiap.squad3.restaurantfinder.external.jpa.entities.ReservaEntity;
import br.com.fiap.squad3.restaurantfinder.interfaceadapters.repositorygateways.ReservaRepositoryGateway;

public class ReservaEntityConverter implements EntityConverter<Reserva, ReservaEntity> {
    private final ReservaRepositoryGateway reservaRepositoryGateway;

    public ReservaEntityConverter(ReservaRepositoryGateway reservaRepositoryGateway) {
        this.reservaRepositoryGateway = reservaRepositoryGateway;
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
}
