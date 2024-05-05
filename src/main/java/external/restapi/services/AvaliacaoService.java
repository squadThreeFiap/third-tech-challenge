package external.restapi.services;

import external.restapi.dtos.AvaliacaoDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AvaliacaoService {
    AvaliacaoDto save(AvaliacaoDto avaliacaoDto);

    AvaliacaoDto update(Long id, AvaliacaoDto avaliacaoDto);

    void delete(Long id);

    AvaliacaoDto findById(Long id);

    Page<AvaliacaoDto> findAll(Pageable pageable);
}