///ready and works fine


package webshop.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import webshop.Model.Slogan;
import webshop.Repository.SloganRepo;
import java.util.Collections;
import java.util.List;


@Service
@Transactional
public class SloganService {
    private SloganRepo sloganRepo;

    @Autowired
    public SloganService(SloganRepo sloganRepo) {
        this.sloganRepo = sloganRepo;
    }

    public Slogan getRandomSlogan() {
       List<Slogan>list= sloganRepo.findSlogansByActiveIsTrue();
        if(list.size()!=0){
       Collections.shuffle(list);
            return list.get(0);
        }
        else {
            return new Slogan("nincs megjelenitheto szoveg");
        }

    }

    public void addSlogan(String slogan) {
        Slogan s = new Slogan();
        s.setText(slogan);
        sloganRepo.saveAndFlush(s);
    }

    public void removeSlogan(long ID) {
        Slogan s =sloganRepo.findSloganByID(ID);
        s.setActive(false);
        sloganRepo.saveAndFlush(s);
    }

    public List<Slogan> listSlogan() {
        return sloganRepo.findAll();
    }

    public void reactivate(long ID){
        Slogan s =sloganRepo.findSloganByID(ID);
        s.setActive(true);
        sloganRepo.saveAndFlush(s);
    }

}
