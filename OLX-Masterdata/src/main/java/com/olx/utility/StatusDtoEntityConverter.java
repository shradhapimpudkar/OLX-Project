package com.olx.utility;

import com.olx.dto.Status;
import com.olx.entity.StatusEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;

import java.util.List;
import java.util.stream.Collectors;

public class StatusDtoEntityConverter {

    public static Status convertEntityToDto(ModelMapper modelMapper, StatusEntity statusEntity) {
        TypeMap<StatusEntity, Status> typeMap = modelMapper.typeMap(StatusEntity.class, Status.class);
        typeMap.addMappings(mapper -> mapper.map(StatusEntity::getStatus, Status::setStatus));
        return modelMapper.map(statusEntity, Status.class);
    }

    public static StatusEntity convertDtoToEntity(ModelMapper modelMapper, Status status) {
        TypeMap<Status, StatusEntity> typeMap = modelMapper.typeMap(Status.class, StatusEntity.class);
        typeMap.addMappings(mapper -> mapper.map(Status::getStatus, StatusEntity::setStatus));
        return modelMapper.map(status, StatusEntity.class);
    }

    public static List<Status> convertListEntityToDto(ModelMapper modelMapper, List<StatusEntity> statusEntityList) {
        TypeMap<StatusEntity, Status> typeMap = modelMapper.typeMap(StatusEntity.class, Status.class);
        typeMap.addMappings(mapper -> mapper.map(StatusEntity::getStatus, Status::setStatus));
        return statusEntityList
                .stream()
                .map(statusEntity -> modelMapper.map(statusEntity, Status.class))
                .collect(Collectors.toList());
    }

    public static List<StatusEntity> convertListDtoToEntity(ModelMapper modelMapper, List<Status> statusList) {
        TypeMap<Status, StatusEntity> typeMap = modelMapper.typeMap(Status.class, StatusEntity.class);
        typeMap.addMappings(mapper -> mapper.map(Status::getStatus, StatusEntity::setStatus));
        return statusList
                .stream()
                .map(status -> modelMapper.map(status, StatusEntity.class))
                .collect(Collectors.toList());
    }
}
