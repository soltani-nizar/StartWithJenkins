package fr.sparkit.crm.services;

import fr.sparkit.crm.dto.StatusDto;
import fr.sparkit.crm.entities.Status;

public interface IStatusService extends IGenericService<Status, Long> {

    StatusDto findById(Long id);

    StatusDto updateStatus(StatusDto statusDto);

}
