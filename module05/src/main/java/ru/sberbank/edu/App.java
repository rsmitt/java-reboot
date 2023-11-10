package ru.sberbank.edu;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        String latitudeGradus = "55(45'00'')";
        String[] latitudeGradusArray = latitudeGradus.split("[(')]");
        for (String latitude : latitudeGradusArray) {
            System.out.println(latitude);
            System.out.println(Integer.parseInt(latitude));
        }
        System.out.println(latitudeGradusArray.length);

        GeoPosition g = new GeoPosition("-58(01'07'')", "55");
        System.out.println(g.getLatitude());
    }
}
