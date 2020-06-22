package base;

import java.io.*;

public class FileUtil {

	 public static void ReadFileByLine(String filename) throws Exception{
	       BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
	        String line = null;
	        while ((line = bufferedReader.readLine()) != null) {
	            System.out.println(line);
	        }
	        bufferedReader.close();
	    }
	 public static void WriteFile(String filename,boolean append) throws Exception{
		 String[] a ={"a","b","c","d"};

		 File file = new File(filename);
         FileOutputStream fos = null;
         if(!file.exists()){
             file.createNewFile();
             fos = new FileOutputStream(file);//首次写入
         }else{
             fos = new FileOutputStream(file,append);//在文件末尾追加写入
         }
         OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");

         //遍历数据
         for(int i=0;i<a.length;++i){
     		System.out.println(a[i]);
     	    osw.write(   "{path: '/"+a[i].split(" ")[0]+"', name: 'page_"+i+"', component: page('"+a[i].replace(" ", "")+"'),},"+"\n"   );
            osw.write("\n");
         }
         osw.close();
	}
	 public static void main(String[] args) throws Exception {
		 WriteFile("d:\\ary2.txt",true);
		 ReadFileByLine("d:\\ary2.txt");
	 }
}
