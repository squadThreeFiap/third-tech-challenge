package br.com.fiap.squad3.restaurantfinder.interfaceadapters.repositorygateways;

import br.com.fiap.squad3.restaurantfinder.application.entities.Avaliacao;
import br.com.fiap.squad3.restaurantfinder.application.gateways.AvaliacaoGateway;
import br.com.fiap.squad3.restaurantfinder.external.jpa.entities.AvaliacaoEntity;
import br.com.fiap.squad3.restaurantfinder.external.jpa.entities.ReservaEntity;
import br.com.fiap.squad3.restaurantfinder.external.jpa.repository.AvaliacaoRepository;
import br.com.fiap.squad3.restaurantfinder.external.jpa.repository.ReservaRepository;
import br.com.fiap.squad3.restaurantfinder.interfaceadapters.converters.db.AvaliacaoEntityConverter;

import java.util.Optional;

public class AvaliacaoRepositoryGateway implements AvaliacaoGateway {
    private final ReservaRepository reservaRepository;
    private final AvaliacaoRepository avaliacaoRepository;

    private final AvaliacaoEntityConverter avaliacaoEntityConverter;

    public AvaliacaoRepositoryGateway(
            ReservaRepository reservaRepository,
            AvaliacaoRepository avaliacaoRepository,
            AvaliacaoEntityConverter avaliacaoEntityConverter
    ) {
        this.reservaRepository = reservaRepository;
        this.avaliacaoRepository = avaliacaoRepository;
        this.avaliacaoEntityConverter = avaliacaoEntityConverter;
    }

    @Override
    public Avaliacao cadastrar(Avaliacao avaliacao) {
        AvaliacaoEntity avaliacaoEntity = avaliacaoEntityConverter.toEntity(avaliacao);

        ReservaEntity reservaEntity = reservaRepository.getReferenceById(avaliacao.getIdReserva());
        avaliacaoEntity.setReservaEntity(reservaEntity);

        AvaliacaoEntity avaliacaoCadastrada = avaliacaoRepository.save(avaliacaoEntity);

        return avaliacaoEntityConverter.toDomainObj(avaliacaoCadastrada);
    }

    @Override
    public Avaliacao buscarPeloIdDaReserva(Long idReserva) {
        Optional<AvaliacaoEntity> avaliacaoEntity = avaliacaoRepository.findByReservaEntityId(idReserva);

        if (avaliacaoEntity.isPresent()) {
            return avaliacaoEntityConverter.toDomainObj(avaliacaoEntity.get());
        }

        return null;
    }
}
