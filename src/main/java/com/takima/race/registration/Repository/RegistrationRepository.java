package com.takima.race.registration.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.takima.race.registration.Entity.Registration;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Long> {
    public List<Registration> findRunnerIdByRaceId(Long RaceId);
    public int countRunnerIdByRaceId (Long RaceId);
    public List<Registration> findRacesIdByRunnerId(Long RunnerId);

}
