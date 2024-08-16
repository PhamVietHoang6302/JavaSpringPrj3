package com.javaweb.utils;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import com.javaweb.model.dto.BuildingDTO;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RentAreaUtils {
    public static List<RentAreaEntity> ConfigRentArea(BuildingEntity buildingEntity, BuildingDTO buildingDTO) {
        List<Long> rentAreaList = Arrays.stream(buildingDTO.getRentArea().split(","))
                .map(Long::valueOf)
                .collect(Collectors.toList());
        List<RentAreaEntity> newRentAreaEntities = rentAreaList.stream()
                .map(value -> {
                    RentAreaEntity rentAreaEntity = new RentAreaEntity();
                    rentAreaEntity.setValue(value);
                    rentAreaEntity.setBuilding(buildingEntity);
                    return rentAreaEntity;
                })
                .collect(Collectors.toList());
        return newRentAreaEntities;
    }

}
