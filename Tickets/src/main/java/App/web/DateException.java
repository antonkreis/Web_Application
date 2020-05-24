package App.web;

import java.io.IOException;
import java.util.logging.*;

public class DateException extends RuntimeException{
    private static Logger logger = Logger.getLogger("NotFoundException");
    private static FileHandler fileHandler;

    static {
        try {
            fileHandler = new FileHandler("mylog.txt");
        } catch (IOException e) {
            logger.severe("Cannot create file handler");
           // e.printStackTrace();
        }
    }

    public DateException(){
        logger.severe("Wrong date");
        SimpleFormatter file = new SimpleFormatter();
        fileHandler.setFormatter(file);
        logger.addHandler(fileHandler);
    }
}
