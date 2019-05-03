import java.util.Arrays;
import java.util.Stack;

class StringCalculator{
	private String expression;//����
	private String[]postFix_exp;//����ǥ����
	public StringCalculator() {}
	public void setExpression(String expression) {
		this.expression = expression;
		ToPostfix toPostfix = new ToPostfix(expression);
		postFix_exp = toPostfix.returnChanged();//������ ����ǥ��������
	}
	public String calculateStr() {//����� return�ϴ� �޼ҵ�
		try {
			if (postFix_exp.length == 1) {
				return postFix_exp[0];
			}//(+-)Infinity�� �޴� ��� �״�� ��ȯ
		}catch(NullPointerException npe) {
			return null;//������ null�� �޾��� ��� null��ȯ
		}
		double result = 0.0;
		try {
//			System.out.println(Arrays.toString(postFix_exp));
			Stack<Double>digit= new Stack<Double>();
			for(int i = 0; i < postFix_exp.length; i++) {
				try {
					double number = Double.parseDouble(postFix_exp[i]);
					digit.push(number);//������ ��� stack�� ����ִ´�
				}catch(NumberFormatException NE) {//���ڰ� �ƴ� ���
					double tempResult = 0.0;	//�ӽ� ����
					try {
						char operator = postFix_exp[i].trim().charAt(0);
						double topStack = digit.pop();//�� ���� ��
						double nextTopStack = digit.pop();//������ ��
						switch(operator) {
						case '+': tempResult = nextTopStack + topStack;break;
						case '-': tempResult = nextTopStack - topStack;break;
						case '*': tempResult = nextTopStack * topStack;break;
						case '/': tempResult = nextTopStack / topStack;break;
						}//���
						digit.push(tempResult);//��� ����� stack����
					}catch(java.lang.StringIndexOutOfBoundsException sie) {
						System.out.println("�߸� �Է��ϼ̽��ϴ�. �Է°�: [" + expression + "]�� Ȯ���� �ּ���.");
					}//�����ڸ� �ߺ� �Է��� ��� (1***2)

				}catch(Exception e) {
					System.out.println(e.toString() + "in StringCalculator.calculateStr");
				}
			}
			result = digit.pop();//���� ����� stack�� ��������
			return Double.toString(result);//��� ��ȯ
		}catch(java.util.EmptyStackException ESE) {String error = "���� �߻�: " + expression + "Ȯ���� �ּ���.";
		return error;//���� �߻�
		}
	}	
}
