package com.example.movie_backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Getter
@Setter
@Entity
@SuperBuilder(toBuilder = true)
@Table(name = "authority")
public class Authority implements Serializable {
    @NotNull
    @Size(max = 50)
    @Id
    @Column(name = "name", length = 50, nullable = false)
    private String name;

    public Authority(String name) {
        super();
        this.name = name;
    }

    public Authority() {
        super();
    }
}
