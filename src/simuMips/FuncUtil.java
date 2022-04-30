package simuMips;

import java.util.LinkedHashMap;
import java.util.Map;

public class FuncUtil {
	
	//constructor
	public FuncUtil() {
		
	}
	
	
	
	//mapeamento do nome das instrucoes tipo R e syscall
	Map<Integer, String> imapR = Map.ofEntries(
			Map.entry(100000, "add"),
			Map.entry(100001, "addu"),
			Map.entry(100100, "and"),
			Map.entry(011010, "div"),
			Map.entry(011011, "divu"),
			Map.entry(001000, "jr"),
			Map.entry(010000, "mfhi"),
			Map.entry(010010, "mflo"),
			Map.entry(011000, "mult"),
			Map.entry(011001, "multu"),
			Map.entry(100111, "nor"),
			Map.entry(100101, "or"),
			Map.entry(000000, "sll"),
			Map.entry(000100, "sllv"),
			Map.entry(101010, "slt"),
			Map.entry(000011, "sra"),
			Map.entry(000111, "srav"),
			Map.entry(000010, "srl"),
			Map.entry(000110, "srlv"),
			Map.entry(100010, "sub"),
			Map.entry(100011, "subu"),
			Map.entry(001100, "syscall"),
			Map.entry(100110, "xor")
			);
	//mapeamento das instrucoes do tipo I e J
	Map<Integer, String> imapIJ = Map.ofEntries(
			Map.entry(001000, "addi"),
			Map.entry(001001, "addiu"),
			Map.entry(001100, "andi"),
			Map.entry(000111, "bgtz"),
			Map.entry(000100, "beq"),
			Map.entry(000001, "bltz"),
			Map.entry(000110, "blez"),
			Map.entry(000101, "bne"),
			Map.entry(000010, "j"),
			Map.entry(000011, "jal"),
			Map.entry(100000, "lb"),
			Map.entry(100100, "lbu"),
			Map.entry(001111, "lui"),
			Map.entry(100011, "lw"),
			Map.entry(001101, "ori"),
			Map.entry(101000, "sb"),
			Map.entry(001010, "slti"),
			Map.entry(101011, "sw"),
			Map.entry(001110, "xori")
			);
			
			
			//retorna os registradores diferentes de zero, pare serem escritos no arquivo de saida.
			public Map<String, Object> getOutputregs(Map<String, Object> registers){
				Map<String, Object> outMapReg = new LinkedHashMap<>();
				registers.forEach((chave, valor) ->{
					//int value = (Integer) valor;
					if(!valor.equals(Integer.valueOf(0))) {
						outMapReg.put(chave, valor);
					}
				});
				
				
				return outMapReg;
			}
			
			// funcao que converte o hexadecimal para binario
			public String hexToBin(String hex) {
				String bin = "";
				String binFragment = "";
				int iHex;
				hex = hex.trim();
				hex = hex.replaceFirst("0x", "");

				for (int i = 0; i < hex.length(); i++) {
					iHex = Integer.parseInt("" + hex.charAt(i), 16);
					binFragment = Integer.toBinaryString(iHex);

					while (binFragment.length() < 4) {
						binFragment = "0" + binFragment;
					}
					bin += binFragment;
				}
				return bin;
			}
			
			//função para checar se há overflow na instrução
			public boolean checkOverflow(String op,int value1, int value2) {
				
				final long negativeLim = Integer.MIN_VALUE;
				final long positiveLim = Integer.MAX_VALUE;
				long l1 = value1;
				long l2 = value2;
				
				
				switch (op) {
				case "+":
					if((l1+l2) > positiveLim || (l1+l2) < negativeLim) {
						return true;
					}
					break;
				case "-":
					if((l1-l2) > positiveLim || (l1-l2) < negativeLim) {
						return true;
					}
					
					break;
				default:
					break;
				}
							
				return false;
			}
			
			//transforma um objeto em um inteiro. caso o objeto não seja um numero ocorrerá um erro.
			public int objToInt(Map<String, Object> reg ,String key) {
				int i = (int) (Double.parseDouble(reg.get(key).toString()));
				
				return i;
			}
			
			//função que converte binario para decimal em complemento a 2
			public int binToDec(String bin) {
				boolean ehNegativo = false;
				if(bin.substring(0, 1).equalsIgnoreCase("1") && bin.length() >= 16) {
					bin = this.complementoa2(bin);
					ehNegativo = true;
				}
				
				int numero = Integer.parseInt(bin, 2);
				if(ehNegativo == true) {
					numero = numero * (-1);
				}
				return numero;
			}
			
			//funçao que converte de decimal para binario 32-bits
			public String decToBin(int dec) {
				String bin = Integer.toBinaryString(dec);
				
				if(dec >= 0) {
					for(int i = bin.length(); i < 32; i++) {
						bin = "0" + bin;
					}
				}
				
				return bin;
			}
			
			//pega o byte mais a direita de uma string de 32bits e retorna esse byte com  sinal extendido 32bits
			public String getByteSignExt(Integer value) {
				String aux = this.decToBin(value);
				String bin = aux.substring(24);
				
				if(bin.startsWith("1")) {
					for(int i = bin.length(); i < 32; i++) {
						bin = "1" + bin;
					}
				}else if(bin.startsWith("0")) {
					for(int i = bin.length(); i < 32; i++) {
						bin = "0" + bin;
					}
				}
				
				
				return bin;
				
			}
			
			//Funçao que faz o complemento a 2 de numeros negativos
			public String complementoa2(String t)
		    {
		        StringBuffer str = new StringBuffer();
		        
		        str.append(t);
				int n = str.length();
		        
		        int i;
		        for (i = n-1 ; i >= 0 ; i--)
		            if (str.charAt(i) == '1')
		                break;
		      
		        
		        if (i == -1)
		            return "1" + str;
		      
		        
		        for (int k = i-1 ; k >= 0; k--)
		        {
		            
		            if (str.charAt(k) == '1')
		                str.replace(k, k+1, "0");
		            else
		                str.replace(k, k+1, "1");
		        }
		      
		        return str.toString();
		    }


			// funcao que retorna O Opcode de uma String binaria
			// ex: [000000] 01011011000100000000100000 -> 000000
			public String getOpcode(String bin) {
				String opcode = bin.substring(0, 6);

				return opcode;
			}

			// funcao que retorna O Rs de uma String binaria
			// ex: 000000 [01011] 011000100000000100000 -> 01011
			public String getRs(String bin) {
				String rs = bin.substring(6, 11);

				return rs;
			}

			// funcao que retorna O Rt de uma String binaria
			// ex: 00000001011 [01100] 0100000000100000 -> 01100
			public String getRt(String bin) {
				String rt = bin.substring(11, 16);

				return rt;
			}

			// funcao que retorna O Rd de uma String binaria
			// ex: 0000000101101100 [01000] 00000100000 -> 01000
			public String getRd(String bin) {
				String rd = bin.substring(16, 21);

				return rd;
			}

			// funcao que retorna O Sh de uma String binaria
			// ex: 000000010110110001000 [00000] 100000 -> 00000
			public String getSh(String bin) {
				String sh = bin.substring(21, 26);

				return sh;
			}

			// funcao que retorna O Fn de uma String binaria
			// ex: 00000001011011000100000000 [100000] -> 100000
			public String getFn(String bin) {
				String fn = bin.substring(26, 32);

				return fn;
			}
			
			// funcao que retorna O operand/offset de uma String binaria
			// ex: 0000000101101100 [0100000000100000] -> 0100000000100000
			public String getOperand(String bin) {
				String fn = bin.substring(16, 32);

				return fn;
			}
			
			// funcao que retorna O operand de uma String binaria
			// ex: 000000 [01011011000100000000100000] -> 0100000000100000
			public String getJTA(String bin) {
				String jta = bin.substring(6, 32);

				return jta;
			}

}
