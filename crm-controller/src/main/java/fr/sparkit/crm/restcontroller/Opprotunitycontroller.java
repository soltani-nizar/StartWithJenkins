package fr.sparkit.crm.restcontroller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
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

import fr.sparkit.crm.converter.OpportunityConverter;
import fr.sparkit.crm.dto.OpportunityDataDto;
import fr.sparkit.crm.dto.OpportunityDto;
import fr.sparkit.crm.entities.Employee;
import fr.sparkit.crm.entities.Objectif;
import fr.sparkit.crm.entities.ObjectifStatus;
import fr.sparkit.crm.entities.Opportunity;
import fr.sparkit.crm.entities.UserCrm;
import fr.sparkit.crm.enumuration.ObjectifType;
import fr.sparkit.crm.services.IEmployeeService;
import fr.sparkit.crm.services.IObjectifService;
import fr.sparkit.crm.services.IObjectifStatusService;
import fr.sparkit.crm.services.IOpportinityService;
import fr.sparkit.crm.services.IUserCrmService;

@RestController
@CrossOrigin(value = { "*" })
@RequestMapping(value = { "/api/crm/opportunity-management/v1" })
public class Opprotunitycontroller {

    private IOpportinityService opportunityService;
    private IObjectifStatusService objectifStatusService;
    private IUserCrmService userCrmService;
    private IObjectifService objectifService;
    private IEmployeeService employeeService;

    public Opprotunitycontroller(IOpportinityService opportinityService, ModelMapper modelMapper,
            IEmployeeService employeeService, IUserCrmService userCrmService,
            IObjectifStatusService objectifStatusService, IObjectifService objectifService) {
        this.opportunityService = opportinityService;
        this.objectifStatusService = objectifStatusService;
        this.userCrmService = userCrmService;
        this.objectifService = objectifService;
        this.employeeService = employeeService;

    }

    @GetMapping(value = { "/opportunities" })
    public ResponseEntity<OpportunityDataDto> getAllObjectifOpportunities() {
        List<Opportunity> opportunities = opportunityService.findByObjectifTypeAndResponsableUserId(
                ObjectifType.STAFFING.getValue(), Long.valueOf(ObjectifType.STAFFINGID.getValue()));
        List<Employee> employees = objectifService.findOne(Long.valueOf(ObjectifType.STAFFINGID.getValue())).get()
                .getEmployees();

        List<Long> employeesId = employees.stream().map(employee -> employee.getId()).collect(Collectors.toList());

        List<ObjectifStatus> objectifStatus = objectifStatusService
                .findByObjectifId(Long.valueOf(ObjectifType.STAFFINGID.getValue()));

        Map<String, String> colorMap = new HashMap<>();
        Map<String, Integer> positionMap = new HashMap<>();
        if (CollectionUtils.isNotEmpty(objectifStatus)) {

            for (ObjectifStatus objectif : objectifStatus) {
                colorMap.put(objectif.getStatus().getTitle(), objectif.getStatus().getColor());
                positionMap.put(objectif.getStatus().getTitle(), objectif.getPositionInPipe());
            }
        }

        Map<String, List<ObjectifStatus>> filteredByStatus = objectifStatus.stream()
                .collect(Collectors.groupingBy(g -> g.getStatus().getTitle()));
        Map<String, List<OpportunityDto>> filteredOpportunities = new HashMap<>();

        if (CollectionUtils.isNotEmpty(objectifStatus)) {
            for (Entry<String, List<ObjectifStatus>> entry : filteredByStatus.entrySet()) {
                List<Opportunity> opportunitiesByStatus = getOppoprtunitiesByStatus(opportunities,
                        entry.getValue().get(0).getPositionInPipe());
                filteredOpportunities.put(entry.getKey(),
                        OpportunityConverter.ListModelToListDto(opportunitiesByStatus));
            }
        }

        Map<String, List<Long>> objectifEmployeesId = new HashMap<>();
        objectifEmployeesId.put(ObjectifType.STAFFING.getValue(), employeesId);

        OpportunityDataDto opportunityDataDto = new OpportunityDataDto(colorMap, filteredOpportunities, positionMap,
                objectifEmployeesId);
        return new ResponseEntity<>(opportunityDataDto, HttpStatus.OK);
    }

    private List<Opportunity> getOppoprtunitiesByStatus(List<Opportunity> allOpportunities, int positionInPipe) {
        return allOpportunities.stream().filter(op -> op.getCurrentPositionPipe() == positionInPipe)
                .collect(Collectors.toList());

    }

    @PostMapping(value = "/opportunities")
    public ResponseEntity<OpportunityDto> saveOpportunity(@RequestBody OpportunityDto opportunityDto) {
        Opportunity opportunity = new Opportunity();
        UserCrm responsableUser = userCrmService.findOne(opportunityDto.getResponsableUserId()).get();
        Objectif objectif = objectifService.findOne(opportunityDto.getObjectifId()).get();
        Employee employee = employeeService.findOne(opportunityDto.getEmployeeId()).get();
        opportunity = OpportunityConverter.dtoToModel(opportunity, opportunityDto, responsableUser, objectif, employee);
        opportunity = opportunityService.saveAndFlush(opportunity);
        OpportunityDto returnedDto = OpportunityConverter.modelToDto(opportunity);
        return new ResponseEntity<>(returnedDto, HttpStatus.CREATED);
    }

    @GetMapping(value = { "/opportunities/{id}/" })
    public ResponseEntity<OpportunityDto> getOpportinity(@PathVariable Long id) {
        Opportunity opportunity = opportunityService.findOne(id).get();
        OpportunityDto opportunityDto = OpportunityConverter.modelToDto((opportunity));
        return new ResponseEntity<>(opportunityDto, HttpStatus.OK);
    }

    @PutMapping(value = "/opportunities/{id}")
    public ResponseEntity<OpportunityDto> updateOpportinity(@PathVariable Long id,
            @RequestBody OpportunityDto opportunityDto) {
        Opportunity opportunity = opportunityService.findOne(id).get();
        UserCrm responsableUser = userCrmService.findOne(opportunityDto.getResponsableUserId()).get();
        Objectif objectif = objectifService.findOne(opportunityDto.getObjectifId()).get();
        Employee employee = employeeService.findOne(opportunityDto.getEmployeeId()).get();
        opportunity = OpportunityConverter.dtoToModel(opportunity, opportunityDto, responsableUser, objectif, employee);
        opportunity = opportunityService.saveAndFlush(opportunity);
        OpportunityDto returnedDto = OpportunityConverter.modelToDto(opportunity);
        return new ResponseEntity<>(returnedDto, HttpStatus.OK);

    }

}