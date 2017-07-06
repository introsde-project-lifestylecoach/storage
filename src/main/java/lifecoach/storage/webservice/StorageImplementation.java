package lifecoach.storage.webservice;

import ws.adaptor.Adaptor;
import ws.adaptor.AdaptorService;
import ws.adaptor.Bmi;
import ws.localdb.*;

import java.util.List;

import javax.jws.WebService;

//Service Implementation

@WebService(endpointInterface = "lifecoach.storage.webservice.Storage",
    serviceName="StorageService")
public class StorageImplementation implements Storage 
{
	PeopleService service;
	People people;
	AdaptorService service_adaptor;
	Adaptor adaptor;
	
	public void init()
	{
		service = new PeopleService();
        people = service.getPeopleImplementationPort();
	}
	
	public void init_adaptor()
	{
		service_adaptor = new AdaptorService();
        adaptor = service_adaptor.getAdaptorImplementationPort();
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
    	init();
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
    public List<Measure> getSomeMeasures(int pId, String measureType, int nMeasure) {
    	init();
    	System.out.println("Reading Measure by pId = " + pId + ", type = " + measureType);
        List<Measure> p = people.getSomeMeasure(pId, measureType, nMeasure);
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
    	init();
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
    public Measure getLastMeasureByType(int pId, String type) {
    	init();
    	System.out.println("Reading Last Measure by pId = " + pId + " and type = " + type);
        Measure p = people.readLastMeasureByType(pId, type);
        if (p!=null) {
            System.out.println("---> Found Measure by id " + p.getIdMeasure());
        } else {
            System.out.println("---> Didn't find any Measure");
        }
        return p;
    }

    @Override
    public int addMeasure(int pId, Measure measure) {
    	init();
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
    	init();
    	System.out.println("Update Measure with id = " + measure.getIdMeasure());  	
    	return people.updateMeasure(pId, measure);
    }
    
    @Override
    public int deleteMeasure(int id) {
    	init();
    	System.out.println("Delete Measure with id = " + id);
    	return people.deleteMeasure(id);
    }
    
    
    /* Manage MeasureType */
    
    @Override
    public MeasureType readMeasureType(int id) {
        init();
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
    	init();
    	System.out.println("Read MeasureType List");
        return people.getMeasureTypeList();
    }
    
    @Override
    public int getIdMeasureTypeByType(String type) {
    	init();
    	System.out.println("Read MeasureType List");
        return people.getIdMeasureTypeByType(type);
    }
    
        
    /* Manage Goal */
    
    @Override
    public List<Goal> getGoals(int pId) {
        init();
    	System.out.println("Reading Goal by id = " + pId);
        List<Goal> m = people.getGoals(pId);
        if (m!=null) {
            System.out.println("---> Found Goal for id = " + pId);
        } else {
            System.out.println("---> Didn't find any Goal with id = " + pId);
        }
        return m;
    }
    
    @Override
    public Goal readGoal(int pId, String measureType, int gId) {
        init();
    	System.out.println("Reading Goal by pId = " + pId + ", mId = " + gId + ", type = " + measureType);
        return people.readGoal(pId, measureType, gId);
    }

    @Override
    public List<Goal> getGoal(int pId, String measureType) {
    	init();
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
    	init();
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
    	init();
    	System.out.println("Update Goal with id = " + goal.getIdGoal());
    	return people.updateGoal(pId, goal);
    }
    
    @Override
    public int deleteGoal(int id) {
    	init();
    	System.out.println("Delete Goal with id = " + id);
    	return people.deleteGoal(id);
    }

     @Override
    public Goal getGoalByTitle(int pId, String title){
	init();
	System.out.println("Goal by id = " + pId + " title = " + title);

	return people.getGoalByTitle(pId, title);
    }
    
    /* Manage GoalType */
    
    @Override
    public GoalType readGoalType(int id) {
        init();
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
    	init();
    	System.out.println("Read GoalType List");
        return people.getGoalTypeList();
    }
    
    
    /* Manage Adaptor*/
    
    @Override
    public Bmi getBmi(float weight, float height, char sex, int age, float waist, float hip) {
    	init_adaptor();    	
    	System.out.println("Calcolate Bmi with: weight=" + weight + ", height=" + height + ", sex=" + sex
          		 + ", age=" + age + ", waist=" + waist + ", hip=" + hip);
        Bmi b = adaptor.getBmi(weight, height, sex, age, waist, hip);
        if (b!=null) {
            System.out.println("---> Bmi for = "+weight+" and "+height);
        } else {
            System.out.println("---> Error in calculating Bmi for = "+weight+" and "+height);
        }
        return b;
    }
}
