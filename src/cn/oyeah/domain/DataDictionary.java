package cn.oyeah.domain;

/**
 * 数据字典(权限,角色)
 * @author Administrator
 *
 */
public class DataDictionary {

     	private int id;
		private String name;
		private String value;

		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
		
}
