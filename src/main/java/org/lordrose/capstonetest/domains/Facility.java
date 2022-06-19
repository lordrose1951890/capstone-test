package org.lordrose.capstonetest.domains;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_facility")
public class Facility {

    @Id
    @GeneratedValue(generator = "cov-generator")
    @GenericGenerator(name = "cov-generator",
            parameters = @Parameter(name = "prefix", value = "FAC"),
            strategy = "org.lordrose.capstonetest.domains.converters.StringPrefixNumericIdGenerator")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "borrowers")
    private String borrowers;

    @ManyToMany
    @JoinTable(name = "tbl_facility_covenant",
            joinColumns = @JoinColumn(name = "facility_id"),
            inverseJoinColumns = @JoinColumn(name = "covenant_id"))
    private List<Covenant> covenants = new ArrayList<>();
}
