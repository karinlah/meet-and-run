package finalproj.com.corun.Class;

/**
 * Created by Karini on 4/10/2016.
 */
public class Pace {

    public int sec;
    public int min;

    public Pace(int m , int s){
        sec = s;
        min = m;
    }

    public int getVal(){
        return (sec+min);
    }

    @Override
    public String toString() {
        return (min + ":" +sec);
    }
}
