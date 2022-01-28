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
    
    public static int book_slot(){
        System.out.println("Enter patient Unique ID: ");
        Scanner ss = new Scanner(System.in);
        String patient_id = ss.nextLine();
        System.out.println("1. Search by area\n2. Search by Vaccine\n3. Exit\nEnter option: ");
        int option = ss.nextInt();
        if(option==1){
            System.out.println("Enter PinCode: ");
            int code = ss.nextInt();
            for(hospital item: array_hospital){
                if(item.pincode == code){
                    System.out.println(item.ID_hospital+" "+item.name);
                }
            }
            System.out.println("Enter hospital id: ");
            ss.nextLine();
            String idhospital = ss.nextLine();
            for(hospital item: array_hospital){
                if(item.ID_hospital.equals(idhospital)){
                    for(citizen patient: array_citizen){
                        if(patient.ID_citizen.equals(patient_id)){
                            if(patient.status.equals("REGISTERED")){
                                boolean slot_available = false;
                                for(int i=0;i<item.array_slots.size();i++){
                                    if(item.array_slots.get(i).qty>0){
                                        System.out.println(i+"-> Day: "+item.array_slots.get(i).day+" Available Qty:"+item.array_slots.get(i).qty+" Vaccine:"+item.array_slots.get(i).vacc);
                                        slot_available = true;
                                    }
                                }
                                if(slot_available==false){
                                    System.out.println("No Slots Available");
                                    return 0;
                                }
                                System.out.println("Choose Slot: ");
                                int slot_index = ss.nextInt();
                                item.array_slots.get(slot_index).qty--;
                                for(citizen people: array_citizen){
                                    if(people.ID_citizen.equals(patient_id)){
                                        people.day_of_vaccination = item.array_slots.get(slot_index).day;
                                        people.doses_given++;
                                        String vaccine_name = item.array_slots.get(slot_index).vacc;
                                        people.vaccine = vaccine_name;
                                        for(vaccine vac: array_vaccine){
                                            if(vac.name.equals(vaccine_name)){
                                                people.total_doses = vac.no_doses;
                                                if(people.doses_given<people.total_doses){
                                                    people.status = "PARTIALLY VACCINATED";
                                                    people.due_date = vac.gap_doses + people.day_of_vaccination;
                                                }else{
                                                    people.status = "FULLY VACCINATED";
                                                    people.due_date = -1;
                                                }
                                                System.out.println(people.name+" vaccinated with "+vac.name);
                                                break;
                                            }
                                        }
                                        break;
                                    }
                                }
                            }
                            else if(patient.status.equals("FULLY VACCINATED")){
                                System.out.println("Patient is fully vaccinated");
                                return 0;
                            }
                            else{
                                boolean slot_availability = false;
                                for(int i=0;i<item.array_slots.size();i++){
                                    if(item.array_slots.get(i).day>=patient.due_date && item.array_slots.get(i).qty>0){
                                        System.out.println(i+"-> Day: "+item.array_slots.get(i).day+" Available Qty:"+item.array_slots.get(i).qty+" Vaccine:"+item.array_slots.get(i).vacc);
                                        slot_availability = true;
                                    }
                                }
                                if(slot_availability==false){
                                    System.out.println("No Slots Available");
                                    return 0;
                                }
                                System.out.println("Choose Slot: ");
                                int slot_index = ss.nextInt();
                                item.array_slots.get(slot_index).qty--;
                                for(citizen people: array_citizen){
                                    if(people.ID_citizen.equals(patient_id)){
                                        people.day_of_vaccination = item.array_slots.get(slot_index).day;
                                        people.doses_given++;
                                        String vaccine_name = item.array_slots.get(slot_index).vacc;
                                        people.vaccine = vaccine_name;
                                        for(vaccine vac: array_vaccine){
                                            if(vac.name.equals(vaccine_name)){
                                                people.total_doses = vac.no_doses;
                                                if(people.doses_given<people.total_doses){
                                                    people.status = "PARTIALLY VACCINATED";
                                                    people.due_date = vac.gap_doses + people.day_of_vaccination;
                                                }else{
                                                    people.status = "FULLY VACCINATED";
                                                    people.due_date = -1;
                                                }
                                                System.out.println(people.name+" vaccinated with "+vac.name);
                                                break;
                                            }
                                        }
                                        break;
                                    }
                                }
                            }
                            break;
                        }
                    }
                    
                    break;
                }
            }
            return 0;
        }
        else if(option==2){
            System.out.println("Enter Vaccine name: ");
            ss.nextLine();
            String vac_name = ss.nextLine();
            int due_date_patient = -1;
            for(citizen peop:array_citizen){
                if(peop.ID_citizen.equals(patient_id)){
                    due_date_patient = peop.due_date;
                    break;
                }
            }
            
            for(hospital hospi: array_hospital){
                for(slots slot: hospi.array_slots){
                    if(slot.vacc.equals(vac_name)){
                        System.out.println(hospi.ID_hospital+" "+hospi.name);
                    }
                }
            }
            
            System.out.println("Enter hospital id: ");
            String hospi_id = ss.nextLine();
            for(hospital hospi: array_hospital){
                if(hospi.ID_hospital.equals(hospi_id)){
                    int i;
                    boolean vaccine_available = false;
                    for(i=0;i<hospi.array_slots.size();i++){
                        if(hospi.array_slots.get(i).vacc.equals(vac_name) && hospi.array_slots.get(i).qty>0 && hospi.array_slots.get(i).day>=due_date_patient) {
                            System.out.println(i+"-> Day:"+hospi.array_slots.get(i).day+" Available Qty:"+hospi.array_slots.get(i).qty+" Vaccine:"+hospi.array_slots.get(i).vacc);
                            vaccine_available = true;
                        }
                    }
                    if(vaccine_available==false){
                        System.out.println("NO Vaccine Slots Available");
                        return 0;
                    }
                    System.out.println("Choose Slot: ");
                    int slot_index = ss.nextInt();
                    hospi.array_slots.get(slot_index).qty--;
                    for(citizen people: array_citizen){
                        if(people.ID_citizen.equals(patient_id)){
                            people.day_of_vaccination = hospi.array_slots.get(slot_index).day;
                            people.doses_given++;
                            String vaccine_name = hospi.array_slots.get(slot_index).vacc;
                            people.vaccine = vaccine_name;
                            for(vaccine vac:array_vaccine){
                                if(vac.name.equals(vaccine_name)){
                                    people.total_doses = vac.no_doses;
                                    if(people.doses_given<people.total_doses){
                                        people.status = "PARTIALLY VACCINATED";
                                        people.due_date = vac.gap_doses + people.day_of_vaccination;
                                    }else{
                                        people.status = "FULLY VACCINATED";
                                        people.due_date = -1;
                                    }
                                    System.out.println(people.name+" vaccinated with "+vac.name);
                                    break;
                                }
                            }
                            break;
                        }
                    }
                    break;
                }
            }
            return 0;
        }else{
            return -1;
        }
    }
