package simuMips;


public class HexDecoder {
	FuncUtil util = new FuncUtil();
	
	//constructor
	public HexDecoder() {}
	
	//Função que decodificara o hex para instrução.
	public String decoderInstruction(String hex) {
		String bin = util.hexToBin(hex);
		String ins = "";
		
		//identificando instruções do tipo R
		if(util.getOpcode(bin).equalsIgnoreCase("000000")) {
			
			switch(util.getFn(bin)) {
			
			case "100000": //add rd, rs, rt
				ins = util.imapR.get(100000) + " $" + util.binToDec(util.getRd(bin));
				ins += ", $" + util.binToDec(util.getRs(bin)) + ", $" + util.binToDec(util.getRt(bin));
				break;
			case "100010": //sub rd, rs, rt
				ins = util.imapR.get(100010) + " $" + util.binToDec(util.getRd(bin));
				ins += ", $" + util.binToDec(util.getRs(bin)) + ", $" + util.binToDec(util.getRt(bin));	
				break;
			case "101010": //slt rd, rs, rt
				ins = util.imapR.get(100010) + " $" + util.binToDec(util.getRd(bin));
				ins += ", $" + util.binToDec(util.getRs(bin)) + ", $" + util.binToDec(util.getRt(bin));
				break;
			case "100100": //and rd, rs, rt
				ins = util.imapR.get(100100) + " $" + util.binToDec(util.getRd(bin));
				ins += ", $" + util.binToDec(util.getRs(bin)) + ", $" + util.binToDec(util.getRt(bin));	
				break;
			case "100101": //or rd, rs, rt
				ins = util.imapR.get(100101) + " $" + util.binToDec(util.getRd(bin));
				ins += ", $" + util.binToDec(util.getRs(bin)) + ", $" + util.binToDec(util.getRt(bin));
				break;
			case "100110": //xor rd, rs, rt
				ins = util.imapR.get(100110) + " $" + util.binToDec(util.getRd(bin));
				ins += ", $" + util.binToDec(util.getRs(bin)) + ", $" + util.binToDec(util.getRt(bin));
				break;
			case "100111": //nor rd, rs, rt
				ins = util.imapR.get(100111) + " $" + util.binToDec(util.getRd(bin));
				ins += ", $" + util.binToDec(util.getRs(bin)) + ", $" + util.binToDec(util.getRt(bin));		
				break;
			case "010000": //mfhi rd
				ins = util.imapR.get(010000) + " $" + util.binToDec(util.getRd(bin));
				break;
			case "010010": //mflo rd
				ins = util.imapR.get(010010) + " $" + util.binToDec(util.getRd(bin));
				break;
			case "100001": //addu rd, rs, rt
				ins = util.imapR.get(100001) + " $" + util.binToDec(util.getRd(bin));
				ins += ", $" + util.binToDec(util.getRs(bin)) + ", $" + util.binToDec(util.getRt(bin));	
				break;
			case "100011": //subu rd, rs, rt
				ins = util.imapR.get(100011) + " $" + util.binToDec(util.getRd(bin));
				ins += ", $" + util.binToDec(util.getRs(bin)) + ", $" + util.binToDec(util.getRt(bin));	
				break;
			case "011000": //mult rs, rt
				ins = util.imapR.get(011000) + " $" + util.binToDec(util.getRs(bin));
				ins += ", $" + util.binToDec(util.getRt(bin));
				break;
			case "011001": //multu rs, rt
				ins = util.imapR.get(011001) + " $" + util.binToDec(util.getRs(bin));
				ins += ", $" + util.binToDec(util.getRt(bin));
				break;
			case "011010": //div rs, rt
				ins = util.imapR.get(011010) + " $" + util.binToDec(util.getRs(bin));
				ins += ", $" + util.binToDec(util.getRt(bin));
				break;
			case "011011": //divu rs, rt
				ins = util.imapR.get(011011) + " $" + util.binToDec(util.getRs(bin));
				ins += ", $" + util.binToDec(util.getRt(bin));
				break;
			case "000000": //sll rd, rt, shamt
				ins = util.imapR.get(000000) + " $" + util.binToDec(util.getRd(bin));
				ins += ", $" + util.binToDec(util.getRt(bin)) + ", " + util.binToDec(util.getOperand(bin));	
				break;
			case "000010": //srl rd, rt, shamt 
				ins = util.imapR.get(000010) + " $" + util.binToDec(util.getRd(bin));
				ins += ", $" + util.binToDec(util.getRt(bin)) + ", " + util.binToDec(util.getOperand(bin));
				break;
			case "000011": //sra rd, rt, shamt 
				ins = util.imapR.get(000011) + " $" + util.binToDec(util.getRd(bin));
				ins += ", $" + util.binToDec(util.getRt(bin)) + ", " + util.binToDec(util.getOperand(bin));
				break;
			case "000100": //sllv rd, rt, rs
				ins = util.imapR.get(000100) + " $" + util.binToDec(util.getRd(bin));
				ins += ", $" + util.binToDec(util.getRt(bin)) + ", $" + util.binToDec(util.getRs(bin));
				break;
			case "000110": //srlv rd, rt, rs
				ins = util.imapR.get(000110) + " $" + util.binToDec(util.getRd(bin));
				ins += ", $" + util.binToDec(util.getRt(bin)) + ", $" + util.binToDec(util.getRs(bin));
				break;
			case "000111": //srav rd rt rs
				ins = util.imapR.get(000111) + " $" + util.binToDec(util.getRd(bin));
				ins += ", $" + util.binToDec(util.getRt(bin)) + ", $" + util.binToDec(util.getRs(bin));
				break;
			case "001000": //jr rs 
				ins = util.imapR.get(001000) + " $" + util.binToDec(util.getRs(bin));
				break;
			case "001100":
				ins = util.imapR.get(001100);
				break;
				
			}
		}

		//identificando instruções do tipo I
		switch (util.getOpcode(bin)) {

		case "100100": // lbu rt, offset(rs)
			ins = util.imapIJ.get(100100) + " $" + util.binToDec(util.getRt(bin));
			ins += ", " + util.binToDec(util.getOperand(bin)) + "($" + util.binToDec(util.getRs(bin)) + ")";
			break;

		case "001010": // slti rt, rs, offset
			ins = util.imapIJ.get(001010) + " $" + util.binToDec(util.getRt(bin));
			ins += ", $" + util.binToDec(util.getRs(bin)) + ", " + util.binToDec(util.getOperand(bin));
			break;
		case "001000": // addi rt, rs, operand
			ins = util.imapIJ.get(001000) + " $" + util.binToDec(util.getRt(bin));
			ins += ", $" + util.binToDec(util.getRs(bin)) + ", " + util.binToDec(util.getOperand(bin));
			break;

		case "001001": // addiu rt, rs, operand
			ins = util.imapIJ.get(001001) + " $" + util.binToDec(util.getRt(bin));
			ins += ", $" + util.binToDec(util.getRs(bin)) + ", " + util.binToDec(util.getOperand(bin));
			break;

		case "001100": // andi rt, rs, operand
			ins = util.imapIJ.get(001100) + " $" + util.binToDec(util.getRt(bin));
			ins += ", $" + util.binToDec(util.getRs(bin)) + ", " + util.binToDec(util.getOperand(bin));
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
			break;

		case "101000": // sb rs, offset(rt)
			ins = util.imapIJ.get(101000) + " $" + util.binToDec(util.getRs(bin));
			ins += ", " + util.binToDec(util.getOperand(bin)) + "($" + util.binToDec(util.getRt(bin)) + ")";
			break;

		case "101011": // sw rt, offset(rs)
			ins = util.imapIJ.get(101011) + " $" + util.binToDec(util.getRt(bin));
			ins += ", " + util.binToDec(util.getOperand(bin)) + "($" + util.binToDec(util.getRs(bin)) + ")";
			break;

		case "001110": // xori $t, $s, operand\offset
			ins = util.imapIJ.get(001110) + " $" + util.binToDec(util.getRt(bin));
			ins += ", $" + util.binToDec(util.getRs(bin)) + ", " + util.binToDec(util.getOperand(bin));
			break;

		// identificando instruções do tipo J
		case "000010": // j start
			ins = util.imapIJ.get(000010) + " start";
			break;

		case "000011": // jal start
			ins = util.imapIJ.get(000010) + " start";
			break;
		}
		return ins;		
	}
	
}
