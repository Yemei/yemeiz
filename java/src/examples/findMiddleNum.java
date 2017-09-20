package examples;

public class findMiddleNum {
    public int findMidNum(int num){
        if(num==0)
            return 0;
        int flag1=-1;
        int flag2=-1;
        while((flag2 & num)==num){
            flag1=flag2;
            flag2>>>=1;
        }
        flag1=(int)(Math.log(flag1)/Math.log(2))/2;
        flag1=(int)Math.pow(2,flag1);
        return flag1 & num;
    }
    public static void main(String[] args){
        findMiddleNum main=new findMiddleNum();
        System.out.println(Integer.toBinaryString(4)+" "+main.findMidNum(4));
        System.out.println(Integer.toBinaryString(-1)+" "+main.findMidNum(-1));
    }
}
