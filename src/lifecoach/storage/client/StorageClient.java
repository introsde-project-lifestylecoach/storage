package lifecoach.storage.client;

import lifecoach.localdb.webservice.Person;
import lifecoach.localdb.webservice.Person.HealthProfile;
import lifecoach.storage.webservice.Storage;
import lifecoach.localdb.webservice.Measure;
import lifecoach.localdb.webservice.MeasureType;
import lifecoach.localdb.webservice.Goal;
import lifecoach.localdb.webservice.GoalType;
import lifecoach.localdb.webservice.People;

import java.net.URL;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

public class StorageClient
{
    public static void main(String[] args) throws Exception 
    {
        URL url = new URL("http://127.0.1.1:6902/ws/storage?wsdl");
        //1st argument service URI, refer to wsdl document above
        //2nd argument is service name, refer to wsdl document above
        QName qname = new QName("http://webservice.storage.lifecoach/", "StorageService");
        Service service = Service.create(url, qname);
        Storage storage = service.getPort(Storage.class);
        
        
        System.out.println("Lifecoach");
        System.out.println("=================================================");
                
        int i, j, x;
        
        /*  readPerson() */
        System.out.println("1: List of perosn");
        
        Person pList = storage.readPerson(1);       

        System.out.println("\t--> " + pList.getFirstname() + " " + pList.getLastname());        
        
        System.out.println("=================================================");
        
        
        /* readPerson(Long id) */
        Person p = storage.readPerson(1);
        
        System.out.println("2: Person by id");
        System.out.println("\t--> " + p.getFirstname() + " " + p.getLastname());
        
        System.out.println("=================================================");
        
        
        /* updatePerson(Person p) */
        System.out.println("3: Update person");
        
        System.out.println("\t--> Before: " + p.getFirstname() + " " + p.getLastname());
        
        p.setFirstname("Nuovo"); 
        i = storage.updatePerson(p);
        
        p = storage.readPerson(i);        
        System.out.println("\t--> After: " + p.getFirstname() + " " + p.getLastname());
        
        System.out.println("=================================================");
        
        /* createPerson(Person p) */
        System.out.println("4: Create person");
        
        Person p1 = makePerson(7, "Chuck", "Norris", "1945-01-01");
        
        //List<Measure> mList = new ArrayList<Measure>();
        //mList.add(makeMeasure(7, (float) 1.72, "1978-09-02", p1, makeMeasureType(1, people)));
        //mList.add(makeMeasure(8, 75, "1978-09-02", p1, makeMeasureType(2, people)));               
        //p1.setMeasure(mList);
        HealthProfile h = new HealthProfile();
        List<Measure> mList = h.getMeasure();
        mList.add(makeMeasure((float) 1.72, "1978-09-02", p1, makeMeasureType(1, storage)));
        mList.add(makeMeasure((float)75, "1978-09-02", p1, makeMeasureType(2, storage)));               
        p1.setHealthProfile(h);
        
        i = storage.addPerson(p1);
        
        p = storage.readPerson(i);
        System.out.println("\t--> " + p.getFirstname() + " " + p.getLastname());
        
        /* TODO: Inserire anche le misure con la nuova persona
	        Measure m3 = people.readMeasure(7, makeMeasureType(1, people).getType(), 7);
	        System.out.println("\t\t--> " + m3.getIdMeasure() + " " + m3.getValue());
	        
	        m3 = people.readMeasure(7, makeMeasureType(2, people).getType(), 8);
	        System.out.println("\t\t--> " + m3.getIdMeasure() + " " + m3.getValue());
        */
        
        System.out.println("=================================================");
                
        /* deletePerson(Long id) */        
        System.out.println("5: Delete person 7");
               
        storage.deletePerson(i);
       
        System.out.println("=================================================");
        
        /* readPersonHistory(Long id, String measureType) */
        System.out.println("6: Measure History");
        
        List<Measure> mH = storage.getMeasures(1, "height");
        
        for(Measure m: mH)
        {
        	System.out.println("\t--> " + m.getIdMeasure() + " " + m.getValue());
        }
        
        System.out.println("=================================================");
        
        /* readMeasureTypes() */
        System.out.println("7: List of MeasureType");
        
        List<MeasureType> mT = storage.getMeasureType();
        
        for(MeasureType m: mT)
        {
        	System.out.println("\t--> " + m.getIdMeasureType() + " " + m.getType());
        }
        
        System.out.println("=================================================");
        
        /* readPersonMeasure(Long id, String measureType, Long mid) */
        System.out.println("8: Read measure");
        
        Measure m1 = storage.readMeasure(1, "height", 1);
        System.out.println("\t--> " + m1.getIdMeasure() + " " + m1.getValue());
        
        System.out.println("=================================================");
        
        /* savePersonMeasure(Long id, Measure m) */
        System.out.println("9: Create measure");
        
        Person p2 = storage.readPerson(1);
        i = storage.addMeasure(1, makeMeasure((float) 1.72, "2011-12-09", p2, makeMeasureType(1, storage)));
        
        Measure m4 = storage.readMeasure(1, makeMeasureType(1, storage).getType(), i);
        System.out.println("\t--> " + m4.getIdMeasure() + " " + m4.getValue());
        
        j = m4.getIdMeasure();
        
        System.out.println("=================================================");
        
        /* updatePersonMeasure(Long id, Measure m) */
        System.out.println("10: Update measure");
        
        Measure m2 = storage.readMeasure(1, "height", 1);
        System.out.println("\t--> Before: " + m2.getIdMeasure() + " " + m2.getValue());
        
        m2.setValue((float) 1.81);
        i = storage.updateMeasure(1, m2);
        
        m2 = storage.readMeasure(1, "height", i);
        
        System.out.println("\t--> After: " + m2.getIdMeasure() + " " + m2.getValue());
        
        System.out.println("=================================================");
               
        /* readGoalHistory(Long id, String measureType) */
        System.out.println("11: Goal History");
        
        List<Goal> gH = storage.getGoal(1, "height");
        
        for(Goal g: gH)
        {
        	System.out.println("\t--> " + g.getIdGoal() + " " + g.getValue());
        }
        
        System.out.println("=================================================");
        
        /* readGoalTypes() */
        System.out.println("12: List of GoalType");
        
        List<GoalType> gT = storage.getGoalType();
        
        for(GoalType g: gT)
        {
        	System.out.println("\t--> " + g.getIdGoalType() + " " + g.getType());
        }
        
        System.out.println("=================================================");
        
        /* readPersonGoal(Long id, String measureType, Long gid) */
        System.out.println("13: Read goal");
        
        Goal g1 = storage.readGoal(1, "height", 1);
        System.out.println("\t--> " + g1.getIdGoal() + " " + g1.getValue());
        
        System.out.println("=================================================");
        
        /* savePersonGoal(Long id, Goal m) */
        System.out.println("14: Create goal");
        
        Person p3 = storage.readPerson(1);
        i = storage.addGoal(1, makeGoal((float) 1.85, "2011-12-09", p2, makeMeasureType(1, storage), makeGoalType(1, storage)));
        
        Goal g2 = storage.readGoal(1, makeMeasureType(1, storage).getType(), i);
        System.out.println("\t--> " + g2.getIdGoal() + " " + g2.getValue());
        
        x = g2.getIdGoal();
        
        System.out.println("=================================================");
        
        /* updatePersonGoal(Long id, Measure m) */
        System.out.println("15: Update goal");
        
        Goal g3 = storage.readGoal(1, "height", 1);
        System.out.println("\t--> Before: " + g3.getIdGoal() + " " + g3.getValue());
        
        g3.setValue((float) 1.81);
        i = storage.updateGoal(1, g3);
        
        g3 = storage.readGoal(1, "height", i);
        
        System.out.println("\t--> After: " + g3.getIdGoal() + " " + g3.getValue());
        
        System.out.println("=================================================");
        
        // Restore the initial conditions
        p = storage.readPerson(1);
        p.setFirstname("Giorgio"); 
        storage.updatePerson(p);
        
        m2.setValue((float) 1.7);
        i = storage.updateMeasure(1, m2);
        
        storage.deleteMeasure(j);
        
        g3.setValue((float) 1.83);
        i = storage.updateGoal(1, g3);
        
        storage.deleteGoal(x);
    }
       
    public static Person makePerson(int id, String fname, String lname, String date)
    {
    	Person p = new Person();
        
    	p.setIdPerson(id);
        p.setFirstname(fname);
        p.setLastname(lname);
        // p.setBirthdate(date);
        
    	return p;
    }
    
    public static Measure makeMeasure(float value, String date, Person person, MeasureType mType)
    {
    	Measure m = new Measure();
    	
    	// m.setIdMeasure(id);
    	m.setValue(value);
    	m.setDate(date);
    	m.setMeasureType(mType);
    	
    	return m;
    }
    
//    public static Measure makeMeasure(int id, float value, String date, Person person, MeasureType mType)
//    {
//    	Measure m = new Measure();
//    	
//    	m.setIdMeasure(id);
//    	m.setValue(value);
//    	m.setDate(date);
//    	m.setMeasureType(mType);
//    	
//    	return m;
//    }
    
    public static MeasureType makeMeasureType(int id, Storage storage)
    {    	
    	return storage.readMeasureType(id);
    }
    
    public static Goal makeGoal(float value, String date, Person person, MeasureType mType, GoalType gType)
    {
    	Goal m = new Goal();
    	
    	// m.setIdGoal(id);
    	m.setValue(value);
    	m.setDate(date);
    	m.setMeasureType(mType);
    	m.setGoalType(gType);
    	
    	return m;
    }
    
    public static GoalType makeGoalType(int id, Storage storage)
    {    	
    	return storage.readGoalType(id);
    }
}