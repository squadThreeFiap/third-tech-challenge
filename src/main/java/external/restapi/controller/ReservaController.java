package external.restapi.controller;

import br.com.fiap.squad3.restaurantfinder.model.dtos.ReservaDto;
import br.com.fiap.squad3.restaurantfinder.service.impl.ReservaServiceImpl;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reserva")
public class ReservaController {
    private final ReservaServiceImpl reservaService;

    public ReservaController(ReservaServiceImpl reservaService) {
        this.reservaService = reservaService;
    }

    @PostMapping(
            produces = {"application/json", "application/xml"},
            consumes = {"application/json", "application/xml"}
    )
    @Transactional
    public ResponseEntity<ReservaDto> save(@Valid @RequestBody ReservaDto reservaDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(reservaService.save(reservaDto));
    }
}