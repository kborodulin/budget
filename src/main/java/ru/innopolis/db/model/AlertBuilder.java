package ru.innopolis.db.model;

import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public class AlertBuilder {
    private Long id;
    private LocalDate alertDate;
    private Long alertInitId;
    private Long familyId;
    private Long familyMemberId;
    private boolean status;
    private boolean isAlertSignProc;

    public AlertBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public AlertBuilder withAlertDate(LocalDate alertDate) {
        this.alertDate = alertDate;
        return this;
    }

    public AlertBuilder withAlertInitId(Long alertInitId) {
        this.alertInitId = alertInitId;
        return this;
    }

    public AlertBuilder withFamilyId(Long familyId) {
        this.familyId = familyId;
        return this;
    }

    public AlertBuilder withFamilyMemberId(Long familyMemberId) {
        this.familyMemberId = familyMemberId;
        return this;
    }

    public AlertBuilder withStatus(boolean status) {
        this.status = status;
        return this;
    }

    public AlertBuilder withIsAlertSignProc(boolean isAlertSignProc) {
        this.isAlertSignProc = isAlertSignProc;
        return this;
    }

    public Alert build() {
        return new Alert(id, alertDate, alertInitId, familyId, familyMemberId, status, isAlertSignProc);
    }
}