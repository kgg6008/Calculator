import java.io.*;

class SaveLoadResult {
	private String loadPath = "History.txt";//불러올 경로
	private String savePath = "History.txt";//저장할 경로
	private String helpPath = "help.txt";//도움말 경로
	private String passWord = "deleteAll";//삭제 비밀번호
	public SaveLoadResult() {}
	public void loadData() {//데이터를 불러오는 메소드
		try {
			File file = new File(loadPath);
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String stringLine = null;
			while((stringLine = br.readLine()) != null) {
				System.out.println(stringLine);
			}
			br.close();
			fr.close();
		}catch(FileNotFoundException fne) {
			System.out.println("저장된 기록 파일이 없습니다.");//파일이 없을 경우
		}catch(Exception e) {}
	}
	public void saveData(String expression, String result) {//데이터를 저장하는 메소드
		try {
			File file = new File(savePath);
			FileWriter fw = new FileWriter(file, true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw,true);
			pw.write(expression + " = " + result + "\n");
			pw.flush();
			pw.close();
			bw.close();
			fw.close();
		}catch(IOException ioe) {}
	}
	public void delData(String pw) {//데이터를 삭제하는 메소드
		if(pw.equals(passWord)) {//비밀번호가 같을경우
			File file = new File(savePath);
			if(file.exists()) {//존재하면 삭제
				if(file.delete())
					System.out.println("데이터 파일이 삭제되었습니다.");
				else
					System.out.println("삭제 중 오류가 발생했습니다.");
			}
			else {
				System.out.println("파일이 존재하지 않습니다.");
			}
		}
		else {
			System.out.println("비밀 번호가 틀렸습니다.");
		}
	}
	public void loadHelp() {//도움말불러오기
		try {
			File file = new File(helpPath);
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String stringLine = null;
			while((stringLine = br.readLine()) != null) {
				System.out.println(stringLine);
			}
			br.close();
			fr.close();
		}catch(FileNotFoundException fne) {
			System.out.println("저장된 기록 파일이 없습니다.");
		}catch(Exception e) {}
		
	}
}
