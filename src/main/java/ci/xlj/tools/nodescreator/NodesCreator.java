/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License") you may not use this file except in compliance with
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

//      Contributors:      Xu Lijia

package ci.xlj.tools.nodescreator;

import ci.xlj.libs.jenkinsvisitor.JenkinsVisitor;
import ci.xlj.libs.utils.StringUtils;

/**
 * This is a tool used to post information to the API of pi-nodescreator plugin.
 * 
 * @author kfzx-xulj
 */
public class NodesCreator {

	public static void main(String[] args) {

		if (!StringUtils.isValid(args)) {
			System.out.println("Invalid parameters. Please check and retry.");
			System.exit(-1);
		}

		JenkinsVisitor v = new JenkinsVisitor(args[0]);
		boolean isLogin = v.login(args[1], args[2]);

		if (isLogin) {
			StringBuilder b = new StringBuilder();
			b.append(args[3]).append(",").append(args[4]).append(",")
					.append(args[5]).append(",").append(args[6]).append(",")
					.append(args[7]);
			v.doPost(args[0] + "/plugin/pi-nodescreator/createNode",
					b.toString());

			if (v.getResponseStatusCode() == 200) {
				System.out.println("Node added.");
			} else {
				System.err.println("Error occured. Reasons:\n"
						+ v.getResponseContent());
			}

		}else{
			System.err.println("Invalid username or password. Please check and retry.");
		}
	}
}
