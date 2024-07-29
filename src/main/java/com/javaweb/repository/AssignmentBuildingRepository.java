package com.javaweb.repository;

import com.javaweb.entity.AssignmentBuildingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssignmentBuildingRepository extends JpaRepository<AssignmentBuildingEntity, Long> {
    void deleteByBuilding_idAndStaffs_id(Long buildingId, Long staffId);
    void deleteByBuilding_idIn(List<Long> buildingId);
}
