package com.zero.auxiliar;

import java.util.List;

import com.zero.domain.Developer;

public class DeveloperList {
	private int count;
    private String next;
    private String previous;
    private List<DeveloperAPI> results;
    
    
	public int getCount() {
		return count;
	}
	public String getNext() {
		return next;
	}
	public String getPrevious() {
		return previous;
	}
	public List<DeveloperAPI> getResults() {
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
	public void setResults(List<DeveloperAPI> results) {
		this.results = results;
	}


}
