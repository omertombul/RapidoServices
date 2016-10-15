package inm5001.rapidoservices;

/**
 * Created by Admin on 2016-10-14.
 * Idée originale de SamTebbs33
 * http://stackoverflow.com/questions/26313084/how-to-set-my-own-message-in-my-custom-exception-in-java-that-can-be-reteived-my
 */

public class MyException extends Exception{

    public String message;

    public MyException(String message){
        this.message = message;
    }

    // Overrides Exception's getMessage()
    @Override
    public String getMessage(){

        return message;
    }
    /*
    // Exemple d'utilisation
    public static void main(String[] args){
        MyException e = new MyException("some message");
        System.out.println(e.getMessage());
    }
    */
}
