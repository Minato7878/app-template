package com.nulp.railway.service;

import com.nulp.railway.entity.Railway;
import com.nulp.railway.exception.EntityNotFoundException;
import com.nulp.railway.repository.RailwayRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class RailwayService {

    private final RailwayRepository railwayRepository;

    public List<Railway> getAllRailwaysView(String filter, String sortDirection, Pageable pageable) {
        Sort sort = Sort.by(sortDirection.equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC, "id");
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
        return railwayRepository.findAllByRailwayNumberStartingWith(filter, pageable).getContent();
    }

    public Long getAllRailwaysCount() {
        return railwayRepository.count();
    }

    public List<Railway> getAll() {
        return StreamSupport.stream(railwayRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public Railway getById(Long railwayId) {
        return railwayRepository.findById(railwayId)
                .orElseThrow(() -> new EntityNotFoundException("Railway not found with ID: " + railwayId));
    }

    public Railway save(Railway railway) {
        return railwayRepository.save(railway);
    }

    public Railway update(Railway railway, Long railwayId) {
        railway.setId(railwayId);
        return railwayRepository.save(railway);
    }

    public void deleteById(Long railwayId) {
        railwayRepository.deleteById(railwayId);
    }

}