package kostomarov.courseproject.enums;

import lombok.Getter;

@Getter
public enum FamilyStateEnum {
    married("Одружен"),
    unmarried("Неодружен"),
    civil("Цивільний шлюб"),
    divorced("Розлучен");

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
