package com.core;

import java.util.logging.Logger;

import java.io.Serializable;

public class SingletonClass extends Super implements Serializable,Cloneable{
    private static Logger logger = Logger.getLogger(SingletonClass.class.getName());
    //  eager initialization causes memory leaks
    //  eager initialization
    //private static volatile SingletonClass sSoleInstance = new SingletonClass();
    //volatile for fully initialized instance
    private static volatile SingletonClass sSoleInstance;
    //private constructor.
    // Suppresses default constructor, ensuring non-instantiability.
    private SingletonClass() {
        //Ensuring the Single instance creation from reflection api.
        if (sSoleInstance != null) {
            throw new RuntimeException("Use getInstance() method to get the single instance of this class.");
        }
    }

   /* public static SingletonClass getInstance() {
        return sSoleInstance;
    }*/

/*    public static SingletonClass getInstance(){ //Lazy initialization
        if (sSoleInstance == null){ //if there is no instance available... create new one
            sSoleInstance = new SingletonClass();
        }

        return sSoleInstance;
    }*/

    public static SingletonClass getInstance() {
        //Double check locking pattern
        if (sSoleInstance == null) { //Check for the first time

            synchronized (SingletonClass.class) {   //Check for the second time.
                //if there is no instance available... create new one
                if (sSoleInstance == null) sSoleInstance = new SingletonClass();
            }
        }

        return sSoleInstance;
    }



    //Make singleton from serialize and deserialize operation.
    //For Singleton most of the time Serialization not required
    protected Object readResolve() {
        return getInstance();
    }

     @Override
    protected Object clone() throws CloneNotSupportedException {
         super.clone();
        return getInstance();
         // return new CloneNotSupportedException();
       //  return super.clone();
     }
}
