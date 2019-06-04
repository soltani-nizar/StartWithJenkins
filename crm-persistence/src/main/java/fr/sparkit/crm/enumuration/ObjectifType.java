package fr.sparkit.crm.enumuration;

public enum ObjectifType {

    STAFFING("Staffing"), STAFFINGID("1");
    private String value;

    ObjectifType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
