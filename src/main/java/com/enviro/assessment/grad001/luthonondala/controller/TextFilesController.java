package com.enviro.assessment.grad001.luthonondala.controller;

import com.enviro.assessment.grad001.luthonondala.service.TextFilesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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
        try{
            byte[] a =  this.SERVICE.read(id);
            return ResponseEntity.status(HttpStatus.OK)
                    .contentType(MediaType.valueOf("text/xml;charset=UTF-8"))
                    .body(a);
        } catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }

    }

    @PutMapping(value = {"update"}, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public String update(@RequestPart("text") MultipartFile file, @RequestPart("text_id") long id) {
        try{
            return this.SERVICE.update(file, id);
        } catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> delete(@PathVariable long id){
        try{
            String a = this.SERVICE.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body(a);
        } catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
