/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package postscorrespondenceproblem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author zhuan
 */
public class PostsCorrespondenceProblem {
    static int m,n;
    static String[] a;
    static String[] b;
    static HashMap<String,ArrayList<Integer>> getPossibleIndexBackup=new HashMap();
    static HashMap<String,ArrayList<Integer>> getSequenceBackup=new HashMap();

    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner sc=new Scanner(System.in);
        m=sc.nextInt();
        n=sc.nextInt();
        a=new String[n];
        b=new String[n];
        sc.nextLine();
        for (int i=0;i<n;i++) {
            a[i]=sc.nextLine();
        }
        
        for (int i=0;i<n;i++) {
            b[i]=sc.nextLine();
        }
        
        ArrayList<Integer> result=getSequence();
        if (result!=null && !result.isEmpty()) {
            System.out.println(result.size());
            for (int i=0;i<result.size();i++)
                System.out.println(result.get(i)+1);
        } else {
            System.out.println("No solution.");
        }
    }

    private static ArrayList<Integer> getSequence() {
        
        ArrayList<Integer> il=new ArrayList();
        return getSequence("","",il);
        
    }

    private static ArrayList<Integer> getSequence(String s1,String s2,ArrayList<Integer> il) {
        //String s1=concatStringList(il,a);
        //String s2=concatStringList(il,b);
        String key=s1+"\n"+s2;
        
        if (getSequenceBackup.containsKey(key)) {
            return getSequenceBackup.get(key);
        }
        if (!s1.isEmpty() && s1.equals(s2)) return il;
        if (il.size()>=m ) {
            getSequenceBackup.put(key,null);
            return null;
        } 
        ArrayList<Integer> ia=getPossibleNextStringIndex(s1,s2);
        for (int index:ia) {
            il.add(index);
            ArrayList<Integer> newIl=getSequence(s1+a[index],s2+b[index],il);
            if (newIl!=null) 
            {
                getSequenceBackup.put(key,newIl);
                return newIl;
            }
            il.remove(il.size()-1);
        }
        getSequenceBackup.put(key,null);
        return null;
    }

    /*private static String concatStringList(ArrayList<Integer> il, String[] sa) {
        String rs="";
        for (int i:il) {
            rs+=sa[i];
        }
        return rs;
    }*/

    private static ArrayList<Integer> getPossibleNextStringIndex(String s1, String s2) {
        String key=s1+"\n"+s2;
        
        if (getPossibleIndexBackup.containsKey(key)) {
            return getPossibleIndexBackup.get(key);
        }
        ArrayList<Integer> ret=new ArrayList();
        for (int i=0;i<n;i++) {
            if (match(s1,s2,i))
                ret.add(i);
            /*String tmpS1=s1+a[i];
            String tmpS2=s2+b[i];
            
            if (tmpS1.length()>=tmpS2.length() && tmpS1.lastIndexOf(tmpS2)>=0) {
                ret.add(i);
            } else if (tmpS2.lastIndexOf(tmpS2)>=0) {
                ret.add(i);
            }*/
        }
        getPossibleIndexBackup.put(key, ret);
        return ret;
    }

    private static boolean match(String s1, String s2, int i) {
        int l1=s1.length();
        int l2=s2.length();
        int start=(l1<=l2)?l1:l2;
        int l3=a[i].length()+l1;
        int l4=b[i].length()+l2;
        int end=(l3<=l4)?l3:l4;

        String tmpS1=s1+a[i];
        String tmpS2=s2+b[i];
        for (int j=start;j<end;j++) {
            if (tmpS1.charAt(j)!=tmpS2.charAt(j))
                return false;
        }
        return true;
    }
    
}
