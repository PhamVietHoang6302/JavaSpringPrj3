package com.javaweb.controller.admin;


import com.javaweb.enums.buildingType;
import com.javaweb.enums.districtCode;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.service.IBuildingService;
import com.javaweb.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@RestController(value = "buildingControllerOfAdmin")
public class BuildingController {

    @Autowired
    IUserService userService;

    @Autowired
    IBuildingService buildingService;

    @Autowired
    BuildingRepository buildingRepository;

    @GetMapping("/admin/building-list")
    public ModelAndView buildingList(@RequestParam(value = "pageBuilding", defaultValue = "1") int page,
                                     @ModelAttribute(name = "modelSearch") BuildingSearchRequest buildingSearchRequest) {

        ModelAndView modelAndView = new ModelAndView("admin/building/list");
        Page<BuildingSearchResponse> pageResult = buildingService.buildingResponse(buildingSearchRequest, page);
        if (pageResult.getTotalPages() < page) {
            page = Math.max(1, pageResult.getTotalPages());
            pageResult = buildingService.buildingResponse(buildingSearchRequest, page - 1);
        }
        modelAndView.addObject("district", districtCode.type());
        modelAndView.addObject("rentType", buildingType.type());
        modelAndView.addObject("staffs", userService.getStaffs());
        modelAndView.addObject("listBuildingResponse", pageResult.getContent());
        modelAndView.addObject("totalPages", pageResult.getTotalPages());
        modelAndView.addObject("currentPage", pageResult.getNumber() + 1);

        return modelAndView;
    }

    @GetMapping("/admin/building-edit")
    public ModelAndView buildingEdit(@ModelAttribute(name = "buildingEdit") BuildingDTO buildingDTO) {
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
