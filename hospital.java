package assignment1;
import java.util.*;

public class hospital {
    String name;
    int pincode;
    String ID_hospital;
    List<slots> array_slots = new ArrayList<slots>();
    hospital(String Name, int Pincode){
        this.name = Name;
        this.pincode = Pincode;
    }
}
