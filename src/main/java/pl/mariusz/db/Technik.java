package pl.mariusz.db;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="technicy")
public class Technik {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String imie;

    @OneToMany(mappedBy = "technik", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Zadanie> zadania;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String name) {
        this.imie = name;
    }

    public void dodajZadanie(Zadanie zadanie) {
        if (this.zadania == null) {
            this.zadania = new ArrayList<>();
        }
        zadanie.setTechnik(this);
        this.zadania.add(zadanie);
    }

    public void usunZadanie(Zadanie zadanie) {
        this.zadania.remove(zadanie);
    }

    public List<Zadanie> getZadania() {
        return zadania;
    }
}
