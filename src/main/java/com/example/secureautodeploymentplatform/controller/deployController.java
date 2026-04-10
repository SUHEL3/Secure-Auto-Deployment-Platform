package com.example.secureautodeploymentplatform.controller;

import com.example.secureautodeploymentplatform.services.DeployServices;
import com.example.secureautodeploymentplatform.wrapper.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sadp/")
public class deployController {
    private final DeployServices deployServices;

    public deployController(DeployServices deployServices){
        this.deployServices = deployServices;
    }

    @PostMapping("/clone")
    public ResponseEntity<ApiResponse<String>> cloneRepo(
            @RequestParam String url
    ) throws Exception {
        ApiResponse<String> response = deployServices.cloneRepo(url);
        return ResponseEntity.ok(response);
    }
}
