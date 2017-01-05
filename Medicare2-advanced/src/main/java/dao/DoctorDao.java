package dao;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import model.Doctor;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

@Component
public class DoctorDao {

	// insert
	public void insert(Doctor doctor) {
		MongoClient mongoc = new MongoClient("localhost", 27017);
		DB db = mongoc.getDB("test");
		DBCollection coll = db.getCollection("myCollection");

		BasicDBObject object = new BasicDBObject();
		object.put("id", doctor.getId());
		object.put("name", doctor.getName());
		coll.insert(object);

	}

	// getalldoctors
	public JSONArray getAllDoctors() {

		MongoClient mongoc = new MongoClient("localhost", 27017);
		DB db = mongoc.getDB("test");
		DBCollection coll = db.getCollection("myCollection");

		DBCursor cursor = coll.find();

		JSONArray jsonarrray = new JSONArray();

		while (cursor.hasNext()) {
			BasicDBObject bobj = (BasicDBObject) cursor.next();
			JSONObject jobj = new JSONObject();
			jobj.put("id", bobj.get("id"));
			jobj.put("name", bobj.get("name"));

			jsonarrray.add(jobj);
		}

		return jsonarrray;

	}

	// getbyid
	public JSONObject getDoctorById(int id) {

		MongoClient mongoc = new MongoClient("localhost", 27017);
		DB db = mongoc.getDB("test");
		DBCollection coll = db.getCollection("myCollection");

		BasicDBObject bobj1=new BasicDBObject();
		bobj1.append("id", id);
		
		
		DBCursor cursor = coll.find(bobj1);
		JSONObject jobj = new JSONObject();
		while (cursor.hasNext()) {
			BasicDBObject bobj2 = (BasicDBObject) cursor.next();
			jobj.put("id", bobj2.get("id"));
			jobj.put("name", bobj2.get("name"));

		
		}

		return jobj;

	}

	
	// update doctor by id
		public void updateDoctor(Doctor doctor) {
			MongoClient mongoc = new MongoClient("localhost", 27017);
			DB db = mongoc.getDB("test");
			DBCollection coll = db.getCollection("myCollection");
			/*
		DBCursor cursor= (DBCursor) coll.findOne(doctor.getId());
		
		while(cursor.hasNext()){
			
			BasicDBObject bobj=(BasicDBObject) cursor.next();
			bobj.put("id",doctor.getId());
			bobj.put("name",doctor.getName());
		}*/
		
		BasicDBObject bobj=new BasicDBObject();
		bobj.put("id", doctor.getId());
		//convert doctor object to dbobject
		BasicDBObject dbobj=new BasicDBObject();
		dbobj.put("name", doctor.getName());
		coll.update(bobj,dbobj);
		
		}
}
