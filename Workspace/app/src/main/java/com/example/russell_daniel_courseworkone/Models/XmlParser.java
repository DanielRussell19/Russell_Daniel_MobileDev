package com.example.russell_daniel_courseworkone.Models;

import android.util.Log;
import java.util.List;

//Daniel Russell S1707149
//Class used to execute the Threaded Task as a new thread
public class XmlParser
{
    private List<Reading> result;
    private Object lock = new Object();

    public List<Reading> getXML() //XmlParser Start
    {
        try
        {
            synchronized (lock){
                ThreadedTask tsk = new ThreadedTask();
                tsk.setLock(lock);

                tsk.start(); //Starts the method responsible for getting the XML and parsing on a separate thread

                if(tsk.isAlive()){
                    lock.wait();
                }

                result = tsk.getResult();
            }

            return result;
        }
        catch (Exception e)
        {
            Log.println(Log.ERROR, "GetXMLInit", e.toString());
        }

        return null;
    }
}
