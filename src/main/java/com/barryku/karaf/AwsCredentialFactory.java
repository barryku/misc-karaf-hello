package com.barryku.karaf;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;

public class AwsCredentialFactory {

	public static final AWSCredentials getCredential() {
		Path file = Paths.get(System.getProperty("user.home") + "/.aws/credentials");
		
		Properties props = new Properties();
		FileInputStream in;
		try {
			in = new FileInputStream(file.toString());
			props.load(in);
			in.close();
		} catch (IOException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return new BasicAWSCredentials((String)props.get("aws_access_key_id"), 
				(String)props.get("aws_secret_access_key"));
		
	}
}
