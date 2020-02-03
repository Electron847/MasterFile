package Cryptology;
import stdlib.*;

public class VigenereCoincidenceFactor {
	public static void main(String[] args) {
		String cipherText = "QHDLXNQLYNGAIGWBCERJFEARNIBKXUSVGZXKYNPXXTKGAATZRQCRFYIDCCLYXHUQXEIXFAFGEAMMALYRGAYXQMTGACDJSYRTLEXUVRVIYFFEGXFKOYSPHGBBYTRESOXUNTXXAKLUAWYDINAAWCZWIFVMCROIUCEIFJYDJAYZJBEOTMUSGAGAYYQNIPTFPYMCBOYDYYSVGWDOJTBZLMFBYJXLQCUDRRIGMIUYWMQUUFRPCZQHTVJOUJSMNRVQQZEJYLACNHRFCPTFENZYEJCLYMBQUCGUMYQDBUAWLQTMOAXCZJBEABHQJYEAMQQDNIRLNTUINRMCYUJAQTZQMGOEXUDEONQPIDBXWNKNIEUNQMBQDUFGXLFXYBVKNTEZCBFJGJUTVHHMBWOZIFQNCTLMBQELYVGNTUHIAXNQUHSROYZJCEFUIACVOBFVAEGBBHGNEIMOHIYRIOZQ";
		int ciTextLen = cipherText.length();
		
		char[] arrayText = cipherText.toCharArray();

		for (int shift=1; shift<14; shift++){
			int coincidenceCount = 0;
			for (int index = 0; index < ciTextLen-1; index++) {
				int shiftedIndex = (index + shift) % ciTextLen;
				if (arrayText[index] == arrayText[shiftedIndex])
					coincidenceCount++;
			}
		StdOut.println("Shift: " + shift + ". Coincidence: " + coincidenceCount);
		}

	}
}
