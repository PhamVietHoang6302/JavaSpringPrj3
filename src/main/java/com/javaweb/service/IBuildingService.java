package com.javaweb.service;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.model.response.ResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;


public interface IBuildingService {

    Page<BuildingSearchResponse> buildingResponse(BuildingSearchRequest buildingSearchRequest, int pageNow);

    ResponseDTO saveBuilding(BuildingDTO buildingDTO);

    BuildingDTO findBuildingById(Long id);

    ResponseDTO deleteBuilding(List<Long> ids);

    ResponseDTO changeOfBuildingManagementStaff(Long buildingId, List<Long> staffs);
}
