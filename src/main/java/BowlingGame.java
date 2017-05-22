import java.util.*;

public class BowlingGame {
    ArrayList<String> scoreList;

    public int getCharValue(char c)
    {
        if (c=='X')
        {
            return 10;
        }else if (c=='-')
        {
            return 0;
        }
        if (c<='9'&&c>='0')
        {
            return c-'0';
        }
        return 0;
    }

    //index 是开始编号，length为长度，可能为1或者2
    public int getAnotherScore(int i,int lengnth){
        int res=0;
        if (lengnth==1)
        {
            res=getCharValue(scoreList.get(i).charAt(0));
        }else //如果是需要两个球的分数
        {
            if (scoreList.get(i).contains("/"))
            {
                return 10;
            }else
            {
                if (scoreList.get(i).length()==1)
                {
                    res=getCharValue(scoreList.get(i).charAt(0));
                    res+=getAnotherScore(i+1,1);
                }else
                {
                    res=getCharValue(scoreList.get(i).charAt(0));
                    res+=getCharValue(scoreList.get(i).charAt(1));
                }
            }
        }
        return res;
    }


    public int getBowlingScore(String bowlingCode) {

        String[] scoreArray;
        scoreList = new ArrayList();

        scoreArray = bowlingCode.split("\\|");
        scoreList.addAll(Arrays.asList(scoreArray));
        if (scoreList.size() > 11) {
            scoreList.remove(10);
        }

        int result=0;
        for (int i=0;i<10;i++)
        {
            if (scoreList.get(i).contains("X"))
            {
                result+=10+getAnotherScore(i+1,2);
            }else if (scoreList.get(i).contains("/"))
            {
                result+=10+getAnotherScore(i+1,1);
            }else
            {
                String tmp=scoreList.get(i);
                for (int k=0;k<tmp.length();k++)
                {
                    result+=getCharValue(tmp.charAt(k));
                }
            }
        }
        return result;
    }
}
