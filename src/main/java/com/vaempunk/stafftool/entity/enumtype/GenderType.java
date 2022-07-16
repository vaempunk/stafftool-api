package com.vaempunk.stafftool.entity.enumtype;

public enum GenderType {
    
    M, F, N;

    public static GenderType from(String gender) {

        if (gender.equalsIgnoreCase("M") || gender.equalsIgnoreCase("MALE"))
            return M;
        else if (gender.equalsIgnoreCase("F") || gender.equalsIgnoreCase("FEMALE"))
            return F;
        else
            return N;
    }

    public static GenderType from(char gender) {
        
        if (gender == 'm' || gender == 'M')
            return M;
        else if (gender == 'f' || gender == 'F')
            return F;
        else
            return N;
    }
}
