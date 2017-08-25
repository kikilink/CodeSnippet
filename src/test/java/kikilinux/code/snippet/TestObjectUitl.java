package kikilinux.code.snippet;

import java.io.Serializable;

public class TestObjectUitl {
	public static void main(String[] args) throws Throwable {
		Student student = new Student();
		student.setId("2");
		student.setName("kikilinux");

		String hexString = ObjectUtil.writeObjectToHexString(student);

		Student student2 = (Student) ObjectUtil
				.readObjectsFromHexString(hexString);
		System.out.println(String.format("ID:%s Name:%s", student2.getId(),
				student2.getName()));
	}

	public static class Student implements Serializable {
		private String id;

		private String name;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getId() {
			return id;

		}

		public void setId(String id) {
			this.id = id;
		}
	}
}
