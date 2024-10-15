package user.controller;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import user.bean.UserDTO;
import user.dao.UserDAO;
import user.service.UserService;

@Controller
public class UserController {
	@Autowired
	private UserService userService;

	@RequestMapping(value="/writeForm", method = RequestMethod.GET)
	public String writeForm() {
		return "/user/writeForm";
	}

	@ResponseBody
	@RequestMapping(value="/getExistId", method=RequestMethod.POST)
	public String getExistId(String id) {
		return userService.getExistId(id);
	}


	@ResponseBody
	@RequestMapping(value="/write", method=RequestMethod.POST)
	public void write(@ModelAttribute UserDTO userDTO) {
		userService.write(userDTO);
	}

	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String list(@RequestParam(required = false, defaultValue = "1") String pg, Model model) {
		Map<String, Object> map2 = userService.list(pg);

		map2.put("pg",pg);
		model.addAttribute("map2", map2);

		//model.addAttribute("list", map2.get("list"));
		//model.addAttribute("userPaging", map2.get("userPaging"));
		return "/user/list"; //=> /WEB-INF/user/list.jsp
	}

	@RequestMapping(value = "/updateForm", method = RequestMethod.GET)
	public String updateForm(@RequestParam("id")String id,@RequestParam String pg, ModelMap modelMap) {
		UserDTO userDTO = userService.getUser(id);

		modelMap.put("pg",pg);
		modelMap.put("userDTO", userDTO);
		return "/user/updateForm";
	}

	@ResponseBody
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public void update(@ModelAttribute UserDTO userDTO) {
		userService.update(userDTO);
	}

	@ResponseBody
	@RequestMapping(value = "/delete",method = RequestMethod.GET)
	public void delete(@RequestParam("id")String id) {
		UserDTO userDTO = userService.getUser(id);
		userService.delete(id);
	}
}
