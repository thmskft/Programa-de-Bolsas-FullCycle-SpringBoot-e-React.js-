package br.com.compass.classes;

public enum TypeAccount {
    SAVINGS_ACCOUNT,
    CURRENT_ACCOUNT,
    SALARY_ACCOUNT;

    @Override
    public String toString() {
        return switch (this) {
            case SAVINGS_ACCOUNT -> "Savings Account";
            case CURRENT_ACCOUNT -> "Current Account";
            case SALARY_ACCOUNT -> "Salary Account";
        };
    }
}
