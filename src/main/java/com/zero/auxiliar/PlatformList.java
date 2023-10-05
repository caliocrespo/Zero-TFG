package com.zero.auxiliar;

import java.util.List;

import com.zero.domain.Platform;

public class PlatformList {
	private int count;
    private String next;
    private String previous;
    private List<PlatformAPI> results;
	public int getCount() {
		return count;
	}
	public String getNext() {
		return next;
	}
	public String getPrevious() {
		return previous;
	}
	public List<PlatformAPI> getResults() {
		return results;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public void setNext(String next) {
		this.next = next;
	}
	public void setPrevious(String previous) {
		this.previous = previous;
	}
	public void setResults(List<PlatformAPI> results) {
		this.results = results;
	}
    
    
}
