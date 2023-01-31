package ru.netology.javacore;

import java.util.ArrayList;

public class Logger {

    private static final Logger logger = new Logger();
    ArrayList<InitJson> logList = new ArrayList<>();

    private Logger(){}

    public static Logger getInstance(){
        return logger;
    }

    public void log(InitJson jsonInit){
        logList.add(jsonInit);
    }

    public InitJson getLog(int logId){
        return logList.get(logId);
    }

    public InitJson getLastLog(){
        return logList.get(logList.size()-1);
    }

    public void logRemote(){
        logList.remove(logList.size()-1);
    }
}
