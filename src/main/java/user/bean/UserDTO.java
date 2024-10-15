package user.bean;

import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class UserDTO {
	private String name,id,pwd;

	@Override
	public String toString() {
		return name + "\t" + id + "\t" + pwd;
	}
}
