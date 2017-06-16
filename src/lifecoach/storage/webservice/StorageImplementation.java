package lifecoach.storage.webservice;

import lifecoach.localdb.webservice.PeopleService;
import lifecoach.localdb.webservice.People;
import lifecoach.localdb.webservice.Person;
import lifecoach.localdb.webservice.Person.HealthProfile;
import lifecoach.localdb.webservice.Measure;
import lifecoach.localdb.webservice.MeasureType;
import lifecoach.localdb.webservice.Goal;
import lifecoach.localdb.webservice.GoalType;

import java.util.List;

import javax.jws.WebService;

//Service Implementation

@WebService(endpointInterface = "lifecoach.storage.webservice.Storage",
    serviceName="StorageService")
public class StorageImplementation implements Storage 
{
	PeopleService service;
	People people;
	
	public void init()
	{
		service = new PeopleService();
        people = service.getPeopleImplementationPort();
	}
	
	
	/* Manage Person*/
	
    @Override
    public Person readPerson(int id) {
    	init();    	
        System.out.println("Reading Person by id = "+id);
        Person p = people.readPerson(id);
        if (p!=null) {
            System.out.println("---> Found Person by id = "+id+" => "+p.getFirstname());
        } else {
            System.out.println("---> Didn't find any Person with  id = "+id);
        }
        return p;
    }

    @Override
    public int addPerson(Person person) {
    	init();
    	System.out.println("Save Person with id = " + person.getIdPerson());
    	return people.createPerson(person);
    }

    @Override
    public int updatePerson(Person person) {
    	init();
    	if(person == null)
    	{
    		System.out.println("Zio billy");
    		return 1;
    	}    	
    	System.out.println("Update Person with id = " + person.getIdPerson());
        return people.updatePerson(person);
    }

    @Override
    public int deletePerson(int id) {
    	init();
    	System.out.println("Delete Person with id = " + id);           
        return people.deletePerson(id);
    }
    
    
    /* Manage Measure */
    
    @Override
    public Measure readMeasure(int pId, String measureType, int mId) {
        init();
    	System.out.println("Reading Measure by pId = " + pId + ", mId = " + mId + ", type = " + measureType);
        List<Measure> p = people.getMeasureHistory(pId, measureType);
        if (p!=null) {
            System.out.println("---> Found Measure");
            for(Measure m : p)
            {
            	System.out.println(m.getIdMeasure() + " " + m.getValue());
            }	
        } else {
            System.out.println("---> Didn't find any Measure");
        }
        return p.get(0);
    }

    @Override
    public List<Measure> getMeasures(int pId, String measureType) {
    	System.out.println("Reading Measure by pId = " + pId + ", type = " + measureType);
        List<Measure> p = people.getMeasureHistory(pId, measureType);
        if (p!=null) {
            System.out.println("---> Found Measure");
            for(Measure m : p)
            {
            	System.out.println(m.getIdMeasure() + " " + m.getValue());
            }
        } else {
            System.out.println("---> Didn't find any Measure");
        }
        return p;
    }
    
    @Override
    public List<Measure> getLastMeasure(int pId) {
    	System.out.println("Reading Last Measure by pId = " + pId);
        List<Measure> p = people.readLastMeasure(pId);
        if (p!=null) {
            System.out.println("---> Found Measure");
            for(Measure m : p)
            {
            	System.out.println(m.getIdMeasure() + " " + m.getValue());
            }
        } else {
            System.out.println("---> Didn't find any Measure");
        }
        return p;
    }

    @Override
    public int addMeasure(int pId, Measure measure) {
    	System.out.println("Save Measure with id = " + measure.getIdMeasure());
        
    	if(measure.getMeasureType()==null)
    	{
    		System.out.println("QUi");
    	}
    	else
    	{
    		System.out.println("QUa " + measure.getMeasureType().getType());
    	}
    	
        return people.createMeasure(pId, measure);
    }

    @Override
    public int updateMeasure(int pId, Measure measure) {
    	System.out.println("Update Measure with id = " + measure.getIdMeasure());  	
    	return people.updateMeasure(pId, measure);
    }
    
    @Override
    public int deleteMeasure(int id) {
    	System.out.println("Delete Measure with id = " + id);
    	return people.deleteMeasure(id);
    }
    
    
    /* Manage MeasureType */
    
    @Override
    public MeasureType readMeasureType(int id) {
        System.out.println("Reading MeasureType by id = " + id);
        MeasureType m = people.readMeasureType(id);
        if (m!=null) {
            System.out.println("---> Found MeasureType by id = " + id + " => " + m.getType());
        } else {
            System.out.println("---> Didn't find any MeasureType with  id = " + id);
        }
        return m;
    }

    @Override
    public List<MeasureType> getMeasureType() {
    	System.out.println("Read MeasureType List");
        return people.getMeasureTypeList();
    }
    
    
    /* Manage Goal */
    
    @Override
    public Goal readGoal(int pId, String measureType, int gId) {
        System.out.println("Reading Goal by pId = " + pId + ", mId = " + gId + ", type = " + measureType);
        return people.readGoal(pId, measureType, gId);
    }

    @Override
    public List<Goal> getGoal(int pId, String measureType) {
    	System.out.println("Reading Goal by pId = " + pId + ", type = " + measureType);
        List<Goal> p = people.getGoalHistory(pId, measureType);
        if (p!=null) {
            System.out.println("---> Found Goal");
            for(Goal m : p)
            {
            	System.out.println(m.getIdGoal() + " " + m.getValue());
            }
        } else {
            System.out.println("---> Didn't find any Goal");
        }
        return p;
    }

    @Override
    public int addGoal(int pId, Goal goal) {
    	System.out.println("Save Goal with id = " + goal.getIdGoal());
        
    	if(goal.getMeasureType()==null)
    	{
    		System.out.println("QUi");
    	}
    	else
    	{
    		System.out.println("QUa " + goal.getMeasureType().getType());
    	}
    	
    	return people.createGoal(pId, goal);
    }

    @Override
    public int updateGoal(int pId, Goal goal) {
    	System.out.println("Update Goal with id = " + goal.getIdGoal());
    	return people.updateGoal(pId, goal);
    }
    
    @Override
    public int deleteGoal(int id) {
    	System.out.println("Delete Goal with id = " + id);
    	return people.deleteGoal(id);
    }
    
    
    /* Manage GoalType */
    
    @Override
    public GoalType readGoalType(int id) {
        System.out.println("Reading GoalType by id = " + id);
        GoalType m = people.readGoalType(id);
        if (m!=null) {
            System.out.println("---> Found GoalType by id = " + id + " => " + m.getType());
        } else {
            System.out.println("---> Didn't find any GoalType with  id = " + id);
        }
        return m;
    }

    @Override
    public List<GoalType> getGoalType() {
    	System.out.println("Read GoalType List");
        return people.getGoalTypeList();
    }
}
