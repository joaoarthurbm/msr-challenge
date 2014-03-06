package main;


public class Test {

	public static void main(String[] args) {
		String flat = "hey, how are you? i am fine! thanks for asking. gegege";
		flat = flat.replaceAll("\\p{Punct}", " ");
		System.out.println(flat);
		
		
	}

}
