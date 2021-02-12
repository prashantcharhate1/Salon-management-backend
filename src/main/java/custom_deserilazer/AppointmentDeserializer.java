package custom_deserilazer;

import java.io.IOException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.app.pojos.Appointment;
import com.app.pojos.Customer;
import com.app.pojos.Employee;
import com.app.pojos.Service;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

@SuppressWarnings("serial")
public class AppointmentDeserializer extends StdDeserializer<Appointment> {

	public AppointmentDeserializer() {
		this(null);
	}

	public AppointmentDeserializer(Class<?> vc) {
		super(vc);
	}

	@Override
	public Appointment deserialize(JsonParser jp, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		JsonNode node = jp.getCodec().readTree(jp);

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		String appTimeTemp = node.get("appTime").asText();
		String appTimeTemp2 = appTimeTemp + ":00";
		LocalDate appDate = LocalDate.parse(node.get("appDate").asText(), formatter);
		Time appTime = Time.valueOf(appTimeTemp2);

		int service = Integer.parseInt(node.get("service").asText());
		int employee = Integer.parseInt(node.get("employee").asText());
		int customer = Integer.parseInt(node.get("customer").asText());

		return new Appointment(appDate, appTime, new Service(service, null, null),
				new Employee(employee, null, null, null, null, null, null, null, null, null, null, null, null, null),
				new Customer(customer, null, null, null, null, null));
	}

}