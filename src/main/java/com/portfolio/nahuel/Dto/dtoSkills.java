package com.portfolio.nahuel.Dto;

import javax.validation.constraints.NotBlank;

/**
 *
 * @author nahuel
 */
public class dtoSkills {
    @NotBlank
    private String nombreSkill;
    @NotBlank
    private int porcentajeSkill;
    
    //Constructores

    public dtoSkills() {
    }

    public dtoSkills(String nombreSkill, int porcentajeSkill) {
        this.nombreSkill = nombreSkill;
        this.porcentajeSkill = porcentajeSkill;
    }
    
    //Getters and Setters

    public String getNombreSkill() {
        return nombreSkill;
    }

    public void setNombreSkill(String nombreSkill) {
        this.nombreSkill = nombreSkill;
    }

    public int getPorcentajeSkill() {
        return porcentajeSkill;
    }

    public void setPorcentajeSkill(int porcentajeSkill) {
        this.porcentajeSkill = porcentajeSkill;
    }
    
}
