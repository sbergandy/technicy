package pl.mariusz.http;

import java.util.ArrayList;
import java.util.List;

public class TechnikView {

    private String imie;
    private List<ZadanieView> zadania;

    public TechnikView(String imie) {
        this.imie = imie;
        this.zadania = new ArrayList<>();
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public List<ZadanieView> getZadania() {
        return zadania;
    }
}
