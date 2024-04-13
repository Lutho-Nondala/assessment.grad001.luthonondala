package com.enviro.assessment.grad001.luthonondala.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class TextFiles {
    @Id
    @GeneratedValue
    private long id;
    @Column
    private String name;
    @Column
    private String type;
    @Column(length = 50000000)
    private byte[] fileByte;

    public TextFiles(){

    }

    public TextFiles(Builder builder){
        this.id = builder.id;
        this.name = builder.name;
        this.type = builder.type;
        this.fileByte = builder.fileByte;
    }

    public static class Builder{
        private long id;
        private String name;
        private String type;
        private byte[] fileByte;

        public Builder id(long id){
            this.id = id;
            return this;
        }

        public Builder name(String name){
            this.name = name;
            return this;
        }

        public Builder type(String type){
            this.type = type;
            return this;
        }

        public Builder fileByte(byte[] fileByte){
            this.fileByte = fileByte;
            return this;
        }

        public Builder copy(TextFiles file){
            this.id = file.id;
            this.name = file.name;
            this.type = file.type;
            this.fileByte = file.fileByte;
            return this;
        }

        public TextFiles build() {
            return new TextFiles(this);
        }
    }
}
