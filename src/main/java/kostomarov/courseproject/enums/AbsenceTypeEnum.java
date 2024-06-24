package kostomarov.courseproject.enums;

import lombok.Getter;

@Getter
public enum AbsenceTypeEnum {
    yearly_vac("Щорічна відпустка"),
    social_vac("Соціальна відпустка"),
    sick_leave("Лікарняний"),
    absenteeism("Прогул");

    private final String type;

    AbsenceTypeEnum(String type) {
        this.type = type;
    }

    public static AbsenceTypeEnum getAbsenceTypeById(int index) {
        if (index >= AbsenceTypeEnum.values().length) {
            return AbsenceTypeEnum.values()[0];
        } else {
            return AbsenceTypeEnum.values()[index];
        }
    }
}
