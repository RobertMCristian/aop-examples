package com.example.aop_examples.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Movie {
    private String title;
    private int year;
}
