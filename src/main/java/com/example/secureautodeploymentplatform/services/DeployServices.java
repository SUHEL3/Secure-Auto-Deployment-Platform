package com.example.secureautodeploymentplatform.services;

import com.example.secureautodeploymentplatform.wrapper.ApiResponse;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;

@Service
public class DeployServices {

    public ApiResponse<String> cloneRepo(String url) throws Exception{
        String folderPath = "repos/project1";
        ProcessBuilder pb = new ProcessBuilder("git","clone",url,folderPath);
        Process process = pb.start();

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(process.getInputStream())
        );
        BufferedReader errorreader = new BufferedReader(
                new InputStreamReader(process.getErrorStream())
        );
        StringBuilder erroroutput = new StringBuilder();
        StringBuilder output = new StringBuilder();
        String line;

        while((line = reader.readLine()) != null){
            output.append(line).append("\n");
        }
        while((line = errorreader.readLine())!= null){
            erroroutput.append(line).append("\n");
        }

        int exitcode = process.waitFor();


        if (exitcode != 0){
            return new ApiResponse<>("Error",erroroutput.toString());
        }
        return new ApiResponse<>("Repository clonned successfully",output.toString());
    }
}
