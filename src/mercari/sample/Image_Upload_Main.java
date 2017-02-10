package mercari.sample;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Image_Upload_Main {
	static final Logger logger = Logger.getLogger(Logger.class.getName());
	  static final String filePath = "log";

	  public static void main(String[] args) {
	    try {

	      FileHandler fileHandler = new FileHandler(filePath, false);
	      fileHandler.setFormatter(new SimpleFormatter());
	      logger.addHandler(fileHandler);
	      logger.setLevel(Level.FINE);

	      ConsoleHandler consoleHandler = new ConsoleHandler();
	      consoleHandler.setLevel(Level.CONFIG);
	      logger.addHandler(consoleHandler);

	      logger.setUseParentHandlers(false);

	      logger.finest("FNST");
	      logger.finer("FNR");
	      logger.fine("FN");
	      logger.config("CFG");
	      logger.info("INF");
	      logger.warning("WNG");
	      logger.severe("SVR");

	      throw new IOException();

	    } catch (FileNotFoundException e) {
	      e.printStackTrace();
	    } catch (SecurityException e) {
	      e.printStackTrace();
	    } catch (IOException e) {

	      logger.log(Level.FINER, "エラー発生", e);
	      System.out.println("エラー");

	    }
	  }
}
