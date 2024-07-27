package com.javaweb.controller.admin;


import com.javaweb.entity.BuildingEntity;
import com.javaweb.enums.buildingType;
import com.javaweb.enums.districtCode;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.service.IBuildingService;
import com.javaweb.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController(value = "buildingControllerOfAdmin")
public class BuildingController {

    @Autowired
    IUserService userService;

    @Autowired
    IBuildingService buildingService;

    @Autowired
    BuildingRepository buildingRepository;

    @GetMapping("/admin/building-list")
    public ModelAndView buildingList(@ModelAttribute(name = "modelSearch") BuildingSearchRequest buildingSearchRequest) {
        ModelAndView modelAndView = new ModelAndView("admin/building/list");
        modelAndView.addObject("district", districtCode.type());
        modelAndView.addObject("rentType", buildingType.type());
        modelAndView.addObject("staffs", userService.getStaffs());
        modelAndView.addObject("listBuildingResponse", buildingService.buildingResponse(buildingSearchRequest));
        return modelAndView;
    }

    @GetMapping("/admin/building-edit")
    public ModelAndView buildingEdit(@ModelAttribute(name = "buildingEdit")BuildingDTO buildingDTO) {
        ModelAndView modelAndView = new ModelAndView("admin/building/edit");
        modelAndView.addObject("district", districtCode.type());
        modelAndView.addObject("rentType", buildingType.type());
        return modelAndView;
    }

    @GetMapping("/admin/building-edit-{ids}")
    public ModelAndView buildingEdit(@PathVariable Long ids) {
        ModelAndView modelAndView = new ModelAndView("admin/building/edit");
        modelAndView.addObject("district", districtCode.type());
        modelAndView.addObject("rentType", buildingType.type());
        modelAndView.addObject("buildingEdit", buildingService.findBuildingById(ids));
        return modelAndView;
    }
}
