package fr.sparkit.crm.restcontroller;

import fr.sparkit.crm.converter.StatusConvertor;
import fr.sparkit.crm.dto.StatusDto;
import fr.sparkit.crm.entities.Opportunity;
import fr.sparkit.crm.entities.Status;
import fr.sparkit.crm.services.IOpportinityService;
import fr.sparkit.crm.services.IStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/crm/status")
public class StatusController {

    private final IStatusService statusService;
    private IOpportinityService opportunityService;

    @Autowired
    public StatusController(IStatusService statusService, IOpportinityService opportinityService) {
        this.statusService = statusService;
        this.opportunityService = opportinityService;
    }

    @GetMapping()
    public void doGet(@Valid StatusDto dto) {

    }

    @GetMapping(value = "/statuts")
    public List<StatusDto> getAllStatuts() {
        return StatusConvertor.modelsToDtos(statusService.findAll());
    }

    @PostMapping()
    public ResponseEntity<StatusDto> saveStatus(@RequestBody StatusDto statusDto) {
        Status status = StatusConvertor.dtoToModel(statusDto);
        Status savedStatus = statusService.saveAndFlush(status);
        statusDto = StatusConvertor.modelToDto(savedStatus);
        return new ResponseEntity<>(statusDto, HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    public ResponseEntity<StatusDto> updateStatus(@RequestBody StatusDto statusDto) {
        return new ResponseEntity<>(statusService.updateStatus(statusDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStatus(@PathVariable Long id) {
        statusService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/changeState")
    public ResponseEntity<String> changeOpportinityState(@RequestParam int status, @RequestParam Long id, @RequestBody String opportunityLog) {
        Optional<Opportunity> opportunitie = opportunityService.findOne(id);
        opportunitie.get().setCurrentPositionPipe(status);
        opportunityService.saveAndFlush(opportunitie.get());
        return new ResponseEntity<>("status updated", HttpStatus.OK);
    }
}
