package fr.sparkit.crm.restcontroller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.sparkit.crm.converter.ObjectifConverter;
import fr.sparkit.crm.converter.ObjectifStatusConverter;
import fr.sparkit.crm.dto.ObjectifStatusDto;
import fr.sparkit.crm.dto.ObjectiveStatusIndexDto;
import fr.sparkit.crm.entities.Employee;
import fr.sparkit.crm.entities.Objectif;
import fr.sparkit.crm.entities.ObjectifStatus;
import fr.sparkit.crm.entities.UserCrm;
import fr.sparkit.crm.services.IEmployeeService;
import fr.sparkit.crm.services.IObjectifService;
import fr.sparkit.crm.services.IObjectifStatusService;
import fr.sparkit.crm.services.IStatusService;
import fr.sparkit.crm.services.IUserCrmService;

@RestController
@CrossOrigin(value = { "*" })
@RequestMapping(value = { "/api/crm/objectif-management/v1" })
public class ObjectifController {

    private IObjectifService objectifService;
    private IEmployeeService employeeService;
    private IUserCrmService userCrmService;
    private IStatusService statusService;
    private IObjectifStatusService objectifStatusService;

    public ObjectifController(IObjectifService objectifService, ModelMapper modelMapper,
            IEmployeeService employeeService, IUserCrmService userCrmService, IStatusService statusService,
            IObjectifStatusService objectifStatusService) {
        this.objectifService = objectifService;
        this.employeeService = employeeService;
        this.userCrmService = userCrmService;
        this.statusService = statusService;
        this.objectifStatusService = objectifStatusService;
    }

    @GetMapping(value = { "/objectifs" })
    public ResponseEntity<List<ObjectifStatusDto>> getAllObjectifs() {
        List<ObjectifStatus> objectifStatuses = objectifStatusService.findAll();
        List<Objectif> objectifs = objectifService.findAll();

        List<ObjectifStatusDto> allObjStatusDto = new ArrayList<>();
        for (Objectif objectif : objectifs) {
            List<ObjectifStatus> listStatusByObjectif = objectifStatuses.stream()
                    .filter(f -> f.getObjectif().getId() == objectif.getId()).collect(Collectors.toList());
            allObjStatusDto.add(ObjectifStatusConverter.modelToDto(objectif, listStatusByObjectif));

        }
        return new ResponseEntity<>(allObjStatusDto, HttpStatus.OK);

    }

    @GetMapping(value = { "/objectifs/{id}" })
    public ResponseEntity<ObjectifStatusDto> getObjectifById(@PathVariable Long id) {
        Objectif objectif = objectifService.findOne(id).get();
        List<ObjectifStatus> allStatusByObjectif = objectifStatusService.findByObjectifId(objectif.getId());
        ObjectifStatusDto objectifStatusDto = ObjectifStatusConverter.modelToDto(objectif, allStatusByObjectif);
        return new ResponseEntity<>(objectifStatusDto, HttpStatus.OK);
    }

    @PostMapping(value = { "/objectifs" })
    public ResponseEntity<ObjectifStatusDto> saveObjectif(@RequestBody ObjectifStatusDto objStatusDto) {

        List<Employee> emplyees = objStatusDto.getEmployeesId().stream().map(e -> checkExistingEmployeeOrCreateNew(e))
                .collect(Collectors.toList());
        List<UserCrm> users = objStatusDto.getResponsablesUsersId().stream().map(u -> checkExistingUserOrCreateNew(u))
                .collect(Collectors.toList());
        Objectif objectif = ObjectifConverter.dtoToModel(new Objectif(), objStatusDto, emplyees, users);
        Objectif savedObjectif = objectifService.saveAndFlush(objectif);
        objStatusDto.getObjectiveStatusIndexDto().forEach(element -> {
            ObjectifStatus objStatus = new ObjectifStatus();
            objStatus.setObjectif(savedObjectif);
            objStatus.setStatus(statusService.findOne(element.getId()).get());
            objStatus.setPositionInPipe(element.getPositionInPipe());
            objectifStatusService.saveAndFlush(objStatus);
        });
        List<ObjectifStatus> allStatusByObj = objectifStatusService.findByObjectifId(savedObjectif.getId());
        ObjectifStatusDto returnedDto = ObjectifStatusConverter.modelToDto(savedObjectif, allStatusByObj);
        return new ResponseEntity<>(returnedDto, HttpStatus.CREATED);
    }

    @PutMapping(value = { "/objectifs/{id}" })
    public ResponseEntity<ObjectifStatusDto> updateObjectif(@RequestBody ObjectifStatusDto objStatusDto,
            @PathVariable Long id) {
        Objectif objectif = objectifService.findOne(id).get();
        List<Employee> emplyees = objStatusDto.getEmployeesId().stream().map(e -> checkExistingEmployeeOrCreateNew(e))
                .collect(Collectors.toList());
        List<UserCrm> users = objStatusDto.getResponsablesUsersId().stream().map(u -> checkExistingUserOrCreateNew(u))
                .collect(Collectors.toList());
        objectif = ObjectifConverter.dtoToModel(objectif, objStatusDto, emplyees, users);
        objectif = objectifService.saveAndFlush(objectif);

        saveObjectifStatus(objStatusDto, objectif);

        List<ObjectifStatus> allStatusByObj = objectifStatusService.findByObjectifId(objectif.getId());
        ObjectifStatusDto returnedDto = ObjectifStatusConverter.modelToDto(objectif, allStatusByObj);
        return new ResponseEntity<>(returnedDto, HttpStatus.OK);

    }

    private void saveObjectifStatus(ObjectifStatusDto objStatusDto, Objectif savedObjectif) {
        List<ObjectifStatus> objStatutes = objectifStatusService.findByObjectifId(objStatusDto.getId());
        for (ObjectifStatus objStatus : objStatutes) {

            for (ObjectiveStatusIndexDto statusDto : objStatusDto.getObjectiveStatusIndexDto()) {
                if (objStatus.getStatus().getId() == statusDto.getId()) {
                    objStatus.setObjectif(savedObjectif);
                    objStatus.setStatus(statusService.findOne(statusDto.getId()).get());
                    objStatus.setPositionInPipe(statusDto.getPositionInPipe());
                    objectifStatusService.saveAndFlush(objStatus);
                }
            }
        }
    }

    private Employee checkExistingEmployeeOrCreateNew(long id) {
        Optional<Employee> employee = employeeService.findOne(id);
        if (employee.isPresent()) {
            return employee.get();
        } else {
            Employee newEmployee = new Employee(id);
            return employeeService.saveAndFlush(newEmployee);
        }
    }

    private UserCrm checkExistingUserOrCreateNew(Long id) {
        Optional<UserCrm> user = userCrmService.findOne(id);
        if (user.isPresent()) {
            return user.get();
        } else {
            UserCrm newUser = new UserCrm(id);
            return userCrmService.saveAndFlush(newUser);
        }
    }
}
