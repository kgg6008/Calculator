import java.util.Scanner;
class ChangeNotation extends SaveLoadResult{
	private int option;//옵션
	private double dNum = 0.0;//실수 값
	private int iNum = 0;//정수값
	private boolean isInt = false;//정수 여부 판단
	Scanner sc = new Scanner(System.in);
	public ChangeNotation() {}
	private void selectOption() {
		option = 0;
		boolean isOk = false;//옵션이 괜찮은지 판단하는 bool
		System.out.println("옵션을 선택하세요. (1. 2진수, 2. 8진수, 3. 16진수) >> ");
		do{
			isOk = true;
			String temp_option = sc.next();//옵션 값(sc.int의경우 오류 발생의 위험이 있음)
			try {				
				option = Integer.parseInt(temp_option);
				if(option < 1 || option > 3) {
					System.out.print("잘못 입력하셨습니다. 다시 입력해 주세요. >> ");
					isOk = false;
				}//option이 범위 외일 경우
			}catch(NumberFormatException NFE){
				System.out.print("잘못 입력하셨습니다. 다시 입력해 주세요. >> ");
				isOk = false;
			}//option이 정수가 아닐경우
		}while(!isOk);
	}
	private void inputNum() {
		boolean isOk = false;
		System.out.println("변환할 수를 입력하세요. >> ");
		do {
			isOk = true;
			String input = sc.next();//값을 받는다.
			try {
				iNum = Integer.parseInt(input);
				isInt = true;//정수인가??
			}catch(NumberFormatException NFE) {
				try {
					dNum = Double.parseDouble(input);
					isInt = false;//실수인가??
				}catch(NumberFormatException NFE2) {
					System.out.print("잘못 입력하셨습니다. 다시 입력해 주세요. >> ");
					isOk = false;//둘다 아니면 다시 입력받는다.
				}
			}
		}while(!isOk);
	}
	public String notationConv() {//진수 변환 메소드
		selectOption();//옵션
		inputNum();//수 입력
		switch(option) {//case 1: 2진수 2: 8진수 3: 16진수
		case 1: if(isInt) {//정수
			String expression = Integer.toString(iNum) + "의 이진수 값은 ";
			saveData(expression, Integer.toBinaryString(iNum));
			return Integer.toBinaryString(iNum);
		}
		else {//실수
			long LB = Double.doubleToRawLongBits(dNum);
			String expression = Double.toString(dNum) + "의 이진수 값은 ";
			saveData(expression, Long.toBinaryString(LB));
			return Long.toBinaryString(LB);
		}
		case 2: if(isInt) {
			String expression = Integer.toString(iNum) + "의 8진수 값은 ";
			saveData(expression, Integer.toOctalString(iNum));
			return Integer.toOctalString(iNum);
		}
		else {
			long LB = Double.doubleToRawLongBits(dNum);
			String expression = Double.toString(dNum) + "의 8진수 값은 ";
			saveData(expression, Long.toOctalString(LB));
			return Long.toOctalString(LB);
		}
		case 3: if(isInt) {
			String expression = Integer.toString(iNum) + "의 16진수 값은 ";
			saveData(expression, Integer.toHexString(iNum));
			return Integer.toHexString(iNum);
		}
		else {
			String expression = Double.toString(dNum) + "의 16진수 값은 ";
			saveData(expression, Double.toHexString(dNum));
			return Double.toHexString(dNum);
		}
		default:
			return null;//모두 아니면 null처리(잘못입력 시)
		}
	}
	
}
