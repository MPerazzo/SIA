package ar.edu.itba.sia;


import ar.edu.itba.sia.core.Dispatcher;
import ar.edu.itba.sia.utils.Parser;

public class Main
{
    public static void main( String[] args )  {
        new Dispatcher(new Parser("configFile.txt"));
    }
}
