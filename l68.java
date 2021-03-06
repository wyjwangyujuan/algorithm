package L68;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class l68 {
	static String[] s; 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sr=new Scanner(System.in);
		
		int n=sr.nextInt();
		sr.nextLine();
		s=new String[n];
		for(int i=0;i<n;i++){
			s[i]=sr.nextLine();
		}
		int m=sr.nextInt();
	
		List<String> ans = new ArrayList<>();
		ans=fullJustify(s,m);
		for(String i:ans){
			System.out.println(i);
		}
		sr.close();
	}
	public static List<String> fullJustify(String[] words, int maxWidth) {
	    List<String> ans = new ArrayList<>();
	    //力扣题刷少了，一直用字符串去弄，本来人家的返回值是List<String>
	    int Len = 0;//用来记录一行单词的长度以及单词之间的一个空格
	    int s = 0;//单词在每一行的开始个数
	    int e = 0;//此时单词的个数（未超过最大长度时
	    for (int i = 0; i < words.length;) {
	        //判断加入该单词是否超过最长长度
	        //分了两种情况，一种情况是加入第一个单词，不需要多加 1
	        //已经有单词的话，再加入单词，需要多加个空格，所以多加了 1
	        if (Len == 0 && Len + words[i].length() <= maxWidth//第一个或者后面的单词长度没超过总长度
	            || Len > 0 && Len + 1 + words[i].length() <= maxWidth) {
	            e++;//增加
	            if (Len == 0) {//如果第一个，并且没超过总长度，就累加长度
	                Len = Len + words[i].length();
	            } else {
	                Len = Len + 1 + words[i].length();
	                //不是第一个，超过总长度，累加长度但还得加空格，因为单词之间空格隔开
	            }
	            i++;//此时，才加
	        } else {
	            int sub = maxWidth - Len + (e- s) - 1;//记录剩下用来补空格的位置的个数
	            if (e - s == 1) {//如果一行只有一个单词，从这个单词的位置
	                String blank = getStringBlank(sub);//
	                ans.add(words[s] + blank);//只有一行，直接输出左对齐，后面加空格
	            } else {
	                StringBuilder temp = new StringBuilder();
	                temp.append(words[s]);//吃这一行的第一个，
	                int averageBlank = sub / ((e - s) - 1);//看剩下的位置是否可以装下此行单词个数的空格
	                //因为要两端对齐or左对齐
	                 //如果除不尽，计算剩余空格数
	                int missing = sub - averageBlank * ((e - s) - 1);//剩下的空格（分成（what a pig）三个数有了两个空格，；因为题目要求如果空格不能分，就加左边）
	                //e-s此行单词个数，-1是单词之间空格个数
	                String blank = getStringBlank(averageBlank + 1);
	                //意思是从左边开始填空格（因为第一个早就吃了，所以从左边
	                //只会是what    is这种之间的空格
	                int k = 1;//
	                for (int j = 0; j < missing; j++) {
	                	//此时分配好的空格加上后面的单词
	                    temp.append(blank + words[s+k]);
	                    k++;
	                }
	                blank = getStringBlank(averageBlank);
	                for (; k <(e - s); k++) {
	                    temp.append(blank + words[s+k]);
	                }
	                ans.add(temp.toString());

	            }
	            s = e;//前一行的最后一个的位置，就是这一行第一个的位置
	            Len = 0;//此行长度重置

	        }
	    }
	    //循环结束，最后一行，特殊要求。左对齐，
	    StringBuilder temp = new StringBuilder();
	    temp.append(words[s]);
	    //先不管，反正第一个顶格写
	    for (int i = 1; i < (e - s); i++) {
	        temp.append(" " + words[s+i]);
	        //后面每一个空格填一个单词
	    }
	    temp.append(getStringBlank(maxWidth -Len));//填完后，剩下的没补满，用空格来
	    ans.add(temp.toString());//把最后的临时变量存储到最终的列表中去
	    return ans;
	}
	private static  String getStringBlank(int n) {
		//此方法的作用是用空格填充剩下的位置
	    StringBuilder str = new StringBuilder();
	    for (int i = 0; i < n; i++) {
	        str.append(" ");
	    }
	    return str.toString();
	}
}