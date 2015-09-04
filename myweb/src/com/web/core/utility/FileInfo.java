package com.web.core.utility;

import javax.activation.DataHandler;

public class FileInfo{
  private String fileName;
  private String fileType;
  private DataHandler dataHandler;
  private String plateNumber;

  public String getPlateNumber()
  {
    return this.plateNumber;
  }

  public void setPlateNumber(String plateNumber) {
    this.plateNumber = plateNumber;
  }

  public String getFileName() {
    return this.fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public String getFileType() {
    return this.fileType;
  }

  public void setFileType(String fileType) {
    this.fileType = fileType;
  }

  public DataHandler getDataHandler() {
    return this.dataHandler;
  }

  public void setDataHandler(DataHandler dataHandler) {
    this.dataHandler = dataHandler;
  }
}
