import java.util.Scanner;
class ChangeNotation extends SaveLoadResult{
	private int option;//�ɼ�
	private double dNum = 0.0;//�Ǽ� ��
	private int iNum = 0;//������
	private boolean isInt = false;//���� ���� �Ǵ�
	Scanner sc = new Scanner(System.in);
	public ChangeNotation() {}
	private void selectOption() {
		option = 0;
		boolean isOk = false;//�ɼ��� �������� �Ǵ��ϴ� bool
		System.out.println("�ɼ��� �����ϼ���. (1. 2����, 2. 8����, 3. 16����) >> ");
		do{
			isOk = true;
			String temp_option = sc.next();//�ɼ� ��(sc.int�ǰ�� ���� �߻��� ������ ����)
			try {				
				option = Integer.parseInt(temp_option);
				if(option < 1 || option > 3) {
					System.out.print("�߸� �Է��ϼ̽��ϴ�. �ٽ� �Է��� �ּ���. >> ");
					isOk = false;
				}//option�� ���� ���� ���
			}catch(NumberFormatException NFE){
				System.out.print("�߸� �Է��ϼ̽��ϴ�. �ٽ� �Է��� �ּ���. >> ");
				isOk = false;
			}//option�� ������ �ƴҰ��
		}while(!isOk);
	}
	private void inputNum() {
		boolean isOk = false;
		System.out.println("��ȯ�� ���� �Է��ϼ���. >> ");
		do {
			isOk = true;
			String input = sc.next();//���� �޴´�.
			try {
				iNum = Integer.parseInt(input);
				isInt = true;//�����ΰ�??
			}catch(NumberFormatException NFE) {
				try {
					dNum = Double.parseDouble(input);
					isInt = false;//�Ǽ��ΰ�??
				}catch(NumberFormatException NFE2) {
					System.out.print("�߸� �Է��ϼ̽��ϴ�. �ٽ� �Է��� �ּ���. >> ");
					isOk = false;//�Ѵ� �ƴϸ� �ٽ� �Է¹޴´�.
				}
			}
		}while(!isOk);
	}
	public String notationConv() {//���� ��ȯ �޼ҵ�
		selectOption();//�ɼ�
		inputNum();//�� �Է�
		switch(option) {//case 1: 2���� 2: 8���� 3: 16����
		case 1: if(isInt) {//����
			String expression = Integer.toString(iNum) + "�� ������ ���� ";
			saveData(expression, Integer.toBinaryString(iNum));
			return Integer.toBinaryString(iNum);
		}
		else {//�Ǽ�
			long LB = Double.doubleToRawLongBits(dNum);
			String expression = Double.toString(dNum) + "�� ������ ���� ";
			saveData(expression, Long.toBinaryString(LB));
			return Long.toBinaryString(LB);
		}
		case 2: if(isInt) {
			String expression = Integer.toString(iNum) + "�� 8���� ���� ";
			saveData(expression, Integer.toOctalString(iNum));
			return Integer.toOctalString(iNum);
		}
		else {
			long LB = Double.doubleToRawLongBits(dNum);
			String expression = Double.toString(dNum) + "�� 8���� ���� ";
			saveData(expression, Long.toOctalString(LB));
			return Long.toOctalString(LB);
		}
		case 3: if(isInt) {
			String expression = Integer.toString(iNum) + "�� 16���� ���� ";
			saveData(expression, Integer.toHexString(iNum));
			return Integer.toHexString(iNum);
		}
		else {
			String expression = Double.toString(dNum) + "�� 16���� ���� ";
			saveData(expression, Double.toHexString(dNum));
			return Double.toHexString(dNum);
		}
		default:
			return null;//��� �ƴϸ� nulló��(�߸��Է� ��)
		}
	}
	
}
