package com.javaweb.repository;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.repository.custom.BuildingRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BuildingRepository extends JpaRepository<BuildingEntity, Long>, BuildingRepositoryCustom {
    public List<BuildingEntity> findByNameContaining(String name);
    public BuildingEntity findById(long id);
    public void deleteAllByIdIn(List<Long> ids);
}
