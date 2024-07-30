package com.javaweb.api.admin;

import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.service.IBuildingService;
import com.javaweb.service.IUserService;
import com.javaweb.service.impl.BuildingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/buildings")
public class BuildingAPI {

    @Autowired
    IBuildingService buildingService;

    @Autowired
    IUserService userService;
    @Autowired
    private BuildingServiceImpl buildingServiceImpl;

    @PostMapping
    public ResponseEntity<ResponseDTO> createOrUpdateBuilding(@RequestBody BuildingDTO buildingDTO) {
        ResponseDTO responseDTO = buildingService.saveBuilding(buildingDTO);
        if (responseDTO.getMessage().equals("Building saved successfully")) {
            return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @DeleteMapping("/{ids}")
    ResponseEntity<ResponseDTO> deleteBuilding(@PathVariable List<Long> ids) {
        ResponseDTO responseDTO = buildingService.deleteBuilding(ids);
        if (responseDTO.getMessage().equals("Building deleted successfully")) {
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @GetMapping("/{id}/staffs")
    public ResponseEntity<ResponseDTO> getBuildingStaffs(@PathVariable Long id) {
        ResponseDTO responseDTO = userService.listStaffChecked(id);
        if (responseDTO.getMessage().equals("Successfully")) {
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @PostMapping("/{idBuilding}/staffs")
    public ResponseEntity<ResponseDTO> updateStaff(@PathVariable Long idBuilding,
                                                   @RequestBody List<Long> idStaffs) {
        ResponseDTO responseDTO = buildingServiceImpl.changeOfBuildingManagementStaff(idBuilding, idStaffs);
        if (responseDTO.getMessage().equals("Successfully")) {
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
