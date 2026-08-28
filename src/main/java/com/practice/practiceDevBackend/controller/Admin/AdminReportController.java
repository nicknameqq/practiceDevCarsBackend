package com.practice.practiceDevBackend.controller.Admin;

import com.practice.practiceDevBackend.dto.report.ReportSummaryResponse;
import com.practice.practiceDevBackend.service.ReportAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/reports")
@RequiredArgsConstructor
public class AdminReportController {

    private final ReportAdminService reportAdminService;

    @GetMapping("/summary")
    public ResponseEntity<ReportSummaryResponse> getSummary() {

        return ResponseEntity.ok(
                reportAdminService.getSummary()
        );
    }
}