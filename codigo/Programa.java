import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Collection;
import java.util.HashMap;
import java.io.FileWriter;
import java.io.File;

public class Programa {
	public static final String INPUT = "INPUT";
	private String name;

	HashMap<String, String> variaveis;
	List<Command> comandos;

	public Programa(String name) {
		this.name = name;
		comandos = new ArrayList<Command>();
		variaveis = new HashMap<String, String>();
	}

	public void setVariaveis(HashMap<String, String> mapa) {
		variaveis = mapa;
	}

	public void addCommand(Command c) {
		comandos.add(c);
	}

	public void saveToFile() {
		try {
			FileWriter f = new FileWriter(new File(name + ".java"));
			f.write("public class " + name + "{\n");
			f.write("	public static void main(String args[]){\n");
			f.write("		java.util.Scanner " + INPUT + " = new java.util.Scanner(System.in);\n\n");
			
			for (HashMap.Entry<String, String> var : variaveis.entrySet()) {
				f.write("		" + var.getValue() + " " + var.getKey() + ";\n");
			}

			for (Command c : comandos) {
				f.write("		"+ c.toJava() + "\n");
			}

			f.write("	}\n");
			f.write("}\n");
			f.close();
		} catch (Exception ex) {
			System.out.println("ERRO:" + ex.getMessage());
		}
	}
}