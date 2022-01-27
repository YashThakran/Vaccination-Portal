package assignment1;
public class citizen {
    String name;
    int Age;
    String ID_citizen;
    String status;
    int day_of_vaccination;
    int due_date;
    String vaccine;
    int doses_given;
    int total_doses;
    citizen(String name ,int Age ,String ID_citizen){
        this.name = name;
        this.Age = Age;
        this.ID_citizen = ID_citizen;
        this.status = "REGISTERED";
        doses_given=0;
        total_doses=0;
        due_date = -1;
//         commit
    }
}
