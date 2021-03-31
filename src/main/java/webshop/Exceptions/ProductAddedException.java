package webshop.Exceptions;

public class ProductAddedException extends Exception {
        public ProductAddedException(){
            super("-- NOT possible to add this product --");
        }

}
