package webshop.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import webshop.Model.Partner;
import webshop.Repository.PartnerRepo;

import java.util.List;

@Service
public class PartnerService {
    PartnerRepo pRepo;

    @Autowired
    public PartnerService(PartnerRepo pRepo) {
        this.pRepo = pRepo;
    }

    @Transactional
    public long newPartner(Partner p){
        p.setActive(true);
        pRepo.save(p);
        return p.getID();
    }
    @Transactional
    public long removePartner(long ID){
        Partner p=pRepo.findByID(ID);
        p.setActive(false);
        pRepo.saveAndFlush(p);
        return ID;
    }

    @Transactional
    public List<Partner> getPartners(){
        return pRepo.findByIsActiveTrueAndUsFalse();
    }


    @Transactional
    public List<Partner> getUsPartners(){
        return pRepo.findByIsActiveTrueAndUsTrue();
    }
}
