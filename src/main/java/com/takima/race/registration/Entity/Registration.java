package com.takima.race.registration.Entity;

import java.sql.Date;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Registration {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long runnerId;
    private Long raceId;
    private Date registrationDate;

    
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
            Registration registration = (Registration) o;
        return Objects.equals(id, registration.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    //Constructeurs
    public Registration (Long idRace, Long idRunner, Date date){
        this.runnerId = idRunner;
        this.raceId = idRace;
        this.registrationDate = date;
    }

    public Registration (){
    }

    //Getters et setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRunnerId() {
        return runnerId;
    }

    public void setRunnerId(Long runnerId) {
        this.runnerId = runnerId;
    }

    public Long getRaceId() {
        return raceId;
    }

    public void setRaceId(Long raceId) {
        this.raceId = raceId;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }


}
