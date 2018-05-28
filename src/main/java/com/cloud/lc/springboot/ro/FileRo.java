package com.cloud.lc.springboot.ro;



public class FileRo {
	
	private String path;
    
    public FileRo(String path) {
        this.path = path;
    }
    public String getPath() {
        return path;
    }
    public void setPath(String path) {
        this.path = path;
    }
	
}
