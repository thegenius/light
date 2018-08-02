package com.lvonce.lightserver.general.file;

import lombok.Data;

@Data
public class UploadFileResponse {
    private String fileName;
    private String fileDownloadUri;
    private String fileType;
    private Long size;
}
