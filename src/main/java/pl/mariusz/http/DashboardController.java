package pl.mariusz.http;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pl.mariusz.db.Technik;
import pl.mariusz.db.TechnikDB;
import pl.mariusz.db.Zadanie;

import java.util.ArrayList;
import java.util.List;

@Controller
public class DashboardController {

    @Autowired
    private TechnikDB technikDB;

    @GetMapping("/test")
    public ModelAndView getDashboard() {
        Iterable<Technik> technicy = this.technikDB.findAll();
        return wyswietlStronęGłówną();
    }

    @PostMapping("/dodaj-zadanie")
    public ModelAndView dodajZadanie(@ModelAttribute DodajZadanie dodajZadanie) {
        Iterable<Technik> technicy = this.technikDB.findAll();

        boolean found = false;
        for (Technik t : technicy) {
            if (t.getImie().equals(dodajZadanie.getNazwaTechnika())) {
                t.dodajZadanie(new Zadanie(dodajZadanie.getZadanie()));
                this.technikDB.save(t);
                found = true;
                break;
            }
        }
        if (!found) {
            Technik nowy = new Technik();
            nowy.setImie(dodajZadanie.getNazwaTechnika());
            if (dodajZadanie.getZadanie() != null && !dodajZadanie.getZadanie().equals("")) {
                nowy.dodajZadanie(new Zadanie(dodajZadanie.getZadanie()));
            }
            this.technikDB.save(nowy);
        }

        return wyswietlStronęGłówną();
    }

    @PostMapping("/usun-zadanie")
    public ModelAndView usunZadanie(@RequestParam String nazwa, @RequestParam String technik) {
        Iterable<Technik> technicy = this.technikDB.findAll();
        for (Technik t : technicy) {
            if (t.getImie().equals(technik)) {
                Zadanie doUsuniecia = null;
                for (Zadanie z : t.getZadania()) {
                    if (z.getNazwa().equals(nazwa)) {
                        doUsuniecia = z;
                    }
                }
                if (doUsuniecia != null) {
//                    t.getZadania().remove(doUsuniecia);
                    t.usunZadanie(doUsuniecia);
                    this.technikDB.save(t);
                }
            }
        }
        return wyswietlStronęGłówną();
    }

    private ModelAndView wyswietlStronęGłówną() {
        Iterable<Technik> technicy = this.technikDB.findAll();
        ModelAndView modelAndView = new ModelAndView("test");
        List<TechnikView> technicyView = new ArrayList<>();
        for (Technik technik : technicy) {
            technicyView.add(new TechnikView(technik.getImie()));
        }
        modelAndView.addObject("technicy", technicy);
        modelAndView.addObject("dodajZadanie", new DodajZadanie());

        return modelAndView;
    }

}
