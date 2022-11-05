package org.example.sdk.common.log;

/**
 * 日志默认实现
 */
public class DefaultLogger implements Logger {

    private static DefaultLogger logger = new DefaultLogger();

    /**
     * //TODO 需改造
     * @return
     */
    public static Logger getLogger() {
        return logger;
    }

    @Override
    public void debug(Object msg) {
        //TODO 示范代码，待实现
        System.out.printf("[DEBUG]:%s" + msg);
    }

    @Override
    public void info(Object msg) {
        //TODO 示范代码，待实现
        System.out.printf("[INFO]:%s" + msg);
    }

    @Override
    public void warn(Object msg) {
        //TODO 示范代码，待实现
        System.out.printf("[WARN]:%s" + msg);
    }

    @Override
    public void error(Object msg) {
        //TODO 示范代码，待实现
        System.out.printf("[ERROR]:%s" + msg);
    }
}
