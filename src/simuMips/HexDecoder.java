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
			//add rd, rs, rt
			if(util.getFn(bin).equalsIgnoreCase("100000")) {
				ins = util.imapR.get(100000) + " $" + util.binToDec(util.getRd(bin));
				ins += ", $" + util.binToDec(util.getRs(bin)) + ", $" + util.binToDec(util.getRt(bin));
			}
			//sub rd, rs, rt
			else if(util.getFn(bin).equalsIgnoreCase("100010")){
				ins = util.imapR.get(100010) + " $" + util.binToDec(util.getRd(bin));
				ins += ", $" + util.binToDec(util.getRs(bin)) + ", $" + util.binToDec(util.getRt(bin));						
			}
			//slt rd, rs, rt
			else if(util.getFn(bin).equalsIgnoreCase("101010")) {
				ins = util.imapR.get(100010) + " $" + util.binToDec(util.getRd(bin));
				ins += ", $" + util.binToDec(util.getRs(bin)) + ", $" + util.binToDec(util.getRt(bin));			
			}
			//and rd, rs, rt
			else if(util.getFn(bin).equalsIgnoreCase("100100")) {
				ins = util.imapR.get(100100) + " $" + util.binToDec(util.getRd(bin));
				ins += ", $" + util.binToDec(util.getRs(bin)) + ", $" + util.binToDec(util.getRt(bin));				
			}
			//or rd, rs, rt
			else if(util.getFn(bin).equalsIgnoreCase("100101")) {
				ins = util.imapR.get(100101) + " $" + util.binToDec(util.getRd(bin));
				ins += ", $" + util.binToDec(util.getRs(bin)) + ", $" + util.binToDec(util.getRt(bin));
			}
			//xor rd, rs, rt
			else if(util.getFn(bin).equalsIgnoreCase("100110")) {
				ins = util.imapR.get(100110) + " $" + util.binToDec(util.getRd(bin));
				ins += ", $" + util.binToDec(util.getRs(bin)) + ", $" + util.binToDec(util.getRt(bin));
			}
			//nor rd, rs, rt
			else if(util.getFn(bin).equalsIgnoreCase("100111")) {
				ins = util.imapR.get(100110) + " $" + util.binToDec(util.getRd(bin));
				ins += ", $" + util.binToDec(util.getRs(bin)) + ", $" + util.binToDec(util.getRt(bin));				
			}
			//mfhi rd
			else if(util.getFn(bin).equalsIgnoreCase("010000")) {
				ins = util.imapR.get(010000) + " $" + util.binToDec(util.getRd(bin));				
			}
			//mflo rd
			else if(util.getFn(bin).equalsIgnoreCase("010010")) {
				ins = util.imapR.get(010010) + " $" + util.binToDec(util.getRd(bin));				
			}
			//addu rd, rs, rt
			else if(util.getFn(bin).equalsIgnoreCase("100001")) {
				ins = util.imapR.get(100001) + " $" + util.binToDec(util.getRd(bin));
				ins += ", $" + util.binToDec(util.getRs(bin)) + ", $" + util.binToDec(util.getRt(bin));				
			}
			//subu rd, rs, rt
			else if(util.getFn(bin).equalsIgnoreCase("100011")) {
				ins = util.imapR.get(100011) + " $" + util.binToDec(util.getRd(bin));
				ins += ", $" + util.binToDec(util.getRs(bin)) + ", $" + util.binToDec(util.getRt(bin));				
			}
			//mult rs, rt
			else if(util.getFn(bin).equalsIgnoreCase("011000")) {
				ins = util.imapR.get(011000) + " $" + util.binToDec(util.getRs(bin));
				ins += ", $" + util.binToDec(util.getRt(bin));
			}
			//multu rs, rt
			else if(util.getFn(bin).equalsIgnoreCase("011001")) {
				ins = util.imapR.get(011001) + " $" + util.binToDec(util.getRs(bin));
				ins += ", $" + util.binToDec(util.getRt(bin));
			}
			//div rs, rt
			else if(util.getFn(bin).equalsIgnoreCase("011010")) {
				ins = util.imapR.get(011010) + " $" + util.binToDec(util.getRs(bin));
				ins += ", $" + util.binToDec(util.getRt(bin));			
			}
			//divu rs, rt
			else if(util.getFn(bin).equalsIgnoreCase("011011")) {
				ins = util.imapR.get(011011) + " $" + util.binToDec(util.getRs(bin));
				ins += ", $" + util.binToDec(util.getRt(bin));		
			}
			//sll rd, rt, shamt 
			else if(util.getFn(bin).equalsIgnoreCase("000000")) {
				ins = util.imapR.get(000000) + " $" + util.binToDec(util.getRd(bin));
				ins += ", $" + util.binToDec(util.getRt(bin)) + ", " + util.binToDec(util.getOperand(bin));			
			}
			//srl rd, rt, shamt 
			else if(util.getFn(bin).equalsIgnoreCase("000010")) {	
				ins = util.imapR.get(000010) + " $" + util.binToDec(util.getRd(bin));
				ins += ", $" + util.binToDec(util.getRt(bin)) + ", " + util.binToDec(util.getOperand(bin));
			}	
			//sra rd, rt, shamt 
			else if(util.getFn(bin).equalsIgnoreCase("000011")) {
				ins = util.imapR.get(000011) + " $" + util.binToDec(util.getRd(bin));
				ins += ", $" + util.binToDec(util.getRt(bin)) + ", " + util.binToDec(util.getOperand(bin));
			}	
			//sllv rd, rt, rs
			else if(util.getFn(bin).equalsIgnoreCase("000100")) {
				ins = util.imapR.get(000100) + " $" + util.binToDec(util.getRd(bin));
				ins += ", $" + util.binToDec(util.getRt(bin)) + ", $" + util.binToDec(util.getRs(bin));	
			}	
			//srlv rd, rt, rs
			else if(util.getFn(bin).equalsIgnoreCase("000110")) {
				ins = util.imapR.get(000110) + " $" + util.binToDec(util.getRd(bin));
				ins += ", $" + util.binToDec(util.getRt(bin)) + ", $" + util.binToDec(util.getRs(bin));
			}	
			//srav rd rt rs
			else if(util.getFn(bin).equalsIgnoreCase("000111")) {
				ins = util.imapR.get(000111) + " $" + util.binToDec(util.getRd(bin));
				ins += ", $" + util.binToDec(util.getRt(bin)) + ", $" + util.binToDec(util.getRs(bin));
			}	
			//jr rs 
			else if(util.getFn(bin).equalsIgnoreCase("001000")) {
				ins = util.imapR.get(001000) + " $" + util.binToDec(util.getRs(bin));
			}
			//syscall
			else if(util.getFn(bin).equalsIgnoreCase("001100")) {
				ins = util.imapR.get(001100);
			}
		}

		switch(util.getOpcode(bin)){
			//identificando instruções do tipo I
		case "100100": //lbu rt, offset(rs)
			ins = util.imapIJ.get(100100) + " $" + util.binToDec(util.getRt(bin));
			ins += ", " + util.binToDec(util.getOperand(bin)) + "($" + util.binToDec(util.getRs(bin)) + ")";
		break;
		
		case "001010": //slti rt, rs, offset
			ins = util.imapIJ.get(001010) + " $" + util.binToDec(util.getRt(bin));
			ins += ", $" + util.binToDec(util.getRs(bin)) + ", " + util.binToDec(util.getOperand(bin));
		break;
			case "001000": //addi rt, rs, operand
				ins = util.imapIJ.get(001000) + " $" + util.binToDec(util.getRt(bin));
				ins += ", $" + util.binToDec(util.getRs(bin)) + ", " + util.binToDec(util.getOperand(bin));	
			break;

			case "001001": //addiu rt, rs, operand
				ins = util.imapIJ.get(001001) + " $" + util.binToDec(util.getRt(bin));
				ins += ", $" + util.binToDec(util.getRs(bin)) + ", " + util.binToDec(util.getOperand(bin));	
			break;

			case "001100": //andi rt, rs, operand
				ins = util.imapIJ.get(001100) + " $" + util.binToDec(util.getRt(bin));
				ins += ", $" + util.binToDec(util.getRs(bin)) + ", " + util.binToDec(util.getOperand(bin));	
			break;
			
			case "000100": //beq rs, rt, start
				ins = util.imapIJ.get(000100) + " $" + util.binToDec(util.getRs(bin));
				ins += ", $" + util.binToDec(util.getRt(bin)) + ", start";	
			break;
			
			case "000001": //bltz rs, start
				ins = util.imapIJ.get(000001) + " $" + util.binToDec(util.getRs(bin)) + ", start";	
			break;
			
			case "000101": //bne  rs, rt, start
				ins = util.imapIJ.get(000101) + " $" + util.binToDec(util.getRt(bin));
				ins += ", $" + util.binToDec(util.getRt(bin)) + ", start";	
			break;
			
			case "100000": //lb   rt, offset(rs)
				ins = util.imapIJ.get(100000) + " $" + util.binToDec(util.getRt(bin));
				ins += ", " + util.binToDec(util.getOperand(bin)) + "($" + util.binToDec(util.getRs(bin)) + ")";
			break;
			
			case "100110": //lbu  rt, offset(rs)
				ins = util.imapIJ.get(100110) + " $" + util.binToDec(util.getRt(bin));
				ins += ", " + util.binToDec(util.getOperand(bin)) + "($" + util.binToDec(util.getRs(bin)) + ")";
				
			break;
			
			case "001111": //lui  rt, offset
				ins = util.imapIJ.get(001111) + " $" + util.binToDec(util.getRt(bin)) + ", " + util.binToDec(util.getOperand(bin));
			break;
			
			case "100011": //lw   rt, offset(rs)
				ins = util.imapIJ.get(100011) + " $" + util.binToDec(util.getRt(bin));
				ins += ", " + util.binToDec(util.getOperand(bin)) + "($" + util.binToDec(util.getRs(bin)) + ")";
			break;

			case "001101": //ori  rt, rs, offset
				ins = util.imapIJ.get(001101) + " $" + util.binToDec(util.getRt(bin));
				ins += ", $" + util.binToDec(util.getRs(bin)) + ", " + util.binToDec(util.getOperand(bin));
			break;
			
			case "101000": //sb   rs, offset(rt)
				ins = util.imapIJ.get(101000) + " $" + util.binToDec(util.getRs(bin));
				ins += ", " + util.binToDec(util.getOperand(bin)) + "($" + util.binToDec(util.getRt(bin)) + ")";			
			break;
			
			case "101011": //sw   rt, offset(rs)
				ins = util.imapIJ.get(101011) + " $" + util.binToDec(util.getRt(bin));
				ins += ", " + util.binToDec(util.getOperand(bin)) + "($" + util.binToDec(util.getRs(bin)) + ")";			
			break;

			case "001110": //xori $t, $s, operand\offset
				ins = util.imapIJ.get(001110) + " $" + util.binToDec(util.getRt(bin));
				ins += ", $" + util.binToDec(util.getRs(bin)) + ", " + util.binToDec(util.getOperand(bin));
			break;

			//identificando instruções do tipo J
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
