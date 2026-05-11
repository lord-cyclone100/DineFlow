package com.cyclone.dineflow.dtomapper;

import com.cyclone.dineflow.dto.responsedto.AddOnResponseDto;
import com.cyclone.dineflow.entity.AddOn;

/**
 * [Detailed description of the class's responsibility]
 * * @author 2480010
 *
 * @version 1.0
 * @since 08-05-2026
 */
public class AddOnResponseDtoMapper {
    public static AddOnResponseDto toDto(AddOn addOn, String message) {
        return new AddOnResponseDto(
                addOn.getName(),
                addOn.getExtraPrice(),
                message
        );
    }
}
