package com.nulp.railway.repository;

import com.nulp.railway.entity.Railway;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RailwayRepository extends PagingAndSortingRepository<Railway, Long> {
    // Additional query methods can be added here if needed

    Page<Railway> findAll(Pageable pageable);

    Page<Railway> findAllByRailwayNumberStartingWith(String filter, Pageable pageable);

}