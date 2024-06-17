package kostomarov.courseproject.enums;

import jakarta.persistence.AttributeConverter;

public class FamilyStateConverter implements AttributeConverter<FamilyStateEnum, Integer> {
    @Override
    public Integer convertToDatabaseColumn(FamilyStateEnum familyStateEnum) {
        return familyStateEnum.ordinal();
    }

    @Override
    public FamilyStateEnum convertToEntityAttribute(Integer integer) {
        return FamilyStateEnum.getFamilyStateById(integer);
    }
}
