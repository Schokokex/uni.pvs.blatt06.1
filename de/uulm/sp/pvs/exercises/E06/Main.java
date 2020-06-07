package de.uulm.sp.pvs.exercises.E06;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedReader;
import java.io.FileReader;
import de.uulm.sp.pvs.exercises.E06.models.Lad;
import de.uulm.sp.pvs.exercises.E06.models.Lass;
import de.uulm.sp.pvs.exercises.E06.models.Clique;
import de.uulm.sp.pvs.exercises.E06.models.Person;
import java.io.FileNotFoundException;


public class Main {
	public static void main(String[] args) throws Exception {
		final Gson gson = new GsonBuilder()
		.registerTypeAdapter(Person.class, new PersonDeserializer())
		.create();

		final Lad lad = gson.fromJson(new BufferedReader(new FileReader("resources/Lad.json")), Lad.class);

		final Lass lass = gson.fromJson(new BufferedReader(new FileReader("resources/Lass.json")), Lass.class);

		final Clique clique = gson.fromJson(new BufferedReader(new FileReader("resources/Clique.json")), Clique.class);

		System.out.println(lad);
		System.out.println(lass);
		System.out.println(clique);
	}
}




class PersonDeserializer implements com.google.gson.JsonDeserializer<Person> {
	public Person deserialize(com.google.gson.JsonElement je, java.lang.reflect.Type type, com.google.gson.JsonDeserializationContext jdc) throws com.google.gson.JsonParseException {
		final com.google.gson.JsonObject jo = je.getAsJsonObject();
		final String name = jo.getAsJsonPrimitive("NAME").getAsString();
		if (jo.getAsJsonPrimitive("female").getAsBoolean()){
			return new Lass(name);
		} else {
			return new Lad(name);
		}
	}
}