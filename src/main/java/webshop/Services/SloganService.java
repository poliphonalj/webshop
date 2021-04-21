package webshop.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import webshop.Model.Slogan;
import webshop.Repository.SloganRepo;

import java.util.List;
import java.util.Random;

//TODO ID on heroku server generated 5 multipls
//random slogan choice is taylormade for tgis bug


@Service
@Transactional
public class SloganService {
    private SloganRepo sloganRepo;

    @Autowired
    public SloganService(SloganRepo sloganRepo) {
        this.sloganRepo = sloganRepo;
    }

    public Slogan getRandomSlogan() {
        int size = sloganRepo.findAll().size();

        if (size != 0) {
            Random r = new Random();
            long i = r.nextInt(size);
            i=i*10+5;
           //Slogan s = sloganRepo.findSloganByID(4);
            System.out.println(i);
            return sloganRepo.findSloganByID(i);
        } else {
            return new Slogan("nincs megjelenitheto szoveg");
        }

    }

    public void addSlogan(String slogan) {
        Slogan s = new Slogan();
        s.setText(slogan);
        sloganRepo.saveAndFlush(s);
    }

    public void removeSlogan(long ID) {
        Slogan s = new Slogan();
        s.setActive(false);
        sloganRepo.saveAndFlush(s);
    }

    public List<Slogan> listSlogan() {
        return sloganRepo.findAll();
    }


}
