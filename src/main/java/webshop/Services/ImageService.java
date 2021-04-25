package webshop.Services;

//TODO
//remove image

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import webshop.Model.Product.Image;
import webshop.Model.Product.Product;
import webshop.Repository.ImageRepo;
import webshop.Repository.ProductRepo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

@Service
public class ImageService {

    private final ImageRepo imageRepo;
    private final ProductRepo productRepo;

    @Autowired
    public ImageService(ImageRepo imageRepo, ProductRepo productRepo) {
        this.imageRepo = imageRepo;
        this.productRepo = productRepo;
    }

    @Transactional
    public void addImage(MultipartFile file, long productID) throws IOException {
        Image dbImage = new Image();
        dbImage.setName(file.getName());
        dbImage.setByteFlow(file.getBytes());
        dbImage.setProduct(productRepo.findProductByID(productID));
        dbImage.setProductID(productID);//repo.findimagebyproductid miatt kell

        Product p = productRepo.findProductByID(productID);
        List<Image> list = p.getImageList();
        list.add(dbImage);
        p.setImageList(list);

        imageRepo.saveAndFlush(dbImage);
        productRepo.saveAndFlush(p);

    }
   /* public void addImage(MultipartFile file, long productID, String description, String tooltip) throws IOException {
        Image i = new Image();
        i.setName(file.getOriginalFilename());
        i.setType(file.getContentType());
        i.setByteFlow(this.compressBytes(file.getBytes()));
        //i.setProductID(productID);
        i.setProduct(productRepo.findProductByID(productID));//ez joooooooooooooo
        i.setDescription(description);
        i.setTooltip(tooltip);

        Product p = productRepo.findProductByID(productID);
        List<Image> list = p.getImageList();
        list.add(i);
        p.setImageList(list);

        imageRepo.saveAndFlush(i);
        productRepo.saveAndFlush(p);
    }*/

    @Transactional
    public JSONObject getImageByName(String imageName) throws IOException, DataFormatException {
        Image retrievedImage = imageRepo.findByNameAndIsActiveTrue(imageName);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("succesful", "true");
        jsonObject.put("list", retrievedImage);
        return jsonObject;
    }

    @Transactional
    public JSONObject getImageByID(long ID) throws IOException, DataFormatException {
        Image retrievedImage = imageRepo.findImageByIDAndIsActiveTrue(ID);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("succesful", "true");
        jsonObject.put("list", retrievedImage);
        return jsonObject;
    }

    //returns a list of pics in the JSonObject...if there are more pics
    public JSONObject getImagesByProductID(long productID) {
        List<Image> imageList = imageRepo.findImagesByProductIDAndIsActiveTrue(productID);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("succesful", "true");
        JSONArray list = new JSONArray();
        for (Image actualImage : imageList) {
            list.add(actualImage);
        }
        jsonObject.put("list", list);
        return jsonObject;
    }

//not a really remove, just changing a flag
    public JSONObject removeImagesByProductID(long productID) {
        List<Image> imageList = imageRepo.findImagesByProductIDAndIsActiveTrue(productID);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("succesful", "true");

        for (Image actualImage : imageList) {
            actualImage.setActive(false);
            imageRepo.saveAndFlush(actualImage);
        }

        return jsonObject;
    }






    // compress the image bytes before storing it in the database
    public byte[] compressBytes(byte[] data) throws IOException {
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        outputStream.close();
        return outputStream.toByteArray();
    }

    // uncompress the image bytes before returning it to the angular application
    public byte[] decompressBytes(byte[] data) throws IOException, DataFormatException {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];

        while (!inflater.finished()) {
            int count = inflater.inflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        outputStream.close();
        return outputStream.toByteArray();
    }
}

