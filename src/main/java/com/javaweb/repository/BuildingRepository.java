package com.javaweb.repository;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.repository.custom.BuildingRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional
public interface BuildingRepository extends JpaRepository<BuildingEntity, Long>, BuildingRepositoryCustom {
    public List<BuildingEntity> findByNameContaining(String name);

    public BuildingEntity findById(long id);

    public void deleteAllByIdIn(List<Long> ids);

    @Modifying
    @Query(value = "DELETE FROM assignmentbuilding WHERE buildingid = :buildingId", nativeQuery = true)
    public void deleteAssignmentsByBuildingId(@Param("buildingId") Long buildingId);
}
