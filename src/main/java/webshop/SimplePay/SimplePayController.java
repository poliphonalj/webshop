package webshop.SimplePay;

import com.google.common.hash.Hashing;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.HmacAlgorithms;
import org.apache.commons.codec.digest.HmacUtils;
import org.json.simple.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.UUID;

@RestController
public class SimplePayController {

    //start
    //A start a fizetési tranzakció kezdete. Ezen a ponton történik meg a kereskedői
    //rendszerben összegyűjtött tranzakciós adatok továbbítása a SimplePay felé
    //generaálok egyjsont amit a frontend elkuld postkent a linkre?

    @PostMapping("/simplepay/start")
    public void start() throws Exception {
        final String uri = "https://sandbox.simplepay.hu/payment/v2/start";
        RestTemplate restTemplate = new RestTemplate();
        SimplePayment sp = new SimplePayment();
        String salt = UUID.randomUUID().toString().replace("-", "");

        sp.setSalt("806a7a4830351ef68a05f3e2b077fd93");//genertes a random 32 long string
        sp.setMerchant("PUBLICTESTHUF");
        sp.setOrderRef("101010515680194414779");
        sp.setCurrency("HUF");
        sp.setCustomerEmail("sdk_test@otpmobil.com");
        sp.setLanguage("HU");
        sp.setSdkVersion("SimplePayV2.1_Payment_PHP_SDK_2.0.7_190701:dd236896400d7463677a82a47f53e36e");

        String[] array = {"CARD"};
        sp.setMethods(array);
        sp.setTotal(25);
        sp.setTimeout("2019-09-11T16:30:41+00:00");
        sp.setUrl("https://sdk.simplepay.hu/back.php");
        //restTemplate.postForObject(uri, sp, SimplePayment.class);


        //String message = sp.toString();//json
        String merchantKey = "FxDa5w314kLlNseq2sKuVwaqZshZT5d6";//ez secretKey neven van


        final String secretkey = "FxDa5w314kLlNseq2sKuVwaqZshZT5d6";

        //String s=this.encode(secretkey,message);


        final String nonce = "FxDa5w314kLlNseq2sKuVwaqZshZT5d6";
        //final String message = "kacsa";
        final String message = sp.toString();
        SecretKeySpec signingKey = new SecretKeySpec(nonce.getBytes(), "HmacSHA384");
        final Mac mac = Mac.getInstance("HmacSHA384");
        mac.init(signingKey);
        String s=(encode(mac.doFinal(message.getBytes())));  ///ez jo eddigez a kacsa signaturja
        String signature = Base64.getEncoder().encodeToString(s.getBytes());


        System.out.println(s);
        System.out.println("signature: " + signature);
        System.out.println(message);
        System.out.println("salt: " + salt);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Signature", signature);





    }



    private static String encode(final byte[] hash) {
        final StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < hash.length; i++) {
            final String hex = Integer.toHexString(0xff & hash[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }








   /* public  String encode(String key, String data) throws Exception {
       data="kacsa";
        Mac sha384_HMAC = Mac.getInstance("HmacSHA384");
        SecretKeySpec secret_key = new SecretKeySpec(key.getBytes("UTF-8"), "HmacSHA384");
        sha384_HMAC.init(secret_key);

        return sha384_HMAC.doFinal(data.getBytes("UTF-8")).toString();
    }*/




}


