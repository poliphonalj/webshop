package webshop.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import webshop.Model.WantEmailNews;

import java.util.List;

@Repository
public interface WantEmailRepo extends JpaRepository<WantEmailNews, Long> {

   List<WantEmailNews> findWantEmailNewsByActiveTrue();
   WantEmailNews findWantEmailNewsByEmail(String email);
}
