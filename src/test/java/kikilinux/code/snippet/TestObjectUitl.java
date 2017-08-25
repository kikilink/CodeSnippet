package kikilinux.code.snippet;

public class TestObjectUitl {
	public static void main(String[] args) throws Throwable {
		ObjectUtil.Student student = new ObjectUtil.Student();
		student.setId("2");
		student.setName("kikilinux");

		String hexString = ObjectUtil.writeObjectToHexString(student);

		ObjectUtil.Student student2 = (ObjectUtil.Student) ObjectUtil
				.readObjectsFromHexString(hexString);
		System.out.println(String.format("ID:%s Name:%s", student2.getId(),
				student2.getName()));
	}
}
