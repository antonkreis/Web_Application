package App.web;

import java.io.IOException;
import java.util.logging.*;

public class AlreadyExistException extends RuntimeException{
    private static Logger logger = Logger.getLogger("AlreadyExistException");
    private static FileHandler fileHandler;

    static {
        try {
            fileHandler = new FileHandler("mylog.txt");
        } catch (IOException e) {
            logger.severe("Cannot create file handler");
            // e.printStackTrace();
        }
    }

    public AlreadyExistException(){
        logger.severe("The ticket with this id already exists");
        SimpleFormatter file = new SimpleFormatter();
        fileHandler.setFormatter(file);
        logger.addHandler(fileHandler);
    }
}
