package pl.mariusz.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="zadania")
public class Zadanie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String nazwa;
    @Column
    private String status;

    @ManyToOne
    @JoinColumn(name = "technik_id", nullable = false)
    private Technik technik;

    public Zadanie() {
    }

    public Zadanie(String nazwa) {
        this.nazwa = nazwa;
        this.status = "NOWE";
    }

    public Long getId() {
        return id;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Technik getTechnik() {
        return technik;
    }

    public void setTechnik(Technik technik) {
        this.technik = technik;
    }
}
