package com.cyclone.dineflow.service;

import com.cyclone.dineflow.dto.requestdto.AddOnRequestDto;
import com.cyclone.dineflow.dto.responsedto.AddOnResponseDto;

import java.util.List;

/**
 * [Detailed description of the class's responsibility]
 * * @author 2480010
 *
 * @version 1.0
 * @since 08-05-2026
 */
public interface AddOnService {
    AddOnResponseDto addAddOn(AddOnRequestDto addOnRequestDto, String itemId);

    List<AddOnResponseDto> viewAllAddOnsOfAMenuItem(String itemId);

    String updateAddOn(String id, AddOnRequestDto addOnRequestDto);

    String deleteAddOn(String id);
}
