public class Tools {
    public static String ArrayToString(Object[] obj){
        String str = "[";
        for(int i = 0; i < obj.length; i++){
            str += (obj[i] + ", ");
        }
        if(str.length() > 1){
            str = str.substring(0, str.length() - 2);
        }
        str += "]";
        return str;
    }

}
