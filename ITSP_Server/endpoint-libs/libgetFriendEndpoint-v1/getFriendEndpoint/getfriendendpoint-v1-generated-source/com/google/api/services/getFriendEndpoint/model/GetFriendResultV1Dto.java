/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
/*
 * This code was generated by https://code.google.com/p/google-apis-client-generator/
 * (build: 2013-08-01 15:32:38 UTC)
 * on 2013-08-03 at 09:40:23 UTC 
 * Modify at your own risk.
 */

package com.google.api.services.getFriendEndpoint.model;

/**
 * Model definition for GetFriendResultV1Dto.
 *
 * <p> This is the Java data model class that specifies how to parse/serialize into the JSON that is
 * transmitted over HTTP when working with the . For a detailed explanation see:
 * <a href="http://code.google.com/p/google-api-java-client/wiki/Json">http://code.google.com/p/google-api-java-client/wiki/Json</a>
 * </p>
 *
 * @author Google, Inc.
 */
@SuppressWarnings("javadoc")
public final class GetFriendResultV1Dto extends com.google.api.client.json.GenericJson {

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.util.List<UserV1Dto> friendList;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String result;

  /**
   * @return value or {@code null} for none
   */
  public java.util.List<UserV1Dto> getFriendList() {
    return friendList;
  }

  /**
   * @param friendList friendList or {@code null} for none
   */
  public GetFriendResultV1Dto setFriendList(java.util.List<UserV1Dto> friendList) {
    this.friendList = friendList;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getResult() {
    return result;
  }

  /**
   * @param result result or {@code null} for none
   */
  public GetFriendResultV1Dto setResult(java.lang.String result) {
    this.result = result;
    return this;
  }

  @Override
  public GetFriendResultV1Dto set(String fieldName, Object value) {
    return (GetFriendResultV1Dto) super.set(fieldName, value);
  }

  @Override
  public GetFriendResultV1Dto clone() {
    return (GetFriendResultV1Dto) super.clone();
  }

}
