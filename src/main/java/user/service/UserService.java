package user.service;

import user.bean.UserDTO;

import java.util.List;
import java.util.Map;

public interface UserService {

	public String getExistId(String id);

	public void write(UserDTO userDTO);

	public Map<String, Object> list(String pg);

	public void update(UserDTO userDTO);

	public UserDTO getUser(String id);

	public void delete(String id);
}
