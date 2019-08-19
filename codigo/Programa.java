import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Collection;
import java.io.FileWriter;
import java.io.File;

public class Programa {
	public static final String INPUT = "INPUT";
	private String name;

	List<String> variaveis;
	List<Command> comandos;

	public Programa(String name) {
		this.name = name;
		comandos = new ArrayList<Command>();
		variaveis = new ArrayList<String>();
	}

	public void setVariaveis(Collection lista) {
		Iterator it = lista.iterator();
		while (it.hasNext()) {
			variaveis.add((String) it.next());
		}
	}

	public void addCommand(Command c) {
		comandos.add(c);
	}

	public void saveToFile() {
		try {
			FileWriter f = new FileWriter(new File(name + ".java"));
			f.write("public class " + name + "{\n");
			f.write("	public static void main(String args[]){\n");
			f.write("		java.util.Scanner " + INPUT + " = new java.util.Scanner(System.in);\n");
			for (String s : variaveis) {
				f.write(" int " + s + ";\n");
			}
			for (Command c : comandos) {
				f.write(c.toJava() + "\n");
			}
			f.write("	}");
			f.write("}");
			f.close();
		} catch (Exception ex) {
			System.out.println("ERRO:" + ex.getMessage());
		}
	}
}