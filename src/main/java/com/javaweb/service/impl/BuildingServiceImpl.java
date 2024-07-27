package com.javaweb.service.impl;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.service.IBuildingService;
import com.javaweb.utils.converters.ConvertBuildingsToDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BuildingServiceImpl implements IBuildingService {

    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private ConvertBuildingsToDTO convertBuildingsToDTO;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<BuildingSearchResponse> buildingResponse(BuildingSearchRequest buildingSearchRequest) {
        List<BuildingEntity> listBuildingRepository = buildingRepository.findAll(buildingSearchRequest);
        List<BuildingSearchResponse> listBuildingSearchResponse = convertBuildingsToDTO.ConvertBuildingsEntityToDTO(listBuildingRepository);
        return listBuildingSearchResponse;
    }

    @Override
    public ResponseDTO saveBuilding(BuildingDTO buildingDTO) {
        BuildingEntity buildingEntity = modelMapper.map(buildingDTO, BuildingEntity.class);
        buildingEntity.setType(buildingDTO.getTypeCode().stream().map(Object::toString).collect(Collectors.joining(",")));
        ResponseDTO responseDTO = new ResponseDTO();
        try {
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
        String[] input = buildingEntity.getType().split(",");
        buildingDTO.setTypeCode(Arrays.asList(input));
        return buildingDTO;
    }

    @Override
    public ResponseDTO deleteBuilding(List<Long> ids) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setData(ids);
        String message = "";
        for (Long id : ids) {
            try {
                buildingRepository.deleteById(id);
                message = "Building deleted successfully";
            } catch (Exception e) {
                responseDTO.setMessage("Building deletion failed");
                responseDTO.setDetail(e.getMessage());
                return responseDTO;
            }
        }
        responseDTO.setMessage(message);
        return responseDTO;
    }


}
