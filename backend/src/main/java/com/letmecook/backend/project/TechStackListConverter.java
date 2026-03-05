package com.letmecook.backend.project;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.letmecook.backend.project.Project.TechStack;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class TechStackListConverter implements AttributeConverter<List<TechStack>, String> {

    @Override
    public String convertToDatabaseColumn(List<TechStack> attribute) {
        if (attribute == null || attribute.isEmpty())
            return "";
        return attribute.stream().map(Enum::name).collect(Collectors.joining(","));
    }

    @Override
    public List<TechStack> convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isBlank())
            return new ArrayList<>();
        return Arrays.stream(dbData.split(",")).map(TechStack::valueOf).collect(Collectors.toList());
    }
}
