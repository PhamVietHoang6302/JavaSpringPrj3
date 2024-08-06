package com.javaweb.service.impl;


import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.model.response.StaffResponseDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.RentAreaRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.service.IBuildingService;
import com.javaweb.utils.RentAreaUtils;
import com.javaweb.utils.UploadFileUtils;
import com.javaweb.utils.converters.ConvertBuildingsToDTO;
import org.apache.tomcat.util.codec.binary.Base64;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
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
    UserRepository userRepository;
    @Autowired
    private UploadFileUtils uploadFileUtils;

    @Override
    public Page<BuildingSearchResponse> buildingResponse(BuildingSearchRequest buildingSearchRequest, int pageNow) {

        if (pageNow >= 1) {
            pageNow -= 1;
        }
        buildingSearchRequest.setMaxPageItems(10);
        Pageable pageable = PageRequest.of(pageNow, buildingSearchRequest.getMaxPageItems());
        Page<BuildingEntity> pageBuildingRepository = buildingRepository.findAll(buildingSearchRequest, pageable);
        List<BuildingEntity> listBuildingRepository = pageBuildingRepository.getContent();
        List<BuildingSearchResponse> listBuildingSearchResponse = convertBuildingsToDTO.ConvertBuildingsEntityToDTO(listBuildingRepository);
        return new PageImpl<>(listBuildingSearchResponse, pageable, pageBuildingRepository.getTotalElements());
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
                rentAreaRepository.saveAll(RentAreaUtils.ConfigRentArea(buildingEntity, buildingDTO));
            }
            buildingEntity.setType(buildingDTO.getTypeCode().stream().map(Object::toString).collect(Collectors.joining(",")));
            saveThumbnail(buildingDTO, buildingEntity);
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
//            assignmentBuildingRepository.deleteByBuilding_idIn(ids);
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

    @Override
    public ResponseDTO changeOfBuildingManagementStaff(Long buildingId, List<Long> staffs) {
        ResponseDTO responseDTO = new ResponseDTO();
        String message = "";
        try {
            buildingRepository.deleteAssignmentsByBuildingId(buildingId);
            BuildingEntity buildingEntity = buildingRepository.findById(buildingId).orElse(null);
            List<UserEntity> userEntities = new ArrayList<>();
            for (Long staffId : staffs) {
                userEntities.add(userRepository.findById(staffId).orElse(null));
            }
            buildingEntity.setUsers(userEntities);
            responseDTO.setData(buildingEntity);
            buildingRepository.save(buildingEntity);
        } catch (Exception e) {
            message = e.getMessage();
            responseDTO.setMessage(message);
            return responseDTO;
        }
        message = "Successfully";
        responseDTO.setMessage(message);
        return responseDTO;
    }


    private void saveThumbnail(BuildingDTO buildingDTO, BuildingEntity buildingEntity) {
        if (buildingDTO.getImageBase64() != null && !buildingDTO.getImageBase64().isEmpty()) {
            String base64Data = buildingDTO.getImageBase64();
            if (base64Data.startsWith("data:image/")) {
                base64Data = base64Data.split(",")[1];
            }

            String path = "C://home/office/building/" + buildingDTO.getImageName();
            System.out.println("Saving image to path: " + path);

            if (buildingEntity.getImage() != null && !buildingEntity.getImage().isEmpty()) {
                File oldFile = new File("C://home/office/building" + buildingEntity.getImage());
                if (oldFile.exists()) {
                    oldFile.delete();
                    System.out.println("Deleted old file: " + oldFile.getAbsolutePath());
                }
            }

            byte[] bytes = Base64.decodeBase64(base64Data);
            try (FileOutputStream fos = new FileOutputStream(path)) {
                fos.write(bytes);
                System.out.println("Image saved successfully.");
            } catch (IOException e) {
                e.printStackTrace();
            }
            buildingEntity.setImage(buildingDTO.getImageName());
        }
    }

}
