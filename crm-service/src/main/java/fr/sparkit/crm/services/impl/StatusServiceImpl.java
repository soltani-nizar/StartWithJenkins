package fr.sparkit.crm.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.sparkit.crm.converter.StatusConvertor;
import fr.sparkit.crm.dao.IStatusDao;
import fr.sparkit.crm.dto.StatusDto;
import fr.sparkit.crm.entities.Status;
import fr.sparkit.crm.services.IStatusService;
import fr.sparkit.crm.util.errors.ApiErrors;
import fr.sparkit.crm.util.errors.ErrorsResponse;
import fr.sparkit.crm.util.http.HttpCustomException;

@Service
public class StatusServiceImpl extends GenericService<Status, Long> implements IStatusService {

    private final IStatusDao statusDao;

    @Autowired
    public StatusServiceImpl(IStatusDao statusDao) {
        this.statusDao = statusDao;
    }

    @Override
    public StatusDto findById(Long id) {
        return StatusConvertor.modelToDto(Optional.ofNullable(statusDao.findOne(id)).orElseThrow(
                () -> new HttpCustomException(ApiErrors.Crm.ENTITY_NOT_FOUND, new ErrorsResponse().error(id))));
    }

    @Override
    public StatusDto updateStatus(StatusDto statusDto) {
        Status status = statusDao.findByColorOrTitleAndIsDeletedFalse(statusDto.getColor(), statusDto.getTitle());
        if (status != null) {
            status.setColor(statusDto.getColor());
            status.setTitle(statusDto.getTitle());
            return StatusConvertor.modelToDto(statusDao.save(status));
        }
        return null;
    }

}
