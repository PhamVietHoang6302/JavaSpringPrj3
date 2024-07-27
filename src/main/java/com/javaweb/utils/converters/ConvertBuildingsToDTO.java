package com.javaweb.utils.converters;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import com.javaweb.enums.districtCode;
import com.javaweb.model.response.BuildingSearchResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ConvertBuildingsToDTO {
    @Autowired
    ModelMapper modelMapper;

    public List<BuildingSearchResponse> ConvertBuildingsEntityToDTO(List<BuildingEntity> buildingEntities) {
        List<BuildingSearchResponse> buildingSearchResponses = new ArrayList<>();
        for (BuildingEntity buildingEntity : buildingEntities) {
            BuildingSearchResponse buildingSearchResponse = modelMapper.map(buildingEntity, BuildingSearchResponse.class);
            List<String> lAddress = new ArrayList<>();
            if (buildingEntity.getStreet() != null && !buildingEntity.getStreet().isEmpty()) {
                lAddress.add(buildingEntity.getStreet());
            }
            if (buildingEntity.getWard() != null && !buildingEntity.getWard().isEmpty()) {
                lAddress.add(buildingEntity.getWard());
            }
            lAddress.add(districtCode.getDistrictNameByValue(buildingEntity.getDistrict()));
            buildingSearchResponse.setAreaForRent(buildingEntity.getListRentArea().stream().sorted(Comparator.comparing(RentAreaEntity::getValue))
                    .map(i -> i.getValue().toString()).collect(Collectors.joining(", ")));
            buildingSearchResponse.setAddress(String.join(", ", lAddress));
            buildingSearchResponses.add(buildingSearchResponse);
        }
        return buildingSearchResponses;
    }
}
