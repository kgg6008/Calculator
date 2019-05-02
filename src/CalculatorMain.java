import java.util.Scanner;
public class CalculatorMain {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringCalculator calc = new StringCalculator();//계산기 클래스 생성
		SaveLoadResult slr = new SaveLoadResult();//파일 입출력 클래스 생성
		boolean isOk = false;
		boolean isFinish = false;
		do {
			int option = 0;//옵션 초기 값
			System.out.print("옵션을 입력하세요. (1. 공학용 계산기, 2. 진수변환, 3. 계산 기록, 4. 기록 데이터 삭제) >> ");
			do {//옵션 제대로 입력할때까지
				isOk = true;
				String temp_sOption = sc.next();
				try {
					option = Integer.parseInt(temp_sOption);
					if(option > 4 || option < 1) {
						System.out.print("잘못 입력하셨습니다. 다시 입력해 주세요. >> ");
						isOk = false;
					}
				}catch(NumberFormatException NFE) {
					System.out.print("잘못 입력하셨습니다. 다시 입력해 주세요. >> ");
					isOk = false;
				}
			}while(!isOk);
			sc.nextLine();//sc.next()의 경우 다음에 \n을 입력받고있어서 초기화
			switch(option) {
			case 1: System.out.print("수식을 입력하세요.(도움이 필요하시면 help를 입력해주세요.) >> ");
			String expression = sc.nextLine();
			if(expression.equalsIgnoreCase("help")) {//도움말
				slr.loadHelp();
				System.out.print("수식을 입력하세요. >> ");
				expression = sc.nextLine();
			}
			calc.setExpression(expression);
			String result = calc.calculateStr();
			System.out.println("Result = " + result);
			slr.saveData(expression, result);
			break;//1. 계산기
			case 2: ChangeNotation cn = new ChangeNotation();
			System.out.println("Result = " + cn.notationConv());
			break;//2. 진수변환기 
			case 3:
			slr.loadData();
			break;
			case 4:System.out.print("비밀번호를 입력하세요. >> ");
			String pw = sc.next();
			slr.delData(pw);
			break;//파일 삭제
			}
			System.out.print("다시 시작하시겠습니까? [Y / N] >> ");//처음으로 다시
			isOk = false;
			do {//y/n판단
				isOk = true;
				String temp_ans = sc.next();
				if (temp_ans.equalsIgnoreCase("Y")) {
					isFinish = false;
				}
				else if(temp_ans.equalsIgnoreCase("N")) {
					isFinish = true;
				}
				else {
					System.out.print("잘못 입력하셨습니다. 다시 입력해 주세요. >> ");
					isOk = false;
				}
			}while(!isOk);
		}while(!isFinish);
		System.out.println("프로그램 종료");
		sc.close();
	}

}
