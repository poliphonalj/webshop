package webshop.Exceptions;


public class UserExistException extends Exception {
    public UserExistException(){
        super("-- User already esists --");
    }

}
