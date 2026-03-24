package com.takima.race.race.DTO;

public class RaceDto {
    private Long count;

    //Constructor
    public RaceDto(Long count) {
        this.count = count;
    }    

    //Getter et Setter
    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
