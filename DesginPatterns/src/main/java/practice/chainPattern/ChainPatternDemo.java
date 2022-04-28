package practice.chainPattern;

import practice.chainPattern.impl.ConsoleLogger;
import practice.chainPattern.impl.ErrorLogger;
import practice.chainPattern.impl.FileLogger;

/**
 * @author wenhoulai
 */
public class ChainPatternDemo {
	private static AbstractLogger getChainOfLoggers() {
		AbstractLogger errLogger = new ErrorLogger(AbstractLogger.ERROR);
		AbstractLogger fileLogger = new FileLogger(AbstractLogger.DEBUG);
		AbstractLogger consoleLogger = new ConsoleLogger(AbstractLogger.INFO);

		errLogger.setNextLogger(fileLogger);
		fileLogger.setNextLogger(consoleLogger);

		return errLogger;
	}

	public static void main(String[] args) {
		AbstractLogger loggerChain = getChainOfLoggers();

		loggerChain.logMessage(AbstractLogger.INFO, "This is an information.");

		loggerChain.logMessage(AbstractLogger.DEBUG, "This is a debug information.");

		loggerChain.logMessage(AbstractLogger.ERROR, "This is an error information.");
	}
}
