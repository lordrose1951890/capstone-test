package org.lordrose.capstonetest.domains;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import org.lordrose.capstonetest.domains.constants.CovenantFrequency;
import org.lordrose.capstonetest.domains.constants.CovenantStatus;
import org.lordrose.capstonetest.domains.constants.CovenantType;
import org.lordrose.capstonetest.domains.converters.CovenantFrequencyPostgreEnum;
import org.lordrose.capstonetest.domains.converters.CovenantTypePostgreEnum;
import org.lordrose.capstonetest.domains.converters.StringPostgreEnum;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
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
@TypeDefs({
        @TypeDef(name = "covenant_type", typeClass = CovenantTypePostgreEnum.class),
        @TypeDef(name = "covenant_frequency", typeClass = CovenantFrequencyPostgreEnum.class),
        @TypeDef(name = "covenant_status", typeClass = StringPostgreEnum.class)
})
@Entity
@Table(name = "tbl_covenant")
public class Covenant {

    @Id
    @GeneratedValue(generator = "cov-generator")
    @GenericGenerator(name = "cov-generator",
            parameters = @Parameter(name = "prefix", value = "COV"),
            strategy = "org.lordrose.capstonetest.domains.converters.StringPrefixNumericIdGenerator")
    private String id;

    @Column(name = "reporter")
    private String reporter;

    @Enumerated(EnumType.STRING)
    @Type(type = "covenant_type")
    private CovenantType type;

    @Column(name = "first_reporting_date")
    private LocalDate firstReportingDate;

    @Column(name = "reporting_expiry_date")
    private LocalDate reportingExpiryDate;

    @Enumerated(EnumType.STRING)
    @Type(type = "covenant_frequency")
    private CovenantFrequency frequency;

    @Enumerated(EnumType.STRING)
    @Type(type = "covenant_status")
    private CovenantStatus status;

    @ManyToMany(mappedBy = "covenants")
    private List<Facility> facilities = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "covenant")
    private List<ReviewPeriod> reviewPeriods = new ArrayList<>();
}
