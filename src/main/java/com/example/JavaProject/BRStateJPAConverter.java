package com.example.JavaProject;

import com.example.JavaProject.enums.BRState;

import javax.persistence.AttributeConverter;

public class BRStateJPAConverter implements AttributeConverter<BRState, String> {
    @Override
    public String convertToDatabaseColumn(BRState brState) {
        if (brState == null)
            return null;
        return brState.name();
    }

    @Override
    public BRState convertToEntityAttribute(String brState) {
        if (brState == null)
            return null;
        return BRState.valueOf(brState);
    }
}
