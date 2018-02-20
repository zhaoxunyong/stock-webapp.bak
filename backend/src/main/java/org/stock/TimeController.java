package org.stock;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * http://samchu.logdown.com/posts/1300192-example-springboot-thymeleaf-vue
 * 
 * @author Dave.zhao
 *
 */
@RestController
public class TimeController {

	@GetMapping(path = "time")
	public Map getName() {
		Map map = new HashMap();
		map.put("name", "Sam");
		map.put("time", new Date());
		return map;
	}
}
