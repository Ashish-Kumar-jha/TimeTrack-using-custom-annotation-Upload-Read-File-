package com.poc.practice.controller;

import com.poc.practice.Entity.FileData;
import com.poc.practice.TimeTrackerAnnotation.TrackExecutionTime;
import org.apache.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@RestController
public class pocController {
    private static final Logger log = Logger.getLogger(pocController.class);

    @GetMapping("/status")
    @TrackExecutionTime
    public String getStatus(@RequestParam String name){
        log.info("API check status using param");
        return name;
    }

    @PostMapping(value = "/readFile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = "application/json")
    public List<FileData> fetchData(@RequestParam(value="files") MultipartFile files) throws IOException {
    List<FileData> res=new ArrayList<>();
        
        BufferedReader br=null;
    try{
        br=new BufferedReader(new InputStreamReader(files.getInputStream()));

//        FileReader fr = new FileReader(files);
//        BufferedReader br = new BufferedReader(fr);
        String line;
        while((line= br.readLine())!=null){
            String data[]=line.split(",");
            FileData fileData=new FileData();
            fileData.setName(data[0]);
            fileData.setEmail(data[1]);
            res.add(fileData);
        }
    }catch(Exception e){
    log.info(e);
    }
    finally {
        br.close();
    }
    return res;
    }
}
