package webshop.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class ASZFController {

//TODO aszf adatvedelmi tajekoztato
        @GetMapping("/ASZF")
        public ResponseEntity<?> getAFSZ(){
            String title="ÁSZF";
            String text=
                    " Lorem ipsum dolor sit amet, consectetur adipiscing elit," +
                    " sed do eiusmod tempor incididunt ut labore et dolore magna aliqua." +
                    " Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi" +
                    " ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit" +
                    " in voluptate velit esse cillum dolore eu fugiat nulla pariatur." +
                    " Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia" +
                    " deserunt mollit anim id est laborum ";
            HashMap<String,String>hmap=new HashMap<>();
            hmap.put("title",title);
            hmap.put("subject",text);
            return ResponseEntity.ok().body(hmap);
        }
    }

