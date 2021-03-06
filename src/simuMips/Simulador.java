package simuMips;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.google.gson.JsonObject;

public class Simulador {

	public static void main(String[] args) {

		JsonHandling test = new JsonHandling();
		FuncUtil util = new FuncUtil();
		List<String> hex;
		HexDecoder hexDecoder = new HexDecoder();		
		Map<String,Object> regsMap = new LinkedHashMap<String,Object>();
		Map<String,Object> memMap = new LinkedHashMap<String,Object>();
		Map<String, Object> dataMap = new LinkedHashMap<String, Object>();
		
		
		try {
			//pegando o nome dos arquivos da pasta input
			File[] listaArq = test.getListFiles();
			
			
			
			//iterando por cada arquivo da lista e executando-o no simulador
			for (int i = 0; i < listaArq.length; i++) {
				// variaveis que precisam iniciar outros componentes do simulador para o
				// funcionamento do programa
				List<Map<String, Object>> saida = new ArrayList<>();
				JsonObject input = test.readJson(listaArq[i].getName());
				regsMap = test.getPreRegs(input);
				memMap = test.getPreMem(input);
				dataMap = test.getPreData(input);
				//Inicializando os bancos de registradores, memoria e "data"
				hexDecoder.initializeReg(regsMap);
				hexDecoder.initializeMem(memMap);	
				hexDecoder.initializeData(dataMap);
				
		
				


				hex = test.getHexList(input);
				// pega cada hex de entrada decodifica e adiciona o resultado em uma lista
				for (String s : hex) {
					String instrucao = hexDecoder.decoderInstruction(s);
					Map<String, Object> output = new LinkedHashMap<String, Object>();
					output.put("hex", s);
					output.put("text", instrucao);
					output.put("regs", util.getOutputBankOf(hexDecoder.registers));
					output.put("mem", util.getOutputBankOf(hexDecoder.memory));
					output.put("stdout", hexDecoder.getStdout());
					saida.add(output);

				}

				// por fim pega a lista e escreve em um arquivo.
				test.writeJSON(saida, listaArq[i].getName());
			}
		
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}	
}
