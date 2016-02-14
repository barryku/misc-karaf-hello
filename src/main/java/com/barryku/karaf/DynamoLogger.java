package com.barryku.karaf;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.amazonaws.regions.Region;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.ItemCollection;
import com.amazonaws.services.dynamodbv2.document.QueryOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;

public class DynamoLogger {

	private static final String LOG_TABLE = "auto-deploy-log";
	private DynamoDB db;

	public DynamoLogger(Region region) {
		//AmazonDynamoDB dynamo = new AmazonDynamoDBClient(AwsCredentialFactory.getCredential());
		AmazonDynamoDB dynamo = new AmazonDynamoDBClient();
		dynamo.setRegion(region);
		db = new DynamoDB(dynamo);
	}

	public List<Item> getLog(String jobId) {
		
		Table table = db.getTable(LOG_TABLE);

		QuerySpec spec = new QuerySpec()
		    .withHashKey("jobId", jobId);
//		.withKeyConditionExpression("jobId = :v_id")
//	    .withValueMap(new ValueMap()
//	        .withString(":v_id", jobId));

		ItemCollection<QueryOutcome> items = table.query(spec);
		List<Item> result = new ArrayList<Item>();
		
		Iterator<Item> iterator = items.iterator();
		while (iterator.hasNext()) {
		    result.add(iterator.next());
		}
		
		return result;
	}

}
