package com.javaweb.service;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.model.response.ResponseDTO;


import java.util.List;


public interface IBuildingService {

    List<BuildingSearchResponse> buildingResponse(BuildingSearchRequest buildingSearchRequest);

    ResponseDTO saveBuilding(BuildingDTO buildingDTO);

    BuildingDTO findBuildingById(Long id);

    ResponseDTO deleteBuilding(List<Long> ids);

    ResponseDTO changeOfBuildingManagementStaff(Long buildingId, List<Long> staffs);
}
