package com.enviro.assessment.grad001.luthonondala.service;

import com.enviro.assessment.grad001.luthonondala.entity.TextFiles;
import com.enviro.assessment.grad001.luthonondala.repository.TextFilesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class TextFilesService {
    @Autowired
    private TextFilesRepository REPO;

    public String create(MultipartFile file) throws IOException {
        TextFiles a = this.REPO.save(new TextFiles.Builder()
                        .name(file.getOriginalFilename())
                        .type(file.getContentType())
                        .fileByte(file.getBytes())
                        .build());

        if (a != null){
            return a.getName() + " uploaded successfully.";
        }

        return null;
    }

    public byte[] read(long id){
        byte[] a = this.REPO.findById(id).get().getFileByte();

        if (a != null){
            return a;
        }

        return null;
    }

    public TextFiles getById(long id){
        return this.REPO.findById(id).get();
    }

    public String update(MultipartFile file, long id) throws IOException {
        TextFiles a = this.getById(id);

        TextFiles b = this.REPO.save(new TextFiles.Builder()
                        .copy(a)
                        .name(file.getOriginalFilename())
                        .type(file.getContentType())
                        .fileByte(file.getBytes())
                        .build());

        if (b != null){
            return b.getName() + " updated successfully.";
        }

        return null;
    }

    public String delete(long id){
        try{
            this.REPO.deleteById(id);
            return "Delete Successful";
        } catch (Exception e){
            System.out.println(e.getMessage());
            return "File does not exist";
        }
    }

}
