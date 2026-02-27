package com.ace.util.converters;

import javax.persistence.Converter;

import com.ace.enums.GameOrientation;

@Converter(autoApply = true)
public class GameOrientationConverter extends EnumAttributeConverter<GameOrientation> {
    public GameOrientationConverter() {
        super(GameOrientation.class);
    }
}

