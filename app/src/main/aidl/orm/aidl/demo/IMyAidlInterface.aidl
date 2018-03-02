// IMyAidlInterface.aidl
package orm.aidl.demo;

 // Declare any non-default types here with import statements

 interface IMyAidlInterface {
     int Sum(int num1,int num2);
     /**
      * Demonstrates some basic types that you can use as parameters
      * and return values in AIDL.
      */
     void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
             double aDouble, String aString);
 }
