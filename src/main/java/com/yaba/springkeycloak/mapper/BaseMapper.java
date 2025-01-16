package com.yaba.springkeycloak.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;
/**
 * A generic mapper interface for converting between Data Transfer Objects (DTOs) and Entity objects.
 *
 * @param <D> The DTO type
 * @param <E> The Entity type
 */
public interface BaseMapper <D,E> {

    /**
     * Converts a DTO to its corresponding Entity.
     *
     * @param dto The DTO to be converted
     * @return The corresponding Entity object
     */
    E toEntity(D dto);

    /**
     * Converts an Entity to its corresponding DTO.
     *
     * @param entity The Entity to be converted
     * @return The corresponding DTO object
     */
    D toDto(E entity);

    /**
     * Converts a list of DTOs to a list of corresponding Entities.
     *
     * @param dtoList The list of DTOs to be converted
     * @return A list of corresponding Entity objects
     */
    List<E> toEntity(List<D> dtoList);

    /**
     * Converts a list of Entities to a list of corresponding DTOs.
     *
     * @param entityList The list of Entities to be converted
     * @return A list of corresponding DTO objects
     */
    List<D> toDto(List<E> entityList);

    /**
     * Performs a partial update of an entity with the non-null values from a DTO.
     * This method is designed to update only the fields that are present in the DTO,
     * ignoring null values to prevent overwriting existing data with nulls.
     *
     * @param entity The entity to be updated
     * @param dto The DTO containing the new values
     */
    @Named("partialUpdate")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void partialUpdate(@MappingTarget E entity, D dto);
}
