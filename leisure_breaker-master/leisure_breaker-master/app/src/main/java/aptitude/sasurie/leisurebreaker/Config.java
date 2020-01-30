package aptitude.sasurie.leisurebreaker;

/**
 * Created by vijay on 02-02-2018.
 */

public class Config {
    //Address of our scripts of the CRUD
    public static final String URL_REGISTER ="http://www.sasurie.com/aptitude/db/register.php";
    public static final String URL_LOGIN  = "http://www.sasurie.com/aptitude/db/validate.php";
    public static final String URL_RESULT = "http://www.sasurie.com/aptitude/db/setresult.php";
    public static final String URL_GET_QUESTIONDETAIL = "http://www.sasurie.com/aptitude/db/gettestdetail.php";
    public static final String URL_GET_QUESTION = "http://www.sasurie.com/aptitude/db/getquestion.php?testid=";


    public static final String KEY_REGNO = "regno";
    public static final String KEY_NAME = "name";

    public static final String KEY_EMAIL ="email";
    public static final String KEY_PHNO ="mobno";
    public static final String KEY_DEPT ="dept";
    public static final String KEY_YEAR ="year";
    public static final String KEY_PASSWORD ="password";

    public static final String KEY_CHOICE1="ch1";
    public static final String KEY_QUESTION="question";
    public static final String KEY_CHOICE2="ch2";
    public static final String KEY_CHOICE3="ch3";
    public static final String KEY_CHOICE4="ch4";
    public static final String KEY_ANSWER="ans";
    public static final String KEY_TESTID="testid";
    public static final String KEY_TESTNAME="testnamee";
    public static final String KEY_SCORED="scored";
    public static final String KEY_QUESTIONS="questions";
    //JSON Tags
    public static final String TAG_JSON_ARRAY="result";
    public static final String TAG_TESTID = "testid";
    public static final String TAG_TEST_NAME = "testname";
    public static final String TAG_QUESTIONS = "questions";
    public static final String TAG_MARKS = "marks";
    public static final String TEST_ID = "testid";
    public static final String TEST_MARK="mark" ;
    public static final String TEST_DURATION="duration" ;

}
