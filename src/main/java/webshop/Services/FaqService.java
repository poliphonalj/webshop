package webshop.Services;

import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class FaqService {
HashMap<String,String>hMap=new HashMap<>();

    public FaqService() {
    }

    public HashMap<String, String>getFaq() {
        hMap.put("Most találkoztam a Farmfalat.hu weboldallal. Mi is ez?"," Online zöldség és gyümölcs rendelésekkel foglalkozó webshop, összepárosítva a kiszállítással.\n" +
                "    Termékpalettánkon találsz szezonális magyar zöldséget, gyümölcsöt, mézet, teát, cserepes virágot,\n" +
                "    szörpöket, savanyúságot és egyeb exkluzív termékeket pl xxxxxxx");


        hMap.put("Miért jó nekem, ha a FarmFalat-hu webshopon rendelek?" , " Nem kell a piacon sorban állnod, nem kell cipekedned, nem kell feleslegesen a dugóban ülnöd.\n" +
                "    Ezzel szemben kényelmesen bárhonnan és bármikor megrendelheted a kívánt terméket.\n" +
                "    A minőséggel sem kell bajlódnod, hiszen kipróbált partnerekkel állunk kapcsolatban, akik már sokszor bizonyítottak.\n" +
                "    De ha ez sem győzőtt meg, akkor szimplán adj egy esélyt a magyar termelőknek. Termékeink legtöbbje magyar kis\n" +
                "    vállalkozók munkájának gyümölcse:) Kiszállító járműveink naponta kb 200 címet szolgának ki. Ezzel jelentősen\n" +
                "    tehermentesítik környezetünket, hiszen kétszáz személyautó környezet-terhelését mentesítjük egyetlen járművel.");

return hMap;
    }


}
