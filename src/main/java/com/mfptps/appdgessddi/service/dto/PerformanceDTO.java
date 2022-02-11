package com.mfptps.appdgessddi.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PerformanceDTO {

    private double efficacite ;
    private double efficience ;
    private double gouvernance ;
    private double impact ;
    private double pgs ;
    private double pgm;
    private String appreciation;
    private boolean global;
    
}
