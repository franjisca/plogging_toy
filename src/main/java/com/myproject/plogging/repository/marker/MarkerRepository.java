package com.myproject.plogging.repository.marker;

import com.myproject.plogging.domain.Marker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarkerRepository extends JpaRepository<Marker, Long>, MarkerCustomRepository {
}
