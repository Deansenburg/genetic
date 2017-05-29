package helper;

public class StringToBinary {

	public String getBinaryString(String s) {
		byte[] bytes = s.getBytes();
		StringBuilder binary = new StringBuilder();
		for (byte b : bytes) {
			int val = b;
			for (int i = 0; i < 8; i++) {
				binary.append((val & 128) == 0 ? 0 : 1);
				val <<= 1;
			}
		}
		return binary.toString();
	}

	public String getString(String s)
	{
		String newS = "";
		for (int i=0;i<s.length();i+=8)
		{
			String chr = s.substring(i, i+8);
			char c = (char)Integer.parseInt(chr, 2);
			newS+=c;
		}
		return newS;
	}
}
