package kostomarov.courseproject.enums;

import jakarta.persistence.AttributeConverter;

public class AbsenceTypeConverter implements AttributeConverter<AbsenceTypeEnum, Integer> {
    @Override
    public Integer convertToDatabaseColumn(AbsenceTypeEnum absenceTypeEnum) {
        return absenceTypeEnum.ordinal();
    }

    @Override
    public AbsenceTypeEnum convertToEntityAttribute(Integer integer) {
        return AbsenceTypeEnum.getAbsenceTypeById(integer);
    }
}
