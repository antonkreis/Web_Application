package App.web;

import java.io.IOException;
import java.util.logging.*;

public class DatabaseException extends RuntimeException{
    private static Logger logger = Logger.getLogger("DatabaseException");
    private static FileHandler fileHandler;

    static {
        try {
            fileHandler = new FileHandler("mylog.txt");
        } catch (IOException e) {
            logger.severe("Cannot create file handler");
            // e.printStackTrace();
        }
    }

    public DatabaseException(){
        logger.severe("Database exception.");
        SimpleFormatter file = new SimpleFormatter();
        fileHandler.setFormatter(file);
        logger.addHandler(fileHandler);
    }
}
