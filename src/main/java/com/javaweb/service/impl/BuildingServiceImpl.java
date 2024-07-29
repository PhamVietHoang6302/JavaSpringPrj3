package com.javaweb.service.impl;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.repository.AssignmentBuildingRepository;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.RentAreaRepository;
import com.javaweb.service.IBuildingService;
import com.javaweb.utils.converters.ConvertBuildingsToDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class BuildingServiceImpl implements IBuildingService {

    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private ConvertBuildingsToDTO convertBuildingsToDTO;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    private RentAreaRepository rentAreaRepository;
    @Autowired
    private AssignmentBuildingRepository assignmentBuildingRepository;

    @Override
    public List<BuildingSearchResponse> buildingResponse(BuildingSearchRequest buildingSearchRequest) {
        List<BuildingEntity> listBuildingRepository = buildingRepository.findAll(buildingSearchRequest);
        List<BuildingSearchResponse> listBuildingSearchResponse = convertBuildingsToDTO.ConvertBuildingsEntityToDTO(listBuildingRepository);
        return listBuildingSearchResponse;
    }

    @Override
    public ResponseDTO saveBuilding(BuildingDTO buildingDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            BuildingEntity buildingEntity = new BuildingEntity();
            if (buildingDTO.getId() != null) {
                rentAreaRepository.deleteByBuilding_Id(buildingDTO.getId());
            }

            modelMapper.map(buildingDTO, buildingEntity);

            if (buildingDTO.getRentArea() != null && buildingDTO.getRentArea() != "") {
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

                rentAreaRepository.saveAll(newRentAreaEntities);

            }


            buildingEntity.setType(buildingDTO.getTypeCode().stream().map(Object::toString).collect(Collectors.joining(",")));

            buildingRepository.save(buildingEntity);
            responseDTO.setMessage("Building saved successfully");
            responseDTO.setData(buildingEntity);
        } catch (Exception e) {
            responseDTO.setMessage(e.getMessage());
        }
        return responseDTO;
    }

    @Override
    public BuildingDTO findBuildingById(Long id) {
        BuildingEntity buildingEntity = buildingRepository.findById(id).orElse(null);
        BuildingDTO buildingDTO = modelMapper.map(buildingEntity, BuildingDTO.class);
        buildingDTO.setRentArea(buildingEntity.getListRentArea().stream()
                .map(rentAreaEntity -> rentAreaEntity.getValue().toString())
                .collect(Collectors.joining(",")));
        String[] input = buildingEntity.getType().split(",");
        buildingDTO.setTypeCode(Arrays.asList(input));
        return buildingDTO;
    }

    @Override
    public ResponseDTO deleteBuilding(List<Long> ids) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setData(ids);
        String message = "";
        try {
            rentAreaRepository.deleteByBuilding_IdIn(ids);
            assignmentBuildingRepository.deleteByBuilding_idIn(ids);
            buildingRepository.deleteAllByIdIn(ids);

            message = "Building deleted successfully";
        } catch (Exception e) {
            responseDTO.setMessage("Building deletion failed");
            responseDTO.setDetail(e.getMessage());
            return responseDTO;
        }
        responseDTO.setMessage(message);
        return responseDTO;
    }


}
