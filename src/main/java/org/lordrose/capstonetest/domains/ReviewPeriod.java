package org.lordrose.capstonetest.domains;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.lordrose.capstonetest.domains.constants.ReviewPeriodStatus;
import org.lordrose.capstonetest.domains.converters.ReviewPeriodStatusPostgreEnum;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TypeDef(name = "period_status", typeClass = ReviewPeriodStatusPostgreEnum.class)
@Entity
@Table(name = "tbl_review_period")
public class ReviewPeriod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "reporting_date")
    private LocalDate reportingDate;

    @Column(name = "due_date")
    private LocalDate dueDate;

    @Enumerated(EnumType.STRING)
    @Type(type = "period_status")
    private ReviewPeriodStatus status;

    @ManyToOne
    @JoinColumn(name = "covenant_id")
    private Covenant covenant;
}
