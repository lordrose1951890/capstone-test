package org.lordrose.capstonetest.domains;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.lordrose.capstonetest.domains.constants.CovenantFrequency;
import org.lordrose.capstonetest.domains.constants.CovenantStatus;
import org.lordrose.capstonetest.domains.constants.CovenantType;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_covenant")
public class Covenant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "reporter")
    private String reporter;

    @Enumerated(EnumType.STRING)
    private CovenantType type;

    @Column(name = "first_reporting_date")
    private LocalDate firstReportingDate;

    @Column(name = "reporting_expiry_date")
    private LocalDate reportingExpiryDate;

    @Enumerated(EnumType.STRING)
    private CovenantFrequency frequency;

    @Enumerated(EnumType.STRING)
    private CovenantStatus status;

    @ManyToMany(mappedBy = "covenants")
    private List<Facility> facilities = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "covenant")
    private List<ReviewPeriod> reviewPeriods = new ArrayList<>();
}
