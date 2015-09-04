package com.web.core.utility;

import java.io.Serializable;
import java.util.List;


public class SoapTruckInfoVO implements Serializable{
  private static final long serialVersionUID = 1L;
  private String xml;
  private List<FileInfo> fileInfo;

  public String getXml()
  {
    return this.xml;
  }
  public void setXml(String xml) {
    this.xml = xml;
  }
  public List<FileInfo> getFileInfo() {
    return this.fileInfo;
  }
  public void setFileInfo(List<FileInfo> fileInfo) {
    this.fileInfo = fileInfo;
  }
}