package com.example.russell_daniel_courseworkone.Models;

import android.util.Log;
import java.util.List;

//Daniel Russell S1707149
//Class used to act as the XML Parser
//Class uses Threaded task which is a class that extends thread to operate on a new thread
public class XmlParser
{
    //variables
    private List<Reading> result;

    //a generic object lock used for async execution, lock is used to match wait and notify from threaded task under the same thread
    private Object lock = new Object();

    public List<Reading> getXML() //XmlParser Start
    {
        try
        {
            synchronized (lock){
                ThreadedTask tsk = new ThreadedTask();
                tsk.setLock(lock); //passes the object lock into tsk to match the lock of this instance

                tsk.start(); //Starts the method responsible for getting the XML and parsing on a separate thread

                if(tsk.isAlive()){ //while the tsk is alive it'll trigger lock.wait to wait for a response from tsk
                    lock.wait();
                } //upon retrieval of notify from tsk, thread wakes up the current thread to continue execution

                result = tsk.getResult(); // retrieves result from tsk, if error return an empty arraylist from try catch
            }

            return result; //returns result
        }
        catch (Exception e)
        {
            Log.println(Log.ERROR, "GetXMLInit", e.toString());
        }

        return null;
    }
}
