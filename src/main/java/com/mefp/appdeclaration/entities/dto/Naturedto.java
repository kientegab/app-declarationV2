package com.mefp.appdeclaration.entities.dto;

public class Naturedto {
    private Long totalPoids;
    private Long totalValeur;

    public Naturedto(Long totalPoids, Long totalValeur) {
        this.totalPoids = totalPoids;
        this.totalValeur = totalValeur;
    }

    public Long getTotalPoids() {
        return totalPoids;
    }

    public void setTotalPoids(Long totalPoids) {
        this.totalPoids = totalPoids;
    }

    public Long getTotalValeur() {
        return totalValeur;
    }

    public void setTotalValeur(Long totalValeur) {
        this.totalValeur = totalValeur;
    }
}
