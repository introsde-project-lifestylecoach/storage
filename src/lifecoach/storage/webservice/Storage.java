package lifecoach.storage.webservice;

import lifecoach.localdb.webservice.Person;
import lifecoach.localdb.webservice.Measure;
import lifecoach.localdb.webservice.MeasureType;
import lifecoach.localdb.webservice.Goal;
import lifecoach.localdb.webservice.GoalType;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.WebResult;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;

@WebService
@SOAPBinding(style = Style.DOCUMENT, use=Use.LITERAL) //optional
public interface Storage 
{
	/* Person */
    
	@WebMethod(operationName="readPerson")
    @WebResult(name="person") 
    public Person readPerson(@WebParam(name="personId") int id);

    @WebMethod(operationName="createPerson")
    @WebResult(name="personId") 
    public int addPerson(@WebParam(name="person", targetNamespace="http://webservice.storage.lifecoach/") Person person);

    @WebMethod(operationName="updatePerson")
    @WebResult(name="personId") 
    public int updatePerson(@WebParam(name="person", targetNamespace="http://webservice.storage.lifecoach/") Person person);

    @WebMethod(operationName="deletePerson")
    @WebResult(name="result") 
    public int deletePerson(@WebParam(name="personId") int id);
    
    
    /* Measure */
    
    @WebMethod(operationName="getMeasureHistory")
    @WebResult(name="measureList") 
    public List<Measure> getMeasures(@WebParam(name="personId") int pId, @WebParam(name="measureTypeId") String typeMeasure);
    
    @WebMethod(operationName="getLastMeasure")
    @WebResult(name="measureList") 
    public List<Measure> getLastMeasure(@WebParam(name="personId") int pIdS);
    
    @WebMethod(operationName="readMeasure")
    @WebResult(name="measure") 
    public Measure readMeasure(@WebParam(name="personId") int pId, @WebParam(name="measureTypeId") String typeMeasure, @WebParam(name="measureId") int mId);
    
    @WebMethod(operationName="createMeasure")
    @WebResult(name="measureId") 
    public int addMeasure(@WebParam(name="personId") int pId, @WebParam(name="measure", targetNamespace="http://webservice.localdb.lifecoach/") Measure measure);

    @WebMethod(operationName="updateMeasure")
    @WebResult(name="measureId") 
    public int updateMeasure(@WebParam(name="personId") int pId, @WebParam(name="measure", targetNamespace="http://webservice.localdb.lifecoach/") Measure measure);
    
    @WebMethod(operationName="deleteMeasure")
    @WebResult(name="result") 
    public int deleteMeasure(@WebParam(name="measureId") int id);
    
    
    /* MeasureType */
    
    @WebMethod(operationName="getMeasureTypeList")
    @WebResult(name="measureTypeList") 
    public List<MeasureType> getMeasureType();
    
    @WebMethod(operationName="readMeasureType")
    @WebResult(name="measureType") 
    public MeasureType readMeasureType(@WebParam(name="goalId") int id);
    
    
    /* Goal */
    
    @WebMethod(operationName="getGoalHistory")
    @WebResult(name="goalList") 
    public List<Goal> getGoal(@WebParam(name="personId") int pId, @WebParam(name="measureTypeId") String typeMeasure);
    
    @WebMethod(operationName="readGoal")
    @WebResult(name="goal") 
    public Goal readGoal(@WebParam(name="personId") int pId, @WebParam(name="measureTypeId") String typeMeasure, @WebParam(name="goalId") int gId);
    
    @WebMethod(operationName="createGoal")
    @WebResult(name="goalId") 
    public int addGoal(@WebParam(name="personId") int pId, @WebParam(name="goal", targetNamespace="http://webservice.localdb.lifecoach/") Goal goal);

    @WebMethod(operationName="updateGoal")
    @WebResult(name="goalId") 
    public int updateGoal(@WebParam(name="personId") int pId, @WebParam(name="goal", targetNamespace="http://webservice.localdb.lifecoach/") Goal goal);
    
    @WebMethod(operationName="deleteGoal")
    @WebResult(name="result") 
    public int deleteGoal(@WebParam(name="goalId") int id);
    
    
    /* GoalType */
    
    @WebMethod(operationName="getGoalTypeList")
    @WebResult(name="goalTypeList") 
    public List<GoalType> getGoalType();
    
    @WebMethod(operationName="readGoalType")
    @WebResult(name="goalType") 
    public GoalType readGoalType(@WebParam(name="goalId") int id);
}
