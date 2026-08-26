package com.practice.practiceDevBackend.controller;

import com.practice.practiceDevBackend.dto.report.ReportResponse;
import com.practice.practiceDevBackend.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api/admin/reports")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @GetMapping
    public ResponseEntity<ReportResponse> getReport(){
        return ResponseEntity.ok(reportService.getReport());
    }
}
