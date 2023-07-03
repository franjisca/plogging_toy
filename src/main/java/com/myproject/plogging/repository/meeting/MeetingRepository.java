package com.myproject.plogging.repository.meeting;

import com.myproject.plogging.domain.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeetingRepository extends JpaRepository<Meeting, Long>, MeetingCustomRepository {

}
