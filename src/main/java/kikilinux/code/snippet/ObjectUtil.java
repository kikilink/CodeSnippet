package kikilinux.code.snippet;

import java.io.*;
import java.util.Date;

/**
 * 提供序列化和反序列化两个能力，以十六进制的字符串为介质。<br/>
 * 序列化的结果是一个byte数组，为了方便传输（如rest接口），需要将其转为一个字符串。<br/>
 * Base64是常见的一种做法。这里使用了另外一种自定义的做法：将每个byte转为十六进制字符串表示。<br/>
 * 具体做法是：将byte变量的二进制值保存下来，放到一个int变量里，再将int转为十六进制字符串。<br/>
 * <strong>int value = b & 0xFF; String hex =
 * Integer.toHexString(value);</strong><br/>
 * 注意，此时byte和int变量的值并不相等，只是将byte的二进制存储到int的低8位数里面，int变量取值范围【0~255】<br/>
 * 即int变量是一个正数，这样能够保证转成十六进制时，字符只有1个或2个，为后面读对象提供读取规律。<br/>
 * 反序列化的过程与上面相反，对于一个十六进制字符串，每2位读取出一个正int值，再通过byte强转，截取低8位，这样就可以获取到byte变量真正的值了。
 */
public class ObjectUtil {

	/**
	 * 将对象序列化为十六进制的字符串
	 *
	 * @param object
	 * @return
	 */
	public static String writeObjectToHexString(Serializable object) {
		ByteArrayOutputStream byteArrayOutputStream = null;
		ObjectOutputStream outputStream = null;

		try {
			// 先序列化为byte数组
			byteArrayOutputStream = new ByteArrayOutputStream(64);
			outputStream = new ObjectOutputStream(byteArrayOutputStream);
			outputStream.writeObject(object);
			outputStream.flush();

			// 再将byte数组转成十六进制字符串
			return bytesToHexString(byteArrayOutputStream.toByteArray());
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		} finally {
			closeIgnoreError(byteArrayOutputStream);
			closeIgnoreError(outputStream);
		}
	}

	/**
	 * 从十六进制的字符串中反序列化对象
	 * 
	 * @param hexString
	 * @return
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	public static Object readObjectsFromHexString(String hexString)
			throws IllegalAccessException, InstantiationException {
		byte[] bytes = new byte[hexString.length() / 2];
		char[] chars = hexString.toCharArray();
		for (int index = 0; index <= hexString.length() - 1; index += 2) {
			int value = Integer.parseInt(String.valueOf(chars[index]), 16) << 4
					| Integer.parseInt(String.valueOf(chars[index + 1]), 16);
			bytes[index / 2] = (byte) value;
		}

		ByteArrayInputStream byteArrayInputStream = null;
		ObjectInputStream inputStream = null;
		try {
			byteArrayInputStream = new ByteArrayInputStream(bytes);
			inputStream = new ObjectInputStream(byteArrayInputStream);
			return inputStream.readObject();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} finally {
			closeIgnoreError(byteArrayInputStream);
			closeIgnoreError(inputStream);
		}

	}

	private static String bytesToHexString(byte[] bytes) {
		StringBuilder sb = new StringBuilder();
		for (byte b : bytes) {
			int value = b & 0xFF;
			String hex = Integer.toHexString(value);
			if (hex.length() < 2) {
				sb.append("0");
			}
			sb.append(hex);
		}
		return sb.toString();
	}

	private static void closeIgnoreError(Closeable closeable) {
		if (null != closeable) {
			try {
				closeable.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
