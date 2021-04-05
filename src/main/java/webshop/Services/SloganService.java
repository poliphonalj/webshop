package webshop.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import webshop.Model.Slogan;
import webshop.Repository.SloganRepo;

import java.util.Random;

@Service
@Transactional
public class SloganService {
    private SloganRepo sloganRepo;

    @Autowired
    public SloganService(SloganRepo sloganRepo) {
        this.sloganRepo = sloganRepo;
    }

    public Slogan getRandomSlogan() {
        int amount = sloganRepo.findAll().size();
        Random r = new Random();
        int i = r.nextInt(amount);
        return sloganRepo.findSloganByIDAndIsActiveTrue(i);
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


}
