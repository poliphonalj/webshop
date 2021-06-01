package webshop.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webshop.Model.WantEmailNews;
import webshop.Repository.WantEmailRepo;

import java.util.ArrayList;
import java.util.List;

@Service
public class WantEmailService {
    WantEmailRepo wantEmailRepo;

    @Autowired
    public WantEmailService(WantEmailRepo wantEmailRepo) {
        this.wantEmailRepo = wantEmailRepo;
    }

    public List<String> getAllWhoWantsEmailNews(){
        List<String>emails =new ArrayList<>();
        List <WantEmailNews> list=wantEmailRepo.findWantEmailNewsByActiveTrue();
        for (WantEmailNews actualuser : list) {
            emails.add(actualuser.getEmail());
        }
        return emails;
    }

    public void save(WantEmailNews w){
        wantEmailRepo.saveAndFlush(w);
    }

    public void remove(WantEmailNews w){
        w.setActive(false);
        wantEmailRepo.saveAndFlush(w);
    }

    public void reactivate(String email){
      WantEmailNews w= wantEmailRepo.findWantEmailNewsByEmail(email);
        w.setActive(true);
        wantEmailRepo.saveAndFlush(w);
    }

    public WantEmailNews wantEmailNewsByEmail(String email){
        return wantEmailRepo.findWantEmailNewsByEmail(email);
    }
}
