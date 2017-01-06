package dao;

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

	// insert a doctor
	public void insert(Doctor doctor) {
		MongoClient mongoc = new MongoClient("localhost", 27017);
		DB db = mongoc.getDB("test");
		DBCollection coll = db.getCollection("myCollection");

		BasicDBObject object = new BasicDBObject();
		object.put("id", doctor.getId());
		object.put("name", doctor.getName());
		coll.insert(object);

	}

	// get all doctors
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

	// get doctor by id
	public JSONObject getDoctorById(int id) {

		MongoClient mongoc = new MongoClient("localhost", 27017);
		DB db = mongoc.getDB("test");
		DBCollection coll = db.getCollection("myCollection");

		BasicDBObject bobj1 = new BasicDBObject();
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
		BasicDBObject queryobj = new BasicDBObject();
		queryobj.put("id", doctor.getId());
		DBCursor cursor = (DBCursor) coll.find(queryobj);

		while (cursor.hasNext()) {
			BasicDBObject bobj = (BasicDBObject) cursor.next();
			bobj.put("id", doctor.getId());
			bobj.put("name", doctor.getName());
			coll.update(queryobj, bobj);
		}

	}
	
	//delete a doctor
	public void deleteDoctor(int id) {
		MongoClient mongoc = new MongoClient("localhost", 27017);
		DB db = mongoc.getDB("test");
		DBCollection coll = db.getCollection("myCollection");
		BasicDBObject queryobj = new BasicDBObject();
		queryobj.put("id",id);
		DBCursor cursor = (DBCursor) coll.find(queryobj);

		while (cursor.hasNext()) {
			BasicDBObject bobj = (BasicDBObject) cursor.next();
			coll.remove(bobj);
		}

	}
	
	//delete all doctors
	public void deleteAllDoctor() {
		MongoClient mongoc = new MongoClient("localhost", 27017);
		DB db = mongoc.getDB("test");
		DBCollection coll = db.getCollection("myCollection");
		DBCursor cursor = (DBCursor) coll.find();
		BasicDBObject bobj=new BasicDBObject();
		coll.remove(bobj);
		

	}
	
}
