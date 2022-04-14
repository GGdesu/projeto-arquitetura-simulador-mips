package simuMips;

import java.util.LinkedHashMap;
import java.util.Map;

public class HexDecoder {
	private String stdout = "";
	

	FuncUtil util = new FuncUtil();
	LinkedHashMap<String, Object> registers = new LinkedHashMap<>();
	
	
	
	//constructor
	//quando uma instancia da classe for criada o banco de registradores é iniciado
	public HexDecoder() {
		
	}
	
	public Map<String, Object> getRegs(){
		return registers;
	}
	
	//inicializa os registradores com valores que vem pre-configurado no arquivo de entrada.
	public void initializeReg(Map<String, Object> regInit) {
		
		registers.put("$0", 0);
		registers.put("$1", 0);
		registers.put("$2", 0);
		registers.put("$3", 0);
		registers.put("$4", 0);
		registers.put("$5", 0);
		registers.put("$6", 0);
		registers.put("$7", 0);
		registers.put("$8", 0);
		registers.put("$9", 0);
		registers.put("$10", 0);
		registers.put("$11", 0);
		registers.put("$12", 0);
		registers.put("$13", 0);
		registers.put("$14", 0);
		registers.put("$15", 0);
		registers.put("$16", 0);
		registers.put("$17", 0);
		registers.put("$18", 0);
		registers.put("$19", 0);
		registers.put("$20", 0);
		registers.put("$21", 0);
		registers.put("$22", 0);
		registers.put("$23", 0);
		registers.put("$24", 0);
		registers.put("$25", 0);
		registers.put("$26", 0);
		registers.put("$27", 0);
		registers.put("$28", 268468224);
		registers.put("$29", 2147479548);
		registers.put("$30", 0);
		registers.put("$31", 0);
		registers.put("pc", 4194304);
		registers.put("hi", 0);
		registers.put("lo", 0);
		
		if (!regInit.isEmpty()) {
			regInit.forEach((chave, valor) -> {
				registers.replace(chave, valor);
			});
		}
	}
	
	//FunÃ§Ã£o que decodificara o hex para instruÃ§Ã£o.
	public String decoderInstruction(String hex) {
		String bin = util.hexToBin(hex);
		String ins = "";
		int result, shamt, offset;
		String rd = "";
		String rs = "";
		String rt;
		String aux = "";
		this.setStdout("");

		
		
		//identificando instruÃ§Ãµes do tipo R
		if(util.getOpcode(bin).equalsIgnoreCase("000000")) {
			
			switch(util.getFn(bin)) {
			
			case "100000": //add rd, rs, rt
				ins = util.imapR.get(100000) + " $" + util.binToDec(util.getRd(bin));
				ins += ", $" + util.binToDec(util.getRs(bin)) + ", $" + util.binToDec(util.getRt(bin));
				//-------------------------------------------------------------------------------------------------------------//
				//pegando os valores da instrução
				rd = "$" + util.binToDec(util.getRd(bin));
				rs = "$" +  util.binToDec(util.getRs(bin));
				rt = "$" + util.binToDec(util.getRt(bin));
				
				//executando a instrução
				if((util.checkOverflow("+", util.objToInt(registers, rs), util.objToInt(registers, rt)))){
					this.setStdout("Overflow");
				}else {
					result = util.objToInt(registers, rs) + util.objToInt(registers, rt);
					registers.put(rd, result);
				}
				
				//-------------------------------------------------------------------------------------------------------------//
				break;
			case "100010": //sub rd, rs, rt
				ins = util.imapR.get(100010) + " $" + util.binToDec(util.getRd(bin));
				ins += ", $" + util.binToDec(util.getRs(bin)) + ", $" + util.binToDec(util.getRt(bin));	
				//-------------------------------------------------------------------------------------------------------------//
				//pegando os valores da instrução
				rd = "$" + util.binToDec(util.getRd(bin));
				rs = "$" +  util.binToDec(util.getRs(bin));
				rt = "$" + util.binToDec(util.getRt(bin));
				
				//executando a instrução
				if((util.checkOverflow("-", util.objToInt(registers, rs), util.objToInt(registers, rt)))){
					this.setStdout("Overflow");
				}else {
					result = util.objToInt(registers, rs) - util.objToInt(registers, rt);
					registers.put(rd, result);
				}
				
				
				//-------------------------------------------------------------------------------------------------------------//
				break;
			case "101010": //slt rd, rs, rt
				ins = util.imapR.get(100010) + " $" + util.binToDec(util.getRd(bin));
				ins += ", $" + util.binToDec(util.getRs(bin)) + ", $" + util.binToDec(util.getRt(bin));
				//-------------------------------------------------------------------------------------------------------------//
				//pegando os valores da instrução
				rd = "$" + util.binToDec(util.getRd(bin));
				rs = "$" +  util.binToDec(util.getRs(bin));
				rt = "$" + util.binToDec(util.getRt(bin));
				
				//executando a instrução
				if(util.objToInt(registers, rs) < util.objToInt(registers, rt)) {
					registers.put(rd, 1);
				}else {
					registers.put(rd, 0);
				}
				//-------------------------------------------------------------------------------------------------------------//
				break;
			case "100100": //and rd, rs, rt
				ins = util.imapR.get(100100) + " $" + util.binToDec(util.getRd(bin));
				ins += ", $" + util.binToDec(util.getRs(bin)) + ", $" + util.binToDec(util.getRt(bin));	
				//-------------------------------------------------------------------------------------------------------------//
				//pegando os valores da instrução
				rd = "$" + util.binToDec(util.getRd(bin));
				rs = "$" +  util.binToDec(util.getRs(bin));
				rt = "$" + util.binToDec(util.getRt(bin));
				
				//executando a instrução
				rs = util.decToBin(util.objToInt(registers, rs));
				rt = util.decToBin(util.objToInt(registers, rt));
				aux = "";
				for(int i = rs.length()-1; i >= 0; i--) {
					if((rs.charAt(i) == rt.charAt(i)) && (rs.charAt(i) == '1' && rt.charAt(i) == '1')) {
						aux = "1" + aux;
					}else {
						aux = "0" + aux;
					}
				}
				registers.put(rd, util.binToDec(aux));
				//-------------------------------------------------------------------------------------------------------------//
				break;
			case "100101": //or rd, rs, rt
				ins = util.imapR.get(100101) + " $" + util.binToDec(util.getRd(bin));
				ins += ", $" + util.binToDec(util.getRs(bin)) + ", $" + util.binToDec(util.getRt(bin));
				//-------------------------------------------------------------------------------------------------------------//
				//pegando os valores da instrução
				rd = "$" + util.binToDec(util.getRd(bin));
				rs = "$" +  util.binToDec(util.getRs(bin));
				rt = "$" + util.binToDec(util.getRt(bin));
				
				//executando a instrução
				rs = util.decToBin(util.objToInt(registers, rs));
				rt = util.decToBin(util.objToInt(registers, rt));
				aux = "";
				for(int i = rs.length()-1; i >= 0; i--) {
					if((rs.charAt(i) == '1' || rt.charAt(i) == '1')) {
						aux = "1" + aux;
					}else {
						aux = "0" + aux;
					}
				}
				registers.put(rd, util.binToDec(aux));
				//-------------------------------------------------------------------------------------------------------------//
				break;
			case "100110": //xor rd, rs, rt
				ins = util.imapR.get(100110) + " $" + util.binToDec(util.getRd(bin));
				ins += ", $" + util.binToDec(util.getRs(bin)) + ", $" + util.binToDec(util.getRt(bin));
				//-------------------------------------------------------------------------------------------------------------//
				//pegando os valores da instrução
				rd = "$" + util.binToDec(util.getRd(bin));
				rs = "$" +  util.binToDec(util.getRs(bin));
				rt = "$" + util.binToDec(util.getRt(bin));
				
				//executando a instrução
				rs = util.decToBin(util.objToInt(registers, rs));
				rt = util.decToBin(util.objToInt(registers, rt));
				aux = "";
				for(int i = rs.length()-1; i >= 0; i--) {
					if((rs.charAt(i) != rt.charAt(i))) {
						aux = "1" + aux;
					}else {
						aux = "0" + aux;
					}
				}
				registers.put(rd, util.binToDec(aux));
				//-------------------------------------------------------------------------------------------------------------//
				break;
			case "100111": //nor rd, rs, rt
				ins = util.imapR.get(100111) + " $" + util.binToDec(util.getRd(bin));
				ins += ", $" + util.binToDec(util.getRs(bin)) + ", $" + util.binToDec(util.getRt(bin));	
				//-------------------------------------------------------------------------------------------------------------//
				//pegando os valores da instrução
				rd = "$" + util.binToDec(util.getRd(bin));
				rs = "$" +  util.binToDec(util.getRs(bin));
				rt = "$" + util.binToDec(util.getRt(bin));
				
				//executando a instrução
				rs = util.decToBin(util.objToInt(registers, rs));
				rt = util.decToBin(util.objToInt(registers, rt));
				aux = "";
				for(int i = rs.length()-1; i >= 0; i--) {
					if(!(rs.charAt(i) == '1' || rt.charAt(i) == '1')) {
						aux = "1" + aux;
					}else {
						aux = "0" + aux;
					}
				}
				registers.put(rd, util.binToDec(aux));
				//-------------------------------------------------------------------------------------------------------------//
				break;
			case "010000": //mfhi rd
				ins = util.imapR.get(010000) + " $" + util.binToDec(util.getRd(bin));
				//-------------------------------------------------------------------------------------------------------------//
				//pegando os valores da instrução
				rd = "$" + util.binToDec(util.getRd(bin));
				
				//executando a instrução
				
				registers.put(rd, util.objToInt(registers, "hi"));
				//-------------------------------------------------------------------------------------------------------------//
				break;
			case "010010": //mflo rd
				ins = util.imapR.get(010010) + " $" + util.binToDec(util.getRd(bin));
				//-------------------------------------------------------------------------------------------------------------//
				//pegando os valores da instrução
				rd = "$" + util.binToDec(util.getRd(bin));
				
				//executando a instrução
				
				registers.put(rd, util.objToInt(registers, "lo"));
				//-------------------------------------------------------------------------------------------------------------//
				break;
			case "100001": //addu rd, rs, rt
				ins = util.imapR.get(100001) + " $" + util.binToDec(util.getRd(bin));
				ins += ", $" + util.binToDec(util.getRs(bin)) + ", $" + util.binToDec(util.getRt(bin));	
				//-------------------------------------------------------------------------------------------------------------//
				//pegando os valores da instrução
				rd = "$" + util.binToDec(util.getRd(bin));
				rs = "$" +  util.binToDec(util.getRs(bin));
				rt = "$" + util.binToDec(util.getRt(bin));
				
				//executando a instrução
				result = util.objToInt(registers, rs) + util.objToInt(registers, rt);
				registers.put(rd, result);
				//-------------------------------------------------------------------------------------------------------------//				
				break;
			case "100011": //subu rd, rs, rt
				ins = util.imapR.get(100011) + " $" + util.binToDec(util.getRd(bin));
				ins += ", $" + util.binToDec(util.getRs(bin)) + ", $" + util.binToDec(util.getRt(bin));	
				//-------------------------------------------------------------------------------------------------------------//
				//pegando os valores da instrução
				rd = "$" + util.binToDec(util.getRd(bin));
				rs = "$" +  util.binToDec(util.getRs(bin));
				rt = "$" + util.binToDec(util.getRt(bin));
				
				//executando a instrução
				result = util.objToInt(registers, rs) - util.objToInt(registers, rt);
				registers.put(rd, result);
				//-------------------------------------------------------------------------------------------------------------//
				break;
			case "011000": //mult rs, rt
				ins = util.imapR.get(011000) + " $" + util.binToDec(util.getRs(bin));
				ins += ", $" + util.binToDec(util.getRt(bin));
				//-------------------------------------------------------------------------------------------------------------//
				//pegando os valores da instrução
				rs = "$" +  util.binToDec(util.getRs(bin));
				rt = "$" + util.binToDec(util.getRt(bin));
				
				//executando a instrução
				result = util.objToInt(registers, rs) * util.objToInt(registers, rt);
				registers.put("lo", result);
				//-------------------------------------------------------------------------------------------------------------//
				break;
			case "011001": //multu rs, rt
				ins = util.imapR.get(011001) + " $" + util.binToDec(util.getRs(bin));
				ins += ", $" + util.binToDec(util.getRt(bin));
				//-------------------------------------------------------------------------------------------------------------//
				//pegando os valores da instrução
				rs = "$" +  util.binToDec(util.getRs(bin));
				rt = "$" + util.binToDec(util.getRt(bin));
				
				//executando a instrução
				result = util.objToInt(registers, rs) * util.objToInt(registers, rt);
				registers.put("lo", result);
				//-------------------------------------------------------------------------------------------------------------//
				break;
			case "011010": //div rs, rt
				ins = util.imapR.get(011010) + " $" + util.binToDec(util.getRs(bin));
				ins += ", $" + util.binToDec(util.getRt(bin));
				//-------------------------------------------------------------------------------------------------------------//
				//pegando os valores da instrução
				rs = "$" +  util.binToDec(util.getRs(bin));
				rt = "$" + util.binToDec(util.getRt(bin));
				
				//executando a instrução
				if(util.objToInt(registers, rs) != 0 && util.objToInt(registers, rt) != 0) {
				registers.put("lo", (util.objToInt(registers, rs) / util.objToInt(registers, rt)));
				registers.put("hi", (util.objToInt(registers, rs) % util.objToInt(registers, rt)));
				}else {
					registers.put("hi", 0);
					registers.put("lo", 0);
				}
				//-------------------------------------------------------------------------------------------------------------//
				break;
			case "011011": //divu rs, rt
				ins = util.imapR.get(011011) + " $" + util.binToDec(util.getRs(bin));
				ins += ", $" + util.binToDec(util.getRt(bin));
				//-------------------------------------------------------------------------------------------------------------//
				//pegando os valores da instrução
				rs = "$" +  util.binToDec(util.getRs(bin));
				rt = "$" + util.binToDec(util.getRt(bin));
				
				//executando a instrução
				if(util.objToInt(registers, rs) != 0 && util.objToInt(registers, rt) != 0) {
					registers.put("lo", (util.objToInt(registers, rs) / util.objToInt(registers, rt)));
					registers.put("hi", (util.objToInt(registers, rs) % util.objToInt(registers, rt)));
					}else {
						registers.put("hi", 0);
						registers.put("lo", 0);
					}
				//-------------------------------------------------------------------------------------------------------------//
				break;
			case "000000": //sll rd, rt, shamt
				ins = util.imapR.get(000000) + " $" + util.binToDec(util.getRd(bin));
				ins += ", $" + util.binToDec(util.getRt(bin)) + ", " + util.binToDec(util.getOperand(bin));	
				//-------------------------------------------------------------------------------------------------------------//
				//pegando os valores da instrução
				rd = "$" +  util.binToDec(util.getRd(bin));
				rt = "$" + util.binToDec(util.getRt(bin));
				shamt = util.binToDec(util.getSh(bin));
				
				//executando a instrução
				result = util.objToInt(registers, rt) * (int) Math.pow(2, shamt);
				
				registers.put(rd, result);
				//-------------------------------------------------------------------------------------------------------------//
				break;
			case "000010": //srl rd, rt, shamt 
				ins = util.imapR.get(000010) + " $" + util.binToDec(util.getRd(bin));
				ins += ", $" + util.binToDec(util.getRt(bin)) + ", " + util.binToDec(util.getOperand(bin));
				//-------------------------------------------------------------------------------------------------------------//
				//pegando os valores da instrução
				rd = "$" +  util.binToDec(util.getRd(bin));
				rt = "$" + util.binToDec(util.getRt(bin));
				shamt = util.binToDec(util.getSh(bin));
				
				//executando a instrução
				result = util.objToInt(registers, rt) * (int) Math.pow(2, -shamt);
				
				registers.put(rd, result);
				//-------------------------------------------------------------------------------------------------------------//
				break;
			case "000011": //sra rd, rt, shamt 
				ins = util.imapR.get(000011) + " $" + util.binToDec(util.getRd(bin));
				ins += ", $" + util.binToDec(util.getRt(bin)) + ", " + util.binToDec(util.getOperand(bin));
				//-------------------------------------------------------------------------------------------------------------//
				//pegando os valores da instrução
				rd = "$" +  util.binToDec(util.getRd(bin));
				rt = "$" + util.binToDec(util.getRt(bin));
				shamt = util.binToDec(util.getSh(bin));
				
				//executando a instrução
				result = util.objToInt(registers, rt) >> shamt;
				
				registers.put(rd, result);
				//-------------------------------------------------------------------------------------------------------------//
				break;
			case "000100": //sllv rd, rt, rs
				ins = util.imapR.get(000100) + " $" + util.binToDec(util.getRd(bin));
				ins += ", $" + util.binToDec(util.getRt(bin)) + ", $" + util.binToDec(util.getRs(bin));
				//-------------------------------------------------------------------------------------------------------------//
				//pegando os valores da instrução
				rd = "$" +  util.binToDec(util.getRd(bin));
				rt = "$" + util.binToDec(util.getRt(bin));
				rs = "$" +  util.binToDec(util.getRs(bin));
				
				//executando a instrução
				result = util.objToInt(registers, rt) << util.objToInt(registers, rs);
				
				registers.put(rd, result);
				//-------------------------------------------------------------------------------------------------------------//
				break;
			case "000110": //srlv rd, rt, rs
				ins = util.imapR.get(000110) + " $" + util.binToDec(util.getRd(bin));
				ins += ", $" + util.binToDec(util.getRt(bin)) + ", $" + util.binToDec(util.getRs(bin));
				//-------------------------------------------------------------------------------------------------------------//
				//pegando os valores da instrução
				rd = "$" +  util.binToDec(util.getRd(bin));
				rt = "$" + util.binToDec(util.getRt(bin));
				rs = "$" +  util.binToDec(util.getRs(bin));
				
				//executando a instrução
				result = util.objToInt(registers, rt) >>> util.objToInt(registers, rs);
				
				registers.put(rd, result);
				//-------------------------------------------------------------------------------------------------------------//
				break;
			case "000111": //srav rd rt rs
				ins = util.imapR.get(000111) + " $" + util.binToDec(util.getRd(bin));
				ins += ", $" + util.binToDec(util.getRt(bin)) + ", $" + util.binToDec(util.getRs(bin));
				//-------------------------------------------------------------------------------------------------------------//
				//pegando os valores da instrução
				rd = "$" +  util.binToDec(util.getRd(bin));
				rt = "$" + util.binToDec(util.getRt(bin));
				rs = "$" +  util.binToDec(util.getRs(bin));
				
				//executando a instrução
				result = util.objToInt(registers, rt) >> util.objToInt(registers, rs);
				
				registers.put(rd, result);
				//-------------------------------------------------------------------------------------------------------------//
				break;
			case "001000": //jr rs 
				ins = util.imapR.get(001000) + " $" + util.binToDec(util.getRs(bin));
				break;
			case "001100":
				ins = util.imapR.get(001100);
				break;
				
			}
		}

		//identificando instruÃ§Ãµes do tipo I
		switch (util.getOpcode(bin)) {

		case "100100": // lbu rt, offset(rs)
			ins = util.imapIJ.get(100100) + " $" + util.binToDec(util.getRt(bin));
			ins += ", " + util.binToDec(util.getOperand(bin)) + "($" + util.binToDec(util.getRs(bin)) + ")";
			break;

		case "001010": // slti rt, rs, offset
			ins = util.imapIJ.get(001010) + " $" + util.binToDec(util.getRt(bin));
			ins += ", $" + util.binToDec(util.getRs(bin)) + ", " + util.binToDec(util.getOperand(bin));
			//-------------------------------------------------------------------------------------------------------------//
			//pegando os valores da instrução
			
			rs = "$" +  util.binToDec(util.getRs(bin));
			rt = "$" + util.binToDec(util.getRt(bin));
			
			//executando a instrução
			if(util.objToInt(registers, rs) < util.binToDec(util.getOperand(bin))) {
				registers.put(rt, 1);
			}else {
				registers.put(rt, 0);
			}
			//-------------------------------------------------------------------------------------------------------------//
			break;
		case "001000": // addi rt, rs, operand
			ins = util.imapIJ.get(001000) + " $" + util.binToDec(util.getRt(bin));
			ins += ", $" + util.binToDec(util.getRs(bin)) + ", " + util.binToDec(util.getOperand(bin));
			//-------------------------------------------------------------------------------------------------------------//
			//pegando os valores da instrução
			rt = "$" + util.binToDec(util.getRt(bin));
			rs = "$" +  util.binToDec(util.getRs(bin));
			offset = util.binToDec(util.getOperand(bin));
			
			//executando a instrução
			if((util.checkOverflow("+", util.objToInt(registers, rs), offset))){
				this.setStdout("Overflow");
			}else {
				result = util.objToInt(registers, rs) + offset;
				registers.put(rt, result);
			}
			
			
			
			//-------------------------------------------------------------------------------------------------------------//
			break;

		case "001001": // addiu rt, rs, operand
			ins = util.imapIJ.get(001001) + " $" + util.binToDec(util.getRt(bin));
			ins += ", $" + util.binToDec(util.getRs(bin)) + ", " + util.binToDec(util.getOperand(bin));
			//-------------------------------------------------------------------------------------------------------------//
			//pegando os valores da instrução
			rt = "$" + util.binToDec(util.getRt(bin));
			rs = "$" +  util.binToDec(util.getRs(bin));
			offset = util.binToDec(util.getOperand(bin));
			
			//executando a instrução
			result = util.objToInt(registers, rs) + offset;
			
			registers.put(rt, result);
			//-------------------------------------------------------------------------------------------------------------//
			break;

		case "001100": // andi rt, rs, operand
			ins = util.imapIJ.get(001100) + " $" + util.binToDec(util.getRt(bin));
			ins += ", $" + util.binToDec(util.getRs(bin)) + ", " + util.binToDec(util.getOperand(bin));
			//-------------------------------------------------------------------------------------------------------------//
			//pegando os valores da instrução
			rs = "$" +  util.binToDec(util.getRs(bin));
			rt = "$" + util.binToDec(util.getRt(bin));
			
			//executando a instrução
			rs = util.decToBin(util.objToInt(registers, rs));
			// usarei a variavel "rd" para guardar o offset
			rd = util.decToBin(util.binToDec(util.getOperand(bin)));
			
			aux = "";
			for(int i = rs.length()-1; i >= 0; i--) {
				if((rs.charAt(i) == rd.charAt(i)) && (rs.charAt(i) == '1' && rd.charAt(i) == '1')) {
					aux = "1" + aux;
				}else {
					aux = "0" + aux;
				}
			}
			registers.put(rt, util.binToDec(aux));
			//-------------------------------------------------------------------------------------------------------------//
			break;

		case "000100": // beq rs, rt, start
			ins = util.imapIJ.get(000100) + " $" + util.binToDec(util.getRs(bin));
			ins += ", $" + util.binToDec(util.getRt(bin)) + ", start";
			break;

		case "000001": // bltz rs, start
			ins = util.imapIJ.get(000001) + " $" + util.binToDec(util.getRs(bin)) + ", start";
			break;

		case "000101": // bne rs, rt, start
			ins = util.imapIJ.get(000101) + " $" + util.binToDec(util.getRt(bin));
			ins += ", $" + util.binToDec(util.getRt(bin)) + ", start";
			break;

		case "100000": // lb rt, offset(rs)
			ins = util.imapIJ.get(100000) + " $" + util.binToDec(util.getRt(bin));
			ins += ", " + util.binToDec(util.getOperand(bin)) + "($" + util.binToDec(util.getRs(bin)) + ")";
			break;

		case "100110": // lbu rt, offset(rs)
			ins = util.imapIJ.get(100110) + " $" + util.binToDec(util.getRt(bin));
			ins += ", " + util.binToDec(util.getOperand(bin)) + "($" + util.binToDec(util.getRs(bin)) + ")";
			break;

		case "001111": // lui rt, offset
			ins = util.imapIJ.get(001111) + " $" + util.binToDec(util.getRt(bin)) + ", "
					+ util.binToDec(util.getOperand(bin));
			break;

		case "100011": // lw rt, offset(rs)
			ins = util.imapIJ.get(100011) + " $" + util.binToDec(util.getRt(bin));
			ins += ", " + util.binToDec(util.getOperand(bin)) + "($" + util.binToDec(util.getRs(bin)) + ")";
			break;

		case "001101": // ori rt, rs, offset
			ins = util.imapIJ.get(001101) + " $" + util.binToDec(util.getRt(bin));
			ins += ", $" + util.binToDec(util.getRs(bin)) + ", " + util.binToDec(util.getOperand(bin));
			//-------------------------------------------------------------------------------------------------------------//
			//pegando os valores da instrução
			rs = "$" +  util.binToDec(util.getRs(bin));
			rt = "$" + util.binToDec(util.getRt(bin));
			
			//executando a instrução
			rs = util.decToBin(util.objToInt(registers, rs));
			// usarei a variavel "rd" para guardar o offset
			rd = util.decToBin(util.binToDec(util.getOperand(bin)));
			aux = "";
			
			for(int i = rs.length()-1; i >= 0; i--) {
				if((rd.charAt(i) == '1' || rs.charAt(i) == '1')) {
					aux = "1" + aux;
				}else {
					aux = "0" + aux;
				}
			}
			registers.put(rt , util.binToDec(aux));
			//-------------------------------------------------------------------------------------------------------------//
			break;

		case "101000": // sb rs, offset(rt)
			ins = util.imapIJ.get(101000) + " $" + util.binToDec(util.getRs(bin));
			ins += ", " + util.binToDec(util.getOperand(bin)) + "($" + util.binToDec(util.getRt(bin)) + ")";
			break;

		case "101011": // sw rt, offset(rs)
			ins = util.imapIJ.get(101011) + " $" + util.binToDec(util.getRt(bin));
			ins += ", " + util.binToDec(util.getOperand(bin)) + "($" + util.binToDec(util.getRs(bin)) + ")";
			break;

		case "001110": // xori $rt, $rs, operand\offset
			ins = util.imapIJ.get(001110) + " $" + util.binToDec(util.getRt(bin));
			ins += ", $" + util.binToDec(util.getRs(bin)) + ", " + util.binToDec(util.getOperand(bin));
			//-------------------------------------------------------------------------------------------------------------//
			//pegando os valores da instrução
			rt = "$" + util.binToDec(util.getRt(bin));
			rs = "$" +  util.binToDec(util.getRs(bin));
			
			//executando a instrução
			rs = util.decToBin(util.objToInt(registers, rs));
			// usarei a variavel "rd" para guardar o offset
			rd = util.decToBin(util.binToDec(util.getOperand(bin)));
			aux = "";
			for(int i = rs.length()-1; i >= 0; i--) {
				if((rs.charAt(i) != rd.charAt(i))) {
					aux = "1" + aux;
				}else {
					aux = "0" + aux;
				}
			}
			registers.put(rt, util.binToDec(aux));
			//-------------------------------------------------------------------------------------------------------------//
			break;

		// identificando instruÃ§Ãµes do tipo J
		case "000010": // j start
			ins = util.imapIJ.get(000010) + " start";
			break;

		case "000011": // jal start
			ins = util.imapIJ.get(000010) + " start";
			break;
		}
		
		//PC = PC+4
		int pc = util.objToInt(registers, "pc");
		registers.put("pc", pc+4);
		//-------------------------------------//
		return ins;		
	}
	
	public String getStdout() {
		return stdout;
	}

	public void setStdout(String stdout) {
		this.stdout = stdout;
	}
	
}
