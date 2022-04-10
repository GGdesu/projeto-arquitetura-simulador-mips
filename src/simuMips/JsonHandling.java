package simuMips;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

public class JsonHandling {
	private Gson gson;

	public JsonHandling() {
	}
	
	

	public void writeJSON(List<?> list) throws IOException {
		// instanciando um objeto gson para utilizar as funções da biblioteca
		gson = new GsonBuilder().setPrettyPrinting().create();

		// conversão do objeto para json e escrevendo no arquivo
		String Json = gson.toJson(list);
		Files.write(Paths.get("output/GRUPOB.exemplo.output.json"), Json.getBytes());

	}

	// Metodo vai ler e retornar um objeto do arquivo json. provisoriamente retornar
	// uma string
	public JsonObject readJson() throws IOException {
		 gson = new GsonBuilder().setPrettyPrinting().create();

		
		// carrego o arquivo Json
		BufferedReader reader = Files.newBufferedReader(Paths.get("input/exemplos.input.json"));
		

		// transforma o json em objeto.(desserializa)
		JsonObject saida = gson.fromJson(reader, JsonObject.class);

		

		// System.out.println(json);
		return saida;

	}
	
	public Map<String, Object> getPreRegs(JsonObject json) {
		gson = new GsonBuilder().setPrettyPrinting().create();
		
		JsonObject regs = json.getAsJsonObject("config").getAsJsonObject("regs");

		Map<String, Object> regsMap = gson.fromJson(regs, new TypeToken<LinkedHashMap<String, Object>>() {
		}.getType());

		return regsMap;

	}
	
	public Map<String, Object> getPreMem(JsonObject json) {
		gson = new GsonBuilder().setPrettyPrinting().create();
		
		JsonObject mem = json.getAsJsonObject("config").getAsJsonObject("mem");

		Map<String, Object> memMap = gson.fromJson(mem, new TypeToken<LinkedHashMap<String, Object>>() {
		}.getType());

		return memMap;

	}
	
	public List<String> getHexList(JsonObject json){
		gson = new GsonBuilder().setPrettyPrinting().create();
		
		JsonElement text = json.get("text");
		Type listType = new TypeToken<List<String>>() {}.getType();
		
		List<String> hex = gson.fromJson(text, listType);
		
		return hex;
		
	}
}
