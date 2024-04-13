package com.enviro.assessment.grad001.luthonondala.controller;

import com.enviro.assessment.grad001.luthonondala.service.TextFilesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin
@RestController
@RequestMapping("enviro/")
public class TextFilesController {
    @Autowired
    private TextFilesService SERVICE;

    @PostMapping(value = {"create"}, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<String> create(@RequestPart("text")MultipartFile file){
        try{
            String a = this.SERVICE.create(file);
            return ResponseEntity.status(HttpStatus.OK).body(a);
        } catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @GetMapping("read/{id}")
    public ResponseEntity<?> read(@PathVariable long id){
        byte[] a =  this.SERVICE.read(id);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("text/xml;charset=UTF-8"))
                .body(a);
    }




}
