package com.javaweb.repository;

import com.javaweb.entity.AssignmentBuildingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssignmentRepository extends JpaRepository<AssignmentBuildingEntity, Long> {
    void deleteByBuilding_idAndStaffs_id(Long buildingId, Long staffId);
}
