package simuMips;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import com.google.gson.JsonObject;

public class Simulador {

	public static void main(String[] args) {

		JsonHandling test = new JsonHandling();
		FuncUtil util = new FuncUtil();
		List<String> hex;
		List<Map<String, Object>> saida = new ArrayList<>();
		HexDecoder hexDecoder = new HexDecoder();		
		Map<String,Object> regsMap = new LinkedHashMap<String,Object>();
		//pegará as preconfig de memoria, por enquanto uso ela pra escrever algo na saida
		Map<String,Object> memMap = new LinkedHashMap<String,Object>();
		
		try {
			//variaveis que precisam ser iniciadas para o funcionamento do programa
			JsonObject input = test.readJson();
			regsMap = test.getPreRegs(input);
			hexDecoder.initializeReg(regsMap);

			
			
			//Object o = gson.fromJson(reader, Object.class);
			
			
			/*map.forEach((k,v)->{
				System.out.println("k: " + k + " v: " + v);
			});*/
			//System.out.println(map.keySet());
			
			
			
			hex = test.getHexList(input);
			//pega cada hex de entrada decodifica e adiciona o resultado em uma lista
			for(String s : hex) {
				String instrucao = hexDecoder.decoderInstruction(s);
				Map<String,Object> output = new LinkedHashMap<String,Object>();
				output.put("hex", s);
				output.put("text", instrucao);
				output.put("regs", util.getOutputregs(hexDecoder.registers));
				output.put("mem", memMap);
				output.put("stdout", hexDecoder.getStdout());
				saida.add(output);
				/*Map<String, Object> tMap = hexDecoder.getRegs();
				tMap.forEach((k,v)->{
					System.out.println("k: " + k + " v: " + v);
				});
				System.out.println();*/
			}
			
			
			//por fim pega a lista e escreve em um arquivo.
			test.writeJSON(saida);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}	
}
