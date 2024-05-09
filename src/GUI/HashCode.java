package GUI;

public class HashCode {
	public static String hash(String value) {
		String result = Integer.toString(value.hashCode());
		return result;
	}
}
