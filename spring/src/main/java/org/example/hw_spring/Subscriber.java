package org.example.hw_spring;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;
import java.util.Date;

@Entity
public class Subscriber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long subscId;

    private String subscName;
    private String subscSurname;
    private String msisdn;
    private Long tariffId;
    private LocalDate startDate;

    // Getters and Setters
    public Long getSubscId() {
        return subscId;
    }

    public void setSubscId(Long subscId) {
        this.subscId = subscId;
    }

    public String getSubscName() {
        return subscName;
    }

    public void setSubscName(String subscName) {
        this.subscName = subscName;
    }

    public String getSubscSurname() {
        return subscSurname;
    }

    public void setSubscSurname(String subscSurname) {
        this.subscSurname = subscSurname;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public Long getTariffId() {
        return tariffId;
    }

    public void setTariffId(Long tariffId) {
        this.tariffId = tariffId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

}
