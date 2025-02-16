package com.beowulfe.hap.impl.json;

import java.io.ByteArrayOutputStream;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;

import com.beowulfe.hap.characteristics.EventableCharacteristic;
import com.beowulfe.hap.impl.http.HttpResponse;

public class EventController {

	public HttpResponse getMessage(int accessoryId, int iid, EventableCharacteristic changed) throws Exception {
		JsonArrayBuilder characteristics = Json.createArrayBuilder();
		
		JsonObjectBuilder characteristicBuilder = Json.createObjectBuilder();
		characteristicBuilder.add("aid", accessoryId);
		characteristicBuilder.add("iid", iid);
		changed.supplyValue(characteristicBuilder);
		characteristics.add(characteristicBuilder.build());
	
		JsonObject data = Json.createObjectBuilder().add("characteristics", characteristics).build();
		
		try(ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
			JsonWriter writer = Json.createWriter(baos);
			writer.write(data);
			writer.close();
			byte[] dataBytes = baos.toByteArray();
		
			return new EventResponse(dataBytes);
		}
		
	}

}
