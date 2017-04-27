package com.hurs.alejandrogs.hurs01z;

import java.security.MessageDigest;

/**
 * Created by alejandrogs on 24/04/17.
 */

public class keysAlgoritmos {

    public String key(String password){

        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());

            byte byteData[] = md.digest();

            //convert the byte to hex format method 1
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }



            //convert the byte to hex format method 2
            StringBuffer hexString = new StringBuffer();
            for (int i=0;i<byteData.length;i++) {
                String hex=Integer.toHexString(0xff & byteData[i]);
                if(hex.length()==1) hexString.append('0');
                hexString.append(hex);
            }
            password = hexString.toString();
            password = password.substring(7,23);
            password = password.toUpperCase();
        }catch (Exception ex) {
            System.out.println("Error");

        }

        return password;
    }
}
