package com.exmample.simplejpa.developer;

import com.exmample.simplejpa.developer.dto.CreateDeveloper;
import com.exmample.simplejpa.developer.dto.DeveloperDTO;
import com.exmample.simplejpa.developer.dto.DeveloperDetailDTO;
import com.exmample.simplejpa.developer.dto.EditDeveloper;
import com.exmample.simplejpa.developer.service.DMakerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class DMakerController {

    private final DMakerService dMakerService;

    @GetMapping("/developers")
    public List<DeveloperDTO> getAllDevelopers() {

        return dMakerService.getAllEmployeeDevelopers();
    }

    @PostMapping("/create-developer")
    public ResponseEntity<CreateDeveloper.Response> createDeveloper(
            @Valid @RequestBody CreateDeveloper.Request request
    ) {

        return ResponseEntity.ok(dMakerService.createDeveloper(request));
    }

    @GetMapping("/developer/{memberId}")
    public ResponseEntity<DeveloperDetailDTO> getDeveloper(
            @PathVariable String memberId
    ) {

        return ResponseEntity.ok(dMakerService.getDeveloper(memberId));
    }

    @PutMapping("/developer/{memberId}")
    public ResponseEntity<DeveloperDetailDTO> updateDeveloper(
            @PathVariable String memberId,
            @Valid @RequestBody EditDeveloper.Request request
    ) {

        return ResponseEntity.ok( dMakerService.updateDeveloper(memberId, request) );
    }

    @DeleteMapping("/developer/{memberId}")
    public ResponseEntity<DeveloperDetailDTO> deleteDeveloper( @PathVariable String memberId ) {

        return ResponseEntity.ok( dMakerService.deleteDeveloper(memberId) );
    }
}
