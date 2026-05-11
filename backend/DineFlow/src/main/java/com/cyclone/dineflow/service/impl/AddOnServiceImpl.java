package com.cyclone.dineflow.service.impl;

import com.cyclone.dineflow.dto.requestdto.AddOnRequestDto;
import com.cyclone.dineflow.dto.responsedto.AddOnResponseDto;
import com.cyclone.dineflow.dtomapper.AddOnResponseDtoMapper;
import com.cyclone.dineflow.entity.AddOn;
import com.cyclone.dineflow.entity.MenuItem;
import com.cyclone.dineflow.entity.MenuItemVariant;
import com.cyclone.dineflow.repository.AddOnRepository;
import com.cyclone.dineflow.repository.MenuItemRepository;
import com.cyclone.dineflow.service.AddOnService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * [Detailed description of the class's responsibility]
 * * @author 2480010
 *
 * @version 1.0
 * @since 08-05-2026
 */
@Service
@RequiredArgsConstructor
public class AddOnServiceImpl implements AddOnService {

    private final AddOnRepository addOnRepository;
    private final MenuItemRepository menuItemRepository;

    @Override
    public AddOnResponseDto addAddOn(AddOnRequestDto addOnRequestDto, String itemId) {
        Optional<AddOn> foundAddon = addOnRepository.findByName(addOnRequestDto.name());
        Optional<MenuItem> foundMenuItem = menuItemRepository.findById(itemId);

        if(foundAddon.isPresent() && foundMenuItem.isPresent()){
            throw new RuntimeException("AddOn already exists");
        }

        AddOn addon = AddOn.builder()
                .name(addOnRequestDto.name())
                .extraPrice(addOnRequestDto.extraPrice())
                .build();
        addOnRepository.save(addon);
        return AddOnResponseDtoMapper.toDto(addon,"AddOn successfully created");
    }

    @Override
    public List<AddOnResponseDto> viewAllAddOnsOfAMenuItem(String itemId) {
        List<AddOnResponseDto> addOnList = addOnRepository.findAll().stream().filter((addOn) ->addOn.getMenuItem().getId().equals(itemId)).map((addOn) -> AddOnResponseDtoMapper.toDto(addOn,null)).toList();
        return addOnList;
    }

    @Override
    public String updateAddOn(String id, AddOnRequestDto addOnRequestDto) {
        AddOn foundAddOn = addOnRepository.findById(id).orElseThrow(()-> new RuntimeException("AddOn not found"));
        foundAddOn.setName(addOnRequestDto.name());
        foundAddOn.setExtraPrice(addOnRequestDto.extraPrice());
        addOnRepository.save(foundAddOn);
        return "AddOn successfully updated";
    }

    @Override
    public String deleteAddOn(String id) {
        AddOn foundAddOn = addOnRepository.findById(id).orElseThrow(()-> new RuntimeException("AddOn not found"));
        addOnRepository.delete(foundAddOn);
        return "AddOn successfully deleted";
    }
}
