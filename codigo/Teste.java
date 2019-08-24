public class Teste{
	public static void main(String args[]){
		java.util.Scanner INPUT = new java.util.Scanner(System.in);

		Integer a;
		Float c;
		Boolean e;

		System.out.println("Meu primeiro programa");

		System.out.println("Digite o valor de A");

		a = INPUT.nextInt();

		System.out.println("Digite o valor de C");

		c = INPUT.nextFloat();

		System.out.println("Digite o valor de E");

		e = INPUT.nextBoolean();

		System.out.println("");

		a = 5555;

		c = 42.0f;

		e = true;

		if(e!=true) {

		System.out.println("dentro do if");

		System.out.println("dentro do if");

		} else {

		System.out.println("senao");

		System.out.println("senao");

		}

		System.out.println("");

		System.out.println("Os valores que foram digitados:");

		System.out.println(a);

		System.out.println(c);

		System.out.println(e);

		System.out.println("Tudo otimo!");

	}
}
