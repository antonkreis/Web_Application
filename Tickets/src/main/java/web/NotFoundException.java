package web;

import java.io.IOException;
import java.util.logging.*;

public class NotFoundException extends RuntimeException{
    private static Logger logger = Logger.getLogger("NotFoundException");
    private static FileHandler fileHandler;

    static {
        try {
            fileHandler = new FileHandler("mylog.txt");
        } catch (IOException e) {
            logger.severe("Cannot create file handler");
            //e.printStackTrace();
        }
    }

    public NotFoundException(String id) {
        logger.severe("Ticket with id <" + id + "> not found");
        SimpleFormatter file = new SimpleFormatter();
        fileHandler.setFormatter(file);
        logger.addHandler(fileHandler);
    }
}
