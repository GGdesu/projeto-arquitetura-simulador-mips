package simuMips;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Simulador {

	public static void main(String[] args) {

	
		JsonHandling test = new JsonHandling();
		List<String> hex;
		List<OutputJson> saida = new ArrayList<OutputJson>();
		HexDecoder hexDecoder = new HexDecoder();		
		
	    
		try {
			InputJson exemplo = test.readJson();
			hex = exemplo.getText();
			//pega cada hex de entrada decodifica e adiciona o resultado em uma lista
			for(String s : hex) {
				//String instrucao = hexDecoder.decoderInstruction(s);
				//saida.add(new OutputJson(s, "OK"));
			}
			
			/*for(OutputJson a: saida) {
				System.out.println(a.toString());
			}*/
			//por fim pega a lista e escreve em um arquivo.
			test.writeJSON(saida);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		

	}	
}
