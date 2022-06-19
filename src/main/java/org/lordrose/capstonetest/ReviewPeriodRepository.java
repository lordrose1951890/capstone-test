package org.lordrose.capstonetest;

import org.lordrose.capstonetest.domains.ReviewPeriod;
import org.lordrose.capstonetest.domains.constants.ReviewPeriodStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewPeriodRepository extends JpaRepository<ReviewPeriod, Long> {

    List<ReviewPeriod> findAllByStatus(ReviewPeriodStatus status);
}
