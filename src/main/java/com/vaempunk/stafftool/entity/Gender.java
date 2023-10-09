package com.vaempunk.stafftool.entity;

public enum Gender {

    M, F;

    public static Gender from(String gender) {
        return switch (gender) {
            case "M" -> M;
            case "F" -> F;
            default -> null;
        };
    }

    public static Gender from(char gender) {
        return switch (gender) {
            case 'M' -> M;
            case 'F' -> F;
            default -> null;
        };
    }
}
