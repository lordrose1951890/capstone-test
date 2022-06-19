package org.lordrose.capstonetest;

import lombok.RequiredArgsConstructor;
import org.lordrose.capstonetest.domains.Covenant;
import org.lordrose.capstonetest.domains.ReviewPeriod;
import org.lordrose.capstonetest.domains.constants.CovenantFrequency;
import org.lordrose.capstonetest.domains.constants.CovenantStatus;
import org.lordrose.capstonetest.domains.constants.CovenantType;
import org.lordrose.capstonetest.domains.constants.ReviewPeriodStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/test")
public class TestController {

    private final ReviewPeriodRepository periodRepository;
    private final CovenantRepository covenantRepository;

    @GetMapping("/create")
    public String intiTest() {
        Covenant covenant = new Covenant();
        covenant.setReporter("test reporter, " + System.currentTimeMillis()/1000);
        covenant.setFrequency(CovenantFrequency.MONTHLY);
        covenant.setStatus(CovenantStatus.ACTIVE);
        covenant.setType(CovenantType.REPORTING);
        Covenant saved = covenantRepository.save(covenant);

        ReviewPeriod period = new ReviewPeriod();
        period.setCovenant(saved);
        period.setStatus(ReviewPeriodStatus.UPCOMING);
        periodRepository.save(period);

        return "ok";
    }

    @GetMapping("/rp")
    public String getTest() {
        periodRepository.findAll()
                .forEach(period -> System.out.printf("id: %s, status: %s",
                        period.getId(), period.getStatus().textValue));

        return "ok";
    }

    @GetMapping("/cov")
    public String getTestCov() {
        covenantRepository.findAll()
                .forEach(covenant -> System.out.printf("id: %s, status: %s",
                        covenant.getId(), covenant.getStatus().textValue));

        return "ok";
    }

    @GetMapping("/rp/{statusParam}")
    public ResponseEntity<String> findTest(@PathVariable String statusParam) {
        ReviewPeriodStatus status;
        try {
            status = ReviewPeriodStatus.valueOf(statusParam);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Not status of covenants");
        }
        String result = periodRepository.findAllByStatus(status).stream()
                .map(period -> period.getId() + ", " + period.getStatus().textValue)
                .collect(Collectors.joining("\n", ">-----\n", "\n-----<"));
        System.out.println(result);
        return ResponseEntity.ok(result);
    }
}
