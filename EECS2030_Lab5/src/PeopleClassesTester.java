import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PeopleClassesTester {
	public static void main(String[] args) {
		Person person1 = new Person ("Person One", new Date()); //dob = current date and time; 
		System.out.println ("(Testing getDob()) " + person1.getDob());
		System.out.println (person1);
		
		Student student1 = new Student ("Student One", new Date(), "123456"); 
		System.out.println (student1);
		
		GraduateStudent grad1 = new GraduateStudent("Grad One", new Date(), "123457"); 
		grad1.setThesisSupervisor("Supervisor One");
		System.out.println ("(Testing getSupervisor()) " + grad1.getThesisSupervisor());
		System.out.println (grad1);
		
		//Employee employee = new Employee(); //this should be impossible 
		Employee employeeS = new SalariedEmployee("Employee Salaried", new Date(), "987");
		((SalariedEmployee) employeeS).setSalary(100000);
		System.out.println (employeeS);

		Employee employeeH = new HourlyEmployee("Employee Hourly", new Date(), "988"); 
		//employeeH.setHourlyRate(65.0); //this should be impossible 
		System.out.println (employeeH);
		
		List <Person> list = new ArrayList<>();
		list.add(person1);
		list.add(student1);
		list.add(grad1);
		list.add(employeeS);
		list.add(employeeH);
		System.out.println ("List printing: polymorphism");
		System.out.println (list);
	}
}
