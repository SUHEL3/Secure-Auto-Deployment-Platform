package com.example.secureautodeploymentplatform.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@RestController
@RequestMapping("/api/sadp")
public class testController {

    @GetMapping("/test")
    public String testingBackend(){
        return "Backend running properly";
    }

    @GetMapping("/version")
    public String commandRuner() throws Exception{
        ProcessBuilder pb = new ProcessBuilder("docker","--version");
        Process process = pb.start();

        BufferedReader successreader = new BufferedReader(
                new InputStreamReader(process.getInputStream())
        );
        BufferedReader errorreader = new BufferedReader(
                new InputStreamReader(process.getErrorStream())
        );
        String error = errorreader.readLine();
        if(error != null){
            return "Error"+error;
        }
        return successreader.readLine();
    }

    @GetMapping("/ps")
    public String dockerPS() throws Exception{
        ProcessBuilder processBuilder = new ProcessBuilder("docker","ps");
        Process process = processBuilder.start();

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(process.getInputStream())
        );
        return reader.readLine();
    }

    @GetMapping("/whoami")
    public String checkUser() throws Exception{
        ProcessBuilder pb = new ProcessBuilder("whoami");
        Process process = pb.start();

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(process.getInputStream())
        );
        return reader.readLine();
    }

    @GetMapping("/images")
    public String getImages() throws Exception{
        ProcessBuilder pb = new ProcessBuilder("docker","images");
        Process process = pb.start();

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(process.getInputStream())
        );

        StringBuilder output = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null){
            output.append(line).append("\n");
        }
        return output.toString();
    }

    @GetMapping("/gitVersion")
    public String gitVersion() throws Exception{
        ProcessBuilder pb = new ProcessBuilder("git","--version");
        Process process = pb.start();

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(process.getInputStream())
        );
        return reader.readLine();
    }
}
