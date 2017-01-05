package service;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import dao.DoctorDao;
import model.Doctor;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

@Service
public class DoctorService {

	@Autowired
	private DoctorDao doctorDao;
	
	

	public void insert(Doctor doctor){
		
		this.doctorDao.insert(doctor);
		
	}
	
	
	public JSONArray getAllDoctors(){
		
		return doctorDao.getAllDoctors();
		
	}
	
public JSONObject getDoctorById(int id){
		
		return doctorDao.getDoctorById(id);
		
	}
}
