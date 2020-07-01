package com.cokreates.grp.hrmservicepmis;

import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApplicationTests {

	@Test
	void validatorTest() {
		JSONObject obj1 = new JSONObject("{}");
		JSONObject obj2 = new JSONObject("{}");
		Schema schema = SchemaLoader.load(obj1);
		schema.validate(obj1);
	}

}
