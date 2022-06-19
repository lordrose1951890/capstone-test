package org.lordrose.capstonetest;

import org.lordrose.capstonetest.domains.Covenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CovenantRepository extends JpaRepository<Covenant, Long> {
}
