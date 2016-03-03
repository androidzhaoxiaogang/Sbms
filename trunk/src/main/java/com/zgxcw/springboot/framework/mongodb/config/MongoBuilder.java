package com.zgxcw.springboot.framework.mongodb.config;

import java.io.Serializable;

import com.mongodb.MongoClientOptions.Builder;
import com.mongodb.WriteConcern;

/**
 * 数据源参数封装类
 * @description 
 * @project SpringBootMicroService
 * @author huangke
 * @date 2016年1月29日 
 * @version 1.0.0
 * @Copyright: Copyright 2007-2015 www.zgxcw.com.com All Rights Reserved
 * @Company: 诸葛修车网
 */
public class MongoBuilder extends Builder implements Serializable{

	private static final long serialVersionUID = -7413782437972449949L;
	
	public MongoBuilder(){
		this.writeConcern(WriteConcern.SAFE);
	}
	
	private int connectionsPerHostV;
	private int minConnectionsPerHostV;
	private boolean socketKeepAliveV;
	private int connectTimeoutV;
	private int socketTimeoutV;
	private boolean autoConnectRetryV;
	private int maxWaitTimeV;
	private int threadsAllowedToBlockForConnectionMultiplierV;

	public int getConnectionsPerHostV() {
		return connectionsPerHostV;
	}
	public void setConnectionsPerHostV(int connectionsPerHostV) {
		this.connectionsPerHost(connectionsPerHostV);
	}
	public int getMinConnectionsPerHostV() {
		return minConnectionsPerHostV;
	}
	public void setMinConnectionsPerHostV(int minConnectionsPerHostV) {
		this.minConnectionsPerHost(minConnectionsPerHostV);
	}
	public boolean isSocketKeepAliveV() {
		return socketKeepAliveV;
	}
	public void setSocketKeepAliveV(boolean socketKeepAliveV) {
		this.socketKeepAlive(socketKeepAliveV);
	}
	public int getConnectTimeoutV() {
		return connectTimeoutV;
	}
	public void setConnectTimeoutV(int connectTimeoutV) {
		this.connectTimeout(connectTimeoutV);
	}
	public int getSocketTimeoutV() {
		return socketTimeoutV;
	}
	public void setSocketTimeoutV(int socketTimeoutV) {
		this.socketTimeout(socketTimeoutV);
	}
	public boolean isAutoConnectRetryV() {
		return autoConnectRetryV;
	}
	@SuppressWarnings("deprecation")
	public void setAutoConnectRetryV(boolean autoConnectRetryV) {
		this.autoConnectRetry(autoConnectRetryV);
	}
	public int getMaxWaitTimeV() {
		return maxWaitTimeV;
	}
	public void setMaxWaitTimeV(int maxWaitTimeV) {
		this.maxWaitTime(maxWaitTimeV);
	}
	public int getThreadsAllowedToBlockForConnectionMultiplierV() {
		return threadsAllowedToBlockForConnectionMultiplierV;
	}
	public void setThreadsAllowedToBlockForConnectionMultiplierV(int threadsAllowedToBlockForConnectionMultiplierV) {
		this.threadsAllowedToBlockForConnectionMultiplier(threadsAllowedToBlockForConnectionMultiplierV);
	}
}
