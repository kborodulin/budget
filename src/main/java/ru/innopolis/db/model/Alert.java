package ru.innopolis.db.model;

import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Objects;

@Repository
public class Alert {
    private Long id;
    private LocalDate alertDate;
    private Long alertInitId;
    private Long familyId;
    private Long familyMemberId;
    private boolean status;
    private boolean isAlertSignProc;

    public Alert() {
    }

    public Alert(Long id, LocalDate alertDate, Long alertInitId, Long familyId, Long familyMemberId, boolean status, boolean isAlertSignProc) {
        this.id = id;
        this.alertDate = alertDate;
        this.alertInitId = alertInitId;
        this.familyId = familyId;
        this.familyMemberId = familyMemberId;
        this.status = status;
        this.isAlertSignProc = isAlertSignProc;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getAlertDate() {
        return alertDate;
    }

    public void setAlertDate(LocalDate alertDate) {
        this.alertDate = alertDate;
    }

    public Long getAlertInitId() {
        return alertInitId;
    }

    public void setAlertInitId(Long alertInitId) {
        this.alertInitId = alertInitId;
    }

    public Long getFamilyId() {
        return familyId;
    }

    public void setFamilyId(Long familyId) {
        this.familyId = familyId;
    }

    public Long getFamilyMemberId() {
        return familyMemberId;
    }

    public void setFamilyMemberId(Long familyMemberId) {
        this.familyMemberId = familyMemberId;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isAlertSignProc() {
        return isAlertSignProc;
    }

    public void setAlertSignProc(boolean alertSignProc) {
        isAlertSignProc = alertSignProc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Alert)) return false;
        Alert alert = (Alert) o;
        return Objects.equals(id, alert.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Alerts{" +
                "id=" + id +
                ", alertDate=" + alertDate +
                ", alertInitId=" + alertInitId +
                ", familyId=" + familyId +
                ", familyMemberId=" + familyMemberId +
                ", status=" + status +
                ", isAlertSignProc=" + isAlertSignProc +
                '}';
    }


}
