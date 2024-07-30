package com.javaweb.repository.custom.impl;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.repository.custom.BuildingRepositoryCustom;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.Field;
import java.util.List;

@Repository
public class BuildingRepositoryCustomImpl implements BuildingRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    private void QueryJoin(BuildingSearchRequest buildingSearchRequest, StringBuilder sql) {
        if (buildingSearchRequest.getAreaFrom() != null || buildingSearchRequest.getAreaTo() != null) {
            sql.append(" join rentarea as ra on ra.buildingid = b.id ");
        }
        if (buildingSearchRequest.getStaffId() != null) {
            sql.append(" join assignmentbuilding as asmb on asmb.buildingid = b.id ");
        }
    }


    @Override
    public List<BuildingEntity> findAll(BuildingSearchRequest buildingSearchRequest) {
        StringBuilder sql = new StringBuilder(" select distinct b.* from building as b ");
        StringBuilder where = new StringBuilder(" where 1 = 1 ");
        QueryJoin(buildingSearchRequest, sql);
        QuerySQLNormal(buildingSearchRequest, where);
        QuerySQLSpecial(buildingSearchRequest, where);
        sql.append(where);
        Query query = entityManager.createNativeQuery(sql.toString(), BuildingEntity.class);
        return query.getResultList();
    }

    private void QuerySQLNormal(BuildingSearchRequest buildingSearchRequest, StringBuilder where) {
        Field[] fields = BuildingSearchRequest.class.getDeclaredFields();
        try {
            for (Field field : fields) {
                field.setAccessible(true);
                String fieldName = field.getName();
                if (!fieldName.contains("From") && !fieldName.contains("To") && !fieldName.contains("staffId")
                        && !fieldName.contains("typeCode")) {
                    Object value = field.get(buildingSearchRequest);
                    if (value != null && value != "") {
                        if (field.getType().getName().equals("java.lang.Long") || field.getType().getName().equals("java.lang.Integer")
                                || field.getType().getName().equals("java.lang.Double") || field.getType().getName().equals("java.lang.Float")) {
                            where.append(" and " + fieldName.toLowerCase() + " = " + value);
                        } else {
                            where.append(" and " + fieldName.toLowerCase() + " like '%" + value + "%'");
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void QuerySQLSpecial(BuildingSearchRequest buildingSearchRequest, StringBuilder where) {
        Long areaFrom = buildingSearchRequest.getAreaFrom();
        Long areaTo = buildingSearchRequest.getAreaTo();
        if (areaFrom != null || areaTo != null) {
            if (areaFrom != null) {
                where.append(" and ra.value >= " + areaFrom);
            }
            if (areaTo != null) {
                where.append(" and ra.value <= " + areaTo);
            }
        }
        Long rentPriceFrom = buildingSearchRequest.getRentPriceFrom();
        Long rentPriceTo = buildingSearchRequest.getRentPriceTo();
        if (rentPriceFrom != null || rentPriceTo != null) {
            if (rentPriceFrom != null) {
                where.append(" and b.rentPrice >= " + rentPriceFrom);
            }
            if (rentPriceTo != null) {
                where.append(" and b.rentPrice <= " + rentPriceTo);
            }
        }
        Long staffId = buildingSearchRequest.getStaffId();
        if (staffId != null) {
            where.append(" and asmb.staffid = " + staffId);
        }
        List<String> rentType = buildingSearchRequest.getTypeCode();
        if (rentType != null && rentType.size() > 0) {
            where.append(" and b.type regexp '" + String.join("|", rentType) + "'");
        }

    }
}
