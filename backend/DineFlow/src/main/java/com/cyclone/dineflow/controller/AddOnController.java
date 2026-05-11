package com.cyclone.dineflow.controller;

import com.cyclone.dineflow.dto.requestdto.AddOnRequestDto;
import com.cyclone.dineflow.dto.responsedto.AddOnResponseDto;
import com.cyclone.dineflow.service.AddOnService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * [Detailed description of the class's responsibility]
 * * @author 2480010
 *
 * @version 1.0
 * @since 08-05-2026
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class AddOnController {
    private final AddOnService addOnService;

    @PostMapping("/items/{itemId}/addons")
    public ResponseEntity<AddOnResponseDto> addAddOn(@RequestBody AddOnRequestDto addOnRequestDto, @PathVariable String itemId) {
        return ResponseEntity.ok(addOnService.addAddOn(addOnRequestDto,itemId));
    }

    @GetMapping("/items/{itemId}/addons")
    public ResponseEntity<List<AddOnResponseDto>> viewAllAddOnsOfAMenuItem(@PathVariable String itemId) {
        return ResponseEntity.ok(addOnService.viewAllAddOnsOfAMenuItem(itemId));
    }

    @PutMapping("/addons/{id}")
    public ResponseEntity<String> updateAddOn(@PathVariable String id, @RequestBody AddOnRequestDto addOnRequestDto) {
        return ResponseEntity.ok(addOnService.updateAddOn(id, addOnRequestDto));
    }

    @DeleteMapping("/addons/{id}")
    public ResponseEntity<String> deleteAddOn(@PathVariable String id) {
        return ResponseEntity.ok(addOnService.deleteAddOn(id));
    }
}
