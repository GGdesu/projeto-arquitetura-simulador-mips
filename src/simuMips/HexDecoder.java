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
			
		}
		
		
		return ins;		
	}

}
