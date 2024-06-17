package kostomarov.courseproject.enums;

import lombok.Getter;

@Getter
public enum FamilyStateEnum {
    married("Одружений/Одружена"),
    unmarried("Неодружений/Неодружена"),
    civil("Цивільний шлюб"),
    divorced("Розлучений/Розлучена");

    private final String state;

    FamilyStateEnum(String state) {
        this.state = state;
    }

    public static FamilyStateEnum getFamilyStateById(int index) {
        if (index >= FamilyStateEnum.values().length) {
            return FamilyStateEnum.values()[0];
        } else {
            return FamilyStateEnum.values()[index];
        }
    }
}
