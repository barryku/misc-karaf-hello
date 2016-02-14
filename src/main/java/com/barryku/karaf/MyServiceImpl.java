/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.barryku.karaf;

import java.util.List;

import javax.ws.rs.core.Response;

import com.amazonaws.regions.RegionUtils;
import com.amazonaws.services.dynamodbv2.document.Item;

public class MyServiceImpl implements MyService {

    public Response getJobLog(String jobId) {
    	
    	DynamoLogger logger = new DynamoLogger(RegionUtils.getRegion("us-west-2"));
    	List<Item> items = logger.getLog(jobId);
    	StringBuilder sb = new StringBuilder();
    	sb.append("[");
    	for (Item item:items) {
    		sb.append(item.toJSONPretty()).append(",");
    	}
    	
    	return Response.ok().entity(
    			sb.length()>1 ? sb.substring(0,sb.length()-1) + "]" : "[]")
    			.header("Access-Control-Allow-Origin", "*")
    			.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
    			.allow("OPTIONS").build();
        
    }
    
}