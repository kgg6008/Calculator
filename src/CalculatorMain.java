import java.util.Scanner;
public class CalculatorMain {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringCalculator calc = new StringCalculator();//���� Ŭ���� ����
		SaveLoadResult slr = new SaveLoadResult();//���� ����� Ŭ���� ����
		boolean isOk = false;
		boolean isFinish = false;
		do {
			int option = 0;//�ɼ� �ʱ� ��
			System.out.print("�ɼ��� �Է��ϼ���. (1. ���п� ����, 2. ������ȯ, 3. ��� ���, 4. ��� ������ ����) >> ");
			do {//�ɼ� ����� �Է��Ҷ�����
				isOk = true;
				String temp_sOption = sc.next();
				try {
					option = Integer.parseInt(temp_sOption);
					if(option > 4 || option < 1) {
						System.out.print("�߸� �Է��ϼ̽��ϴ�. �ٽ� �Է��� �ּ���. >> ");
						isOk = false;
					}
				}catch(NumberFormatException NFE) {
					System.out.print("�߸� �Է��ϼ̽��ϴ�. �ٽ� �Է��� �ּ���. >> ");
					isOk = false;
				}
			}while(!isOk);
			sc.nextLine();//sc.next()�� ��� ������ \n�� �Է¹ް��־ �ʱ�ȭ
			switch(option) {
			case 1: System.out.print("������ �Է��ϼ���.(������ �ʿ��Ͻø� help�� �Է����ּ���.) >> ");
			String expression = sc.nextLine();
			if(expression.equalsIgnoreCase("help")) {//����
				slr.loadHelp();
				System.out.print("������ �Է��ϼ���. >> ");
				expression = sc.nextLine();
			}
			calc.setExpression(expression);
			String result = calc.calculateStr();
			System.out.println("Result = " + result);
			slr.saveData(expression, result);
			break;//1. ����
			case 2: ChangeNotation cn = new ChangeNotation();
			System.out.println("Result = " + cn.notationConv());
			break;//2. ������ȯ�� 
			case 3:
			slr.loadData();
			break;
			case 4:System.out.print("��й�ȣ�� �Է��ϼ���. >> ");
			String pw = sc.next();
			slr.delData(pw);
			break;//���� ����
			}
			System.out.print("�ٽ� �����Ͻðڽ��ϱ�? [Y / N] >> ");//ó������ �ٽ�
			isOk = false;
			do {//y/n�Ǵ�
				isOk = true;
				String temp_ans = sc.next();
				if (temp_ans.equalsIgnoreCase("Y")) {
					isFinish = false;
				}
				else if(temp_ans.equalsIgnoreCase("N")) {
					isFinish = true;
				}
				else {
					System.out.print("�߸� �Է��ϼ̽��ϴ�. �ٽ� �Է��� �ּ���. >> ");
					isOk = false;
				}
			}while(!isOk);
		}while(!isFinish);
		System.out.println("���α׷� ����");
		sc.close();
	}

}
