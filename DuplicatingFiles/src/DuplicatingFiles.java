import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class DuplicatingFiles {

	public static boolean compareFiles(File fileOne, File fileTwo)
			throws Exception {
		if (fileOne.length() != fileTwo.length())
			return false;

		FileReader fr1 = new FileReader(fileOne);
		FileReader fr2 = new FileReader(fileTwo);

		BufferedReader br1 = new BufferedReader(fr1);
		BufferedReader br2 = new BufferedReader(fr2);

		String line1 = null;
		String line2 = null;
		boolean flag = true;

		while ((flag) && ((line1 = br1.readLine()) != null)
				&& ((line2 = br2.readLine()) != null)) {
			if (!line1.equalsIgnoreCase(line2))
				flag = false;
			else
				flag = true;
		}

		br1.close();
		br2.close();

		return flag;
	}

	public static ArrayList<File> listDuplicatingFiles(File dir)
			throws Exception {

		File[] listOfFiles = dir.listFiles();
		ArrayList<File> result = new ArrayList<File>();

		for (int i = 0; i < listOfFiles.length; i++) {

			if (listOfFiles[i].isDirectory()) {
				result.addAll(listDuplicatingFiles(listOfFiles[i]));
			} else {
				result.add(listOfFiles[i]);
			}
		}

		for (int i = 0; i < result.size(); i++) {
			for (int j = 0; j < result.size(); j++) {
				if (i != j && compareFiles(result.get(i), result.get(j)))
					result.remove(result.get(i));
			}

		}

		return result;
	}

	private static final ArrayList<String> finalStr(ArrayList<File> files) {
		ArrayList<String> finRes = new ArrayList<String>();
		for (File f : files) {
			finRes.add(f.getName());
		}
		return finRes;
	}

	public static void main(String[] args) throws Exception {

		File dir = new File("D:/root");
		System.out.println(finalStr(listDuplicatingFiles(dir)));
	}
}
