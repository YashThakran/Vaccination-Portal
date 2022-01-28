// package assignment1;
import java.util.*;
public class mainclass {

    public static List<vaccine> array_vaccine = new ArrayList<vaccine>();
    public static List<hospital> array_hospital = new ArrayList<hospital>();
    public static List<citizen> array_citizen = new ArrayList<citizen>();
    
    public static void add_vaccine(String nam, int doses, int gap){
        vaccine obj = new vaccine(nam,doses,gap);
        array_vaccine.add(obj);
        System.out.println("Vaccine Name: "+obj.name+", Number of Doses: "+obj.no_doses+", Gap Between Doses: "+obj.gap_doses);
    }

    public static void register_hospital(String Name, int Pincode){
        hospital obj_hospital = new hospital(Name , Pincode);
        String unique_id = String.valueOf(array_hospital.size()+1);
        while(unique_id.length()<6){
            unique_id = "0"+unique_id;
        }
        obj_hospital.ID_hospital = unique_id;
        array_hospital.add(obj_hospital);

        System.out.println("Hospital name: "+obj_hospital.name+", PinCode: "+obj_hospital.pincode+", Unique ID: "+obj_hospital.ID_hospital);
    }

    public static void register_citizen(String Name, int Age, String ID){
        if(Age<18){
            System.out.println("Citizen Name: "+Name+", Age: "+Age+", Unique ID: "+ID);
            System.out.println("Onle above 18 are allowed");
            return;
        }
        citizen obj = new citizen(Name , Age , ID);
        array_citizen.add(obj);
        System.out.println("Citizen Name: "+obj.name+", Age: "+obj.Age+", Unique ID: "+obj.ID_citizen);
    }

    public static void create_slots(String hospital_id, int quantity, String vaccine, int day){
        slots obj = new slots(hospital_id, quantity, vaccine, day);
        for(hospital item: array_hospital){
            if(item.ID_hospital.equals(hospital_id)){
                item.array_slots.add(obj);
                System.out.println("Slot added by Hospital "+hospital_id+" for Day: "+day+", Availabe Quantity: "+quantity+" of Vaccine "+vaccine);
                return;
            }
        }
    }
