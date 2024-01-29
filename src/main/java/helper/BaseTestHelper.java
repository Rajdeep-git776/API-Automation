package helper;

import java.io.File;
import java.util.Date;

public class BaseTestHelper {

    public static void createFolder(String path){
        File file = new File(path);
        if(!file.exists()){
            file.mkdir(); // this is used to create a folder if it does not exist
        }else{
            System.out.println("Folder already exists");
        }
    }


    public static String timeStamp(){
        Date now = new Date();
        String TimeStamp = now.toString().replace(":","_");
        return TimeStamp;


    }
}
