package webshop.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import webshop.Model.Partner;

import java.util.List;
    @Repository
    public interface PartnerRepo extends JpaRepository<Partner, Long> {
      Partner findByID(long ID);
      List<Partner> findByIsActiveTrue();
    }

