package com.ace.util.converters;

import javax.persistence.Converter;

import com.ace.enums.GameProvider;

@Converter(autoApply = true)
public class GameProviderConverter extends EnumAttributeConverter<GameProvider> {
    public GameProviderConverter() {
        super(GameProvider.class);
    }
}

