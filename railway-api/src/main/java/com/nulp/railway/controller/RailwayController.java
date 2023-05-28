package com.nulp.railway.controller;

import com.nulp.railway.dto.RailwayDto;
import com.nulp.railway.mapper.RailwayMapper;
import com.nulp.railway.service.RailwayService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Validated
@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/railways")
public class RailwayController {

    private static final String LOG_MESSAGE = "Endpoint - {}() call";
    private static final String LOG_MESSAGE_WITH_RAILWAY_ID = "Endpoint - {}({}) call";
    private final RailwayService railwayService;
    private final RailwayMapper railwayMapper;

    @GetMapping("/view")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<List<RailwayDto>> getAllRailwaysView(
            @RequestParam("filter") String filter,
            @RequestParam("sortDirection") String sortDirection,
            @PageableDefault(size = 5) Pageable pageable
    ) {
        log.debug(LOG_MESSAGE, "getAllRailwaysView");
        List<RailwayDto> railways = railwayMapper.toDtoList(railwayService.getAllRailwaysView(filter, sortDirection, pageable));
        return ResponseEntity.ok(railways);
    }

    @GetMapping("/count")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Long> getAllRailwaysCount() {
        log.debug(LOG_MESSAGE, "getAllRailwaysCount");
        Long count = railwayService.getAllRailwaysCount();
        return ResponseEntity.ok(count);
    }

    @GetMapping
    public ResponseEntity<List<RailwayDto>> getAll() {
        log.debug(LOG_MESSAGE, "getAllRailways");
        var railways = railwayMapper.toDtoList(railwayService.getAll());
        return new ResponseEntity<>(railways, HttpStatus.OK);
    }

    @GetMapping("/{railwayId}")
    public ResponseEntity<RailwayDto> getById(@PathVariable Long railwayId) {
        log.debug(LOG_MESSAGE_WITH_RAILWAY_ID, "getRailwayById", railwayId);
        var railway = railwayMapper.toDto(railwayService.getById(railwayId));
        return new ResponseEntity<>(railway, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<RailwayDto> create(@RequestBody @Valid RailwayDto railwayDto) {
        log.debug(LOG_MESSAGE, "createRailway");
        var railway = railwayService.save(railwayMapper.toEntity(railwayDto));
        return new ResponseEntity<>(railwayMapper.toDto(railway), HttpStatus.CREATED);
    }

    @PutMapping("/{railwayId}")
    public ResponseEntity<RailwayDto> update(@PathVariable Long railwayId, @RequestBody @Valid RailwayDto railwayDto) {
        log.debug(LOG_MESSAGE_WITH_RAILWAY_ID, "updateRailway", railwayId);
        var railway = railwayService.update(railwayMapper.toEntity(railwayDto), railwayId);
        return new ResponseEntity<>(railwayMapper.toDto(railway), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{railwayId}")
    public void deleteById(@PathVariable Long railwayId) {
        log.debug(LOG_MESSAGE_WITH_RAILWAY_ID, "deleteRailwayById", railwayId);
        railwayService.deleteById(railwayId);
    }
}