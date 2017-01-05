package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mongodb.DBCursor;

import model.Doctor;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import service.DoctorService;

@RestController
@RequestMapping(value="/doctors")
public class DoctorController {


	@Autowired
	private DoctorService doctorService;
	
	//save doctor entity using POST
	@RequestMapping(method=RequestMethod.POST, 
			consumes=MediaType.APPLICATION_JSON_VALUE)
	public String insert(@RequestBody Doctor doctor){
		
		doctorService.insert(doctor);
		return "insertion completed";
		
	}
	
	
	//get all doctors using GET
	@RequestMapping(method=RequestMethod.GET)
	@ResponseBody
	public JSONArray getAllDoctors(){
		
		return doctorService.getAllDoctors();
		
	}
	
	
	//get doctor by id using GET
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	@ResponseBody
	public JSONObject getDoctorById(@PathVariable("id") int id){
		
		return doctorService.getDoctorById(id);
		
	}
}
