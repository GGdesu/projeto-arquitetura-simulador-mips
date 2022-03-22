package simuMips;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonHandling {
	private Gson gson;

	public JsonHandling() {
	}
	
	

	public void writeJSON(List list) throws IOException {
		// instanciando um objeto gson para utilizar as funções da biblioteca
		gson = new GsonBuilder().setPrettyPrinting().create();

		// conversão do objeto para json e escrevendo no arquivo
		String Json = gson.toJson(list);
		Files.write(Paths.get("outputJson.json"), Json.getBytes());

	}

	// Metodo vai ler e retornar um objeto do arquivo json. provisoriamente retornar
	// uma string
	public InputJson readJson() throws IOException {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		
		// carrego o arquivo Json
		BufferedReader reader = Files.newBufferedReader(Paths.get("exemplos.input.json"));
		

		// transforma o json em objeto.(desserializa)
		InputJson saida = gson.fromJson(reader, InputJson.class);

		

		// System.out.println(json);
		return saida;

	}

}

//classe do json de saida
class OutputJson {
	private String hex;
	private String text;

	public OutputJson(String hex, String text) {
		this.hex = hex;
		this.text = text;
	}

	public String getHex() {
		return hex;
	}

	public void setHex(String hex) {
		this.hex = hex;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return "OutputJson [hex= " + hex + ", text= " + text + "]";
	}
	
	

}

//classes do json de entrada
//-----------------------------------------------------//
class InputJson {
	private Config config;
	private Data data;
	private List<String> text;

	// constructor
	public InputJson() {
	}

	//getters and setters
	public Config getConfig() {
		return config;
	}


	public void setConfig(Config config) {
		this.config = config;
	}


	public Data getData() {
		return data;
	}


	public void setData(Data data) {
		this.data = data;
	}


	public List<String> getText() {
		return text;
	}


	public void setText(List<String> text) {
		this.text = text;
	}


	// tostring
	@Override
	public String toString() {
		return "InputJson [config=" + config + ", data=" + data + ", text=" + text + "]";
	}

}

class Data {

	// tostring
	@Override
	public String toString() {
		return "Data []";
	}

}

class Config {
	private Reg regs;
	private Mem mem;

	// constructor
	public Config() {
	}

	// tostring
	@Override
	public String toString() {
		return "Config [regs=" + regs + ", mem=" + mem + "]";
	}

}

class Reg {

	// tostring
	@Override
	public String toString() {
		return "Reg []";
	}

}

class Mem {

	// tostring
	@Override
	public String toString() {
		return "Mem []";
	}

}
//-----------------------------------------------------//